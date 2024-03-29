package uk.co.raubach.tractivity.server;

import jakarta.annotation.Priority;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.*;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.Provider;
import uk.co.raubach.tractivity.server.util.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filter that checks if restricted resources are only accessed if a valid Bearer token is set.
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter
{
	private static final String REALM                 = "example";
	private static final String AUTHENTICATION_SCHEME = "Bearer";

	public static final long AGE = 43200000; // 12 hours

	private static Map<String, UserDetails> tokenToTimestamp  = new ConcurrentHashMap<>();
	private static Map<String, String>      tokenToImageToken = new ConcurrentHashMap<>();

	@Context
	private HttpServletRequest  request;
	@Context
	private HttpServletResponse response;
	@Context
	ServletContext servletContext;
	@Context
	private ResourceInfo resourceInfo;

	public static void updateAcceptedDatasets(HttpServletRequest req, HttpServletResponse resp, Integer licenseId)
	{
		Set<Integer> ids = getAcceptedLicenses(req);
		ids.add(licenseId);

		Cookie cookie = new Cookie("accepted-licenses", CollectionUtils.join(ids, ","));
		cookie.setPath(getContextPath(req));
		cookie.setMaxAge((int) (AGE / 1000));
		cookie.setHttpOnly(true);
		resp.addCookie(cookie);
	}

	@Override
	public void filter(ContainerRequestContext requestContext)
		throws IOException
	{
		// Get the Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// Extract the token from the Authorization header
		String token;

		if (!StringUtils.isEmpty(authorizationHeader))
			token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();
		else
			token = null;

		if (Objects.equals(token, "null"))
			token = null;

		final String finalToken = token;

		final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
		requestContext.setSecurityContext(new SecurityContext()
		{
			@Override
			public Principal getUserPrincipal()
			{
				UserDetails details = finalToken == null ? null : tokenToTimestamp.get(finalToken);

				if (details == null)
				{
					details = new UserDetails(null, null, AGE);
				}

				return details;
			}

			@Override
			public boolean isUserInRole(String role)
			{
				return true;
			}

			@Override
			public boolean isSecure()
			{
				return currentSecurityContext.isSecure();
			}

			@Override
			public String getAuthenticationScheme()
			{
				return AUTHENTICATION_SCHEME;
			}
		});

		Class<?> resourceClass = resourceInfo.getResourceClass();
		boolean isClassFree = resourceClass.getAnnotation(PermitAll.class) != null;

		Method resourceMethod = resourceInfo.getResourceMethod();
		boolean isMethodFree = resourceMethod.getAnnotation(PermitAll.class) != null;

		try
		{
			validateToken(token);
		}
		catch (Exception e)
		{
			abortWithUnauthorized(requestContext);
		}
	}

	/**
	 * Removes and invalidates all currently available tokens.
	 */
	public static void invalidateAllTokens()
	{
		tokenToTimestamp.clear();
		tokenToImageToken.clear();
	}

	private boolean isTokenBasedAuthentication(String authorizationHeader)
	{

		// Check if the Authorization header is valid
		// It must not be null and must be prefixed with "Bearer" plus a whitespace
		// The authentication scheme comparison must be case-insensitive
		return authorizationHeader != null && authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}

	private void abortWithUnauthorized(ContainerRequestContext requestContext)
	{

		// Abort the filter chain with a 401 status code response
		// The WWW-Authenticate header is sent along with the response
		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
										 .header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
										 .build());
	}

	private void validateToken(String token)
	{
		boolean canAccess = false;

		// Check if it's a valid token
		UserDetails details = tokenToTimestamp.get(token);

		if (details != null)
		{
			// First, check the bearer token and see if we have it in the cache
			if ((System.currentTimeMillis() - AGE) < details.timestamp)
			{
				canAccess = true;
				// Extend the cookie
				details.timestamp = System.currentTimeMillis();
				tokenToTimestamp.put(token, details);
			}
			else
			{
				// TODO
				removeToken(token, request, response);
				throw new RuntimeException();
			}
		}

		if (canAccess)
		{
			// Extend the cookie here
			setCookie(token, request, response);
		}
		else
		{
			removeToken(token, request, response);

			// TODO
			throw new RuntimeException();
		}
	}

	public static void addToken(HttpServletRequest request, HttpServletResponse response, String token, String imageToken)
	{
		setCookie(token, request, response);
		UserDetails details = new UserDetails();
		details.timestamp = System.currentTimeMillis();
		details.imageToken = imageToken;
		details.token = token;
		tokenToTimestamp.put(token, details);
		tokenToImageToken.put(token, details.imageToken);
	}

	public static void removeToken(String token, HttpServletRequest request, HttpServletResponse response)
	{
		if (token != null)
		{
			UserDetails exists = tokenToTimestamp.remove(token);
			if (exists != null)
				tokenToImageToken.remove(exists.imageToken);
		}

		setCookie(null, request, response);
		setDatasetCookie(true, request, response);
	}

	private static void setCookie(String token, HttpServletRequest request, HttpServletResponse response)
	{
		boolean delete = StringUtils.isEmpty(token);

		if (delete)
		{
			Cookie cookie = new Cookie("token", "");
			cookie.setPath(getContextPath(request));
			cookie.setMaxAge(0);
			cookie.setHttpOnly(true);
			response.addCookie(cookie);

			// This is for the docker image that uses a proxy-reverse
			cookie = new Cookie("token", "");
			cookie.setPath("/");
			cookie.setMaxAge(0);
			cookie.setHttpOnly(true);
			response.addCookie(cookie);
		}
		else
		{
			Cookie cookie = new Cookie("token", token);
			cookie.setPath(getContextPath(request));
			cookie.setMaxAge((int) (AGE / 1000));
			cookie.setHttpOnly(true);
			response.addCookie(cookie);
		}

		setDatasetCookie(false, request, response);
	}

	private static void setDatasetCookie(boolean delete, HttpServletRequest request, HttpServletResponse response)
	{
		Set<Integer> ids = getAcceptedLicenses(request);
		if (!CollectionUtils.isEmpty(ids))
		{
			Cookie cookie = new Cookie("accepted-licenses", CollectionUtils.join(ids, ","));
			cookie.setPath(getContextPath(request));
			cookie.setMaxAge(delete ? 0 : (int) (AGE / 1000));
			cookie.setHttpOnly(true);
			response.addCookie(cookie);
		}
	}

	public static Set<Integer> getAcceptedLicenses(HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();

		if (CollectionUtils.isEmpty(cookies))
			return new HashSet<>();
		else
			return Arrays.stream(cookies)
						 .filter(c -> c.getName().equals("accepted-licenses"))
						 .map(c -> {
							 String[] values = c.getValue().split(",");
							 Set<Integer> result = new HashSet<>();
							 for (String value : values)
							 {
								 try
								 {
									 result.add(Integer.parseInt(value));
								 }
								 catch (NumberFormatException e)
								 {
								 }
							 }
							 return result;
						 })
						 .findFirst()
						 .orElse(new HashSet<>());
	}

	private static String getContextPath(HttpServletRequest request)
	{
		String result = request.getContextPath();

		if (!StringUtils.isEmpty(result))
		{
			result = URLDecoder.decode(result, StandardCharsets.UTF_8);

			int index = result.lastIndexOf("/api");

			if (index != -1)
			{
				result = result.substring(0, index + 4);
			}
		}

		if (StringUtils.isEmpty(result))
			result = "/";

		return result;
	}

	/**
	 * Checks whether the given image token is valid
	 *
	 * @param imageToken The token
	 * @return <code>true</code> if the token is valid
	 */
	public static boolean isValidImageToken(String imageToken)
	{
		return !StringUtils.isEmpty(imageToken) && tokenToImageToken.containsValue(imageToken);
	}

	public static class UserDetails implements Principal
	{
		private String  token;
		private String  imageToken;
		private Long    timestamp;

		public UserDetails()
		{
		}

		public UserDetails(String token, String imageToken, Long timestamp)
		{
			this.token = token;
			this.imageToken = imageToken;
			this.timestamp = timestamp;
		}

		public String getToken()
		{
			return token;
		}

		public String getImageToken()
		{
			return imageToken;
		}

		public Long getTimestamp()
		{
			return timestamp;
		}

		@Override
		public String toString()
		{
			return "UserDetails{" +
				", token='" + token + '\'' +
				", imageToken='" + imageToken + '\'' +
				", timestamp=" + timestamp +
				'}';
		}

		@Override
		public String getName()
		{
			return token;
		}
	}
}
