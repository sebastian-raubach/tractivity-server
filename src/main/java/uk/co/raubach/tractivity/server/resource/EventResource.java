package uk.co.raubach.tractivity.server.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.jooq.*;
import org.jooq.Record;
import uk.co.raubach.tractivity.server.database.Database;
import uk.co.raubach.tractivity.server.database.codegen.tables.pojos.*;
import uk.co.raubach.tractivity.server.database.codegen.tables.records.*;
import uk.co.raubach.tractivity.server.pojo.*;
import uk.co.raubach.tractivity.server.util.*;

import java.sql.*;
import java.util.*;

import static uk.co.raubach.tractivity.server.database.codegen.tables.Events.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.Participants.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.ViewActivities.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.ViewEvents.*;

@Path("event")
@Secured
public class EventResource extends BaseResource implements IFilteredResource
{
	@GET
	@Path("/{eventId:\\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response getEventById(@PathParam("eventId") Integer eventId)
		throws SQLException
	{
		if (eventId == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			return Response.ok(context.selectFrom(VIEW_EVENTS)
									  .where(VIEW_EVENTS.EVENT_ID.eq(eventId))
									  .fetchAnyInto(ViewEvents.class)).build();
		}
	}

	@POST
	@Path("/{eventId:\\d+}/activity")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response getEventActivities(@PathParam("eventId") Integer eventId, PaginatedRequest request)
		throws SQLException
	{
		if (eventId == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		processRequest(request);
		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);
			SelectSelectStep<Record> select = context.select();

			if (previousCount == -1)
				select.hint("SQL_CALC_FOUND_ROWS");

			SelectConditionStep<Record> from = select.from(VIEW_ACTIVITIES)
													 .where(VIEW_ACTIVITIES.EVENT_ID.eq(eventId));

			filter(from, filters);

			List<ViewActivities> result = setPaginationAndOrderBy(from)
				.fetch()
				.into(ViewActivities.class);

			long count = previousCount == -1 ? context.fetchOne("SELECT FOUND_ROWS()").into(Long.class) : previousCount;

			return Response.ok(new PaginatedResult<>(result, count)).build();
		}
	}

	@POST
	@Path("/table")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedResult<List<ViewEvents>> getEvents(PaginatedRequest request)
		throws SQLException
	{
		processRequest(request);
		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);
			SelectSelectStep<Record> select = context.select();

			if (previousCount == -1)
				select.hint("SQL_CALC_FOUND_ROWS");

			SelectJoinStep<Record> from = select.from(VIEW_EVENTS);

			filter(from, filters);

			List<ViewEvents> result = setPaginationAndOrderBy(from)
				.fetch()
				.into(ViewEvents.class);

			long count = previousCount == -1 ? context.fetchOne("SELECT FOUND_ROWS()").into(Long.class) : previousCount;

			return new PaginatedResult<>(result, count);
		}
	}

	@PATCH
	@Path("/{eventId:\\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response patchEvent(@PathParam("eventId") Integer eventId, Events update)
		throws SQLException
	{
		if (update == null || update.getId() == null || !Objects.equals(update.getId(), eventId) || StringUtils.isEmpty(update.getName()))
			return Response.status(Response.Status.BAD_REQUEST).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			// Check event exists
			EventsRecord existingEvent = context.selectFrom(EVENTS)
													  .where(EVENTS.ID.eq(eventId))
													  .fetchAny();

			if (existingEvent == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			// Update
			if (!StringUtils.isEmpty(update.getName()))
				existingEvent.setName(update.getName());
			if (!StringUtils.isEmpty(update.getDescription()))
				existingEvent.setDescription(update.getDescription());
			if (update.getCreatedOn() != null)
				existingEvent.setCreatedOn(update.getCreatedOn());
			else
				existingEvent.setCreatedOn(new Timestamp(System.currentTimeMillis()));
			if (existingEvent.changed())
				existingEvent.store();

			return Response.ok().build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response postEvent(Events newEvent)
		throws SQLException
	{
		if (newEvent == null || newEvent.getId() != null || StringUtils.isEmpty(newEvent.getName()))
			return Response.status(Response.Status.BAD_REQUEST).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			EventsRecord record = context.newRecord(EVENTS, newEvent);
			return Response.ok(record.store() > 0).build();
		}
	}
}
