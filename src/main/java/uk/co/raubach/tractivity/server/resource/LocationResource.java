package uk.co.raubach.tractivity.server.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.jooq.DSLContext;
import uk.co.raubach.tractivity.server.database.Database;
import uk.co.raubach.tractivity.server.database.codegen.tables.pojos.Locations;
import uk.co.raubach.tractivity.server.database.codegen.tables.records.LocationsRecord;
import uk.co.raubach.tractivity.server.util.*;

import java.sql.*;

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
