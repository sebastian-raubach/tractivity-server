package uk.co.raubach.tractivity.server.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.jooq.DSLContext;
import uk.co.raubach.tractivity.server.database.Database;
import uk.co.raubach.tractivity.server.database.codegen.tables.pojos.*;
import uk.co.raubach.tractivity.server.database.codegen.tables.records.*;
import uk.co.raubach.tractivity.server.util.*;

import java.sql.*;
import java.util.Objects;

import static uk.co.raubach.tractivity.server.database.codegen.tables.Events.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.Locations.*;

@Path("location")
@Secured
public class LocationResource
{
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocations()
		throws SQLException
	{
		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			return Response.ok(context.selectFrom(LOCATIONS)
									  .orderBy(LOCATIONS.NAME)
									  .fetchInto(Locations.class))
						   .build();
		}
	}

	@PATCH
	@Path("/{locationId:\\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response patchEvent(@PathParam("locationId") Integer locationId, Locations update)
		throws SQLException
	{
		if (update == null || update.getId() == null || !Objects.equals(update.getId(), locationId) || StringUtils.isEmpty(update.getName()))
			return Response.status(Response.Status.BAD_REQUEST).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			// Check event exists
			LocationsRecord existingLocation = context.selectFrom(LOCATIONS)
												.where(LOCATIONS.ID.eq(locationId))
												.fetchAny();

			if (existingLocation == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			// Update
			if (!StringUtils.isEmpty(update.getName()))
				existingLocation.setName(update.getName());
			if (update.getLatitude() != null)
				existingLocation.setLatitude(update.getLatitude());
			if (update.getLongitude() != null)
				existingLocation.setLongitude(update.getLongitude());
			if (update.getElevation() != null)
				existingLocation.setElevation(update.getElevation());
			if (update.getCreatedOn() != null)
				existingLocation.setCreatedOn(update.getCreatedOn());
			else
				existingLocation.setCreatedOn(new Timestamp(System.currentTimeMillis()));
			if (existingLocation.changed())
				existingLocation.store();

			return Response.ok().build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postLocation(Locations location)
		throws SQLException
	{
		if (location == null || StringUtils.isEmpty(location.getName()) || location.getName().length() >= 255)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			location.setCreatedOn(new Timestamp(System.currentTimeMillis()));
			location.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			LocationsRecord record = context.newRecord(LOCATIONS, location);
			return Response.ok(record.store() > 0).build();
		}
	}
}
