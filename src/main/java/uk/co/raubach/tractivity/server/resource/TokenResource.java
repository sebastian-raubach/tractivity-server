/*
 * Copyright 2018 Information & Computational Sciences, The James Hutton Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.raubach.tractivity.server.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import uk.co.raubach.tractivity.server.AuthenticationFilter;
import uk.co.raubach.tractivity.server.pojo.*;
import uk.co.raubach.tractivity.server.util.*;

import java.io.IOException;
import java.util.*;

/**
 * @author Sebastian Raubach
 */
@Path("token")
public class TokenResource extends ContextResource
{
	public static Integer SALT = 10;

	@POST
	@Path("/check")
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkToken(Token token) {
		AuthenticationFilter.UserDetails sessionUser = (AuthenticationFilter.UserDetails) securityContext.getUserPrincipal();

		if (token != null && !StringUtils.isEmpty(token.getToken()) && Objects.equals(token.getToken(), sessionUser.getToken()))
			return Response.status(Response.Status.OK).build();
		else
			return Response.status(Response.Status.UNAUTHORIZED).build();
	}

	@DELETE
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteToken(LoginDetails user)
		throws IOException
	{
		if (user == null)
		{
			return Response.status(Response.Status.NOT_FOUND)
						   .build();
		}

		AuthenticationFilter.UserDetails sessionUser = (AuthenticationFilter.UserDetails) securityContext.getUserPrincipal();

		if (sessionUser == null || !Objects.equals(sessionUser.getToken(), user.getPassword()))
		{
			return Response.status(Response.Status.FORBIDDEN)
						   .build();
		}

		try
		{
			// Try and see if it's a valid UUID
			UUID.fromString(user.getPassword());
			AuthenticationFilter.removeToken(user.getPassword(), req, resp);
			return Response.ok(true).build();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return Response.ok(false).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postToken(LoginDetails request)
		throws IOException
	{
		boolean canAccess = false;
		try
		{
			String username = PropertyWatcher.get(ServerProperty.ADMIN_USERNAME);
			String password = PropertyWatcher.get(ServerProperty.ADMIN_PASSWORD);

			canAccess = Objects.equals(request.getUsername(), username) && Objects.equals(request.getPassword(), password);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return Response.status(Response.Status.FORBIDDEN.getStatusCode(), "Invalid credentials.")
						   .build();
		}

		String token;
		String imageToken;

		if (canAccess)
		{
			token = UUID.randomUUID().toString();
			imageToken = UUID.randomUUID().toString();
			AuthenticationFilter.addToken(this.req, this.resp, token, imageToken);
		}
		else
		{
			return Response.status(Response.Status.FORBIDDEN.getStatusCode(), "Invalid credentials.")
						   .build();
		}

		return Response.ok(new Token(token, imageToken, AuthenticationFilter.AGE, System.currentTimeMillis()))
					   .build();
	}
}
