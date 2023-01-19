package uk.co.raubach.tractivity.server.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.*;
import org.jooq.DSLContext;
import uk.co.raubach.tractivity.server.AuthenticationFilter;
import uk.co.raubach.tractivity.server.database.Database;
import uk.co.raubach.tractivity.server.database.codegen.tables.pojos.ActivityTypes;
import uk.co.raubach.tractivity.server.database.codegen.tables.records.*;
import uk.co.raubach.tractivity.server.util.*;

import java.io.*;
import java.sql.*;

import static uk.co.raubach.tractivity.server.database.codegen.tables.ActivityTypes.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.Participants.*;

@Path("activitytype")
public class ActivityTypeResource
{
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response getActivityTypes()
		throws SQLException
	{
		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			return Response.ok(context.selectFrom(ACTIVITY_TYPES)
									  .orderBy(ACTIVITY_TYPES.NAME)
									  .fetchInto(ActivityTypes.class))
						   .build();
		}
	}

	@GET
	@Path("/{activityTypeId:\\d+}/img")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({"image/png", "image/jpeg", "image/*"})
	public Response getActivityTypeImage(@PathParam("activityTypeId") Integer activityTypeId, @QueryParam("imageToken") String imageToken)
		throws SQLException
	{
		// Check the image token
		if (!AuthenticationFilter.isValidImageToken(imageToken))
			return Response.status(Response.Status.FORBIDDEN).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			ActivityTypesRecord activityType = context.selectFrom(ACTIVITY_TYPES).where(ACTIVITY_TYPES.ID.eq(activityTypeId)).fetchAny();

			if (activityType == null || activityType.getImage() == null)
				return Response.status(Response.Status.NOT_FOUND).build();
			else
				return Response.ok((StreamingOutput) output -> {
								   output.write(activityType.getImage());
								   output.flush();
							   })
							   .type("image/png")
							   .build();
		}
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response postParticipantImage(@FormDataParam("name") String name, @FormDataParam("image") InputStream fileIs, @FormDataParam("image") FormDataContentDisposition fileDetails)
		throws SQLException, IOException
	{
		if (StringUtils.isEmpty(name) || name.length() >= 255)
			return Response.status(Response.Status.BAD_REQUEST).build();

		if (fileDetails != null && fileDetails.getSize() >= 4194304)
			return Response.status(Response.Status.REQUEST_ENTITY_TOO_LARGE).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			ActivityTypesRecord record = context.newRecord(ACTIVITY_TYPES);
			record.setName(name);
			record.setCreatedOn(new Timestamp(System.currentTimeMillis()));
			record.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			if (fileIs != null)
				record.setImage(IOUtils.toByteArray(fileIs));
			return Response.ok(record.store() > 0).build();
		}
	}
}
