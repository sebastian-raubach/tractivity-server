package uk.co.raubach.tractivity.server.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.jooq.*;
import org.jooq.impl.DSL;
import uk.co.raubach.tractivity.server.database.Database;
import uk.co.raubach.tractivity.server.database.codegen.tables.pojos.*;
import uk.co.raubach.tractivity.server.database.codegen.tables.records.*;
import uk.co.raubach.tractivity.server.pojo.*;
import uk.co.raubach.tractivity.server.util.*;

import java.sql.*;
import java.util.List;

import static uk.co.raubach.tractivity.server.database.codegen.tables.Activities.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.ActivityParticipants.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.ActivityTypes.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.Events.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.Locations.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.Participants.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.ViewActivities.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.ViewActivityParticipantMeasures.*;

@Path("activity")
public class ActivityResource extends BaseResource implements IFilteredResource
{
	@GET
	@Path("/{activityId:\\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response getActivityById(@PathParam("activityId") Integer activityId)
		throws SQLException
	{
		if (activityId == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			return Response.ok(context.selectFrom(VIEW_ACTIVITY_PARTICIPANT_MEASURES)
									  .where(VIEW_ACTIVITY_PARTICIPANT_MEASURES.ACTIVITY_ID.eq(activityId))
									  .fetchAnyInto(ViewActivityParticipantMeasures.class)
			).build();
		}
	}

	@POST
	@Path("/table")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public PaginatedResult<List<ViewActivities>> getActivities(PaginatedRequest request)
		throws SQLException
	{
		processRequest(request);
		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);
			SelectSelectStep<Record> select = context.select();

			if (previousCount == -1)
				select.hint("SQL_CALC_FOUND_ROWS");

			SelectJoinStep<Record> from = select.from(VIEW_ACTIVITIES);

			filter(from, filters);

			List<ViewActivities> result = setPaginationAndOrderBy(from)
				.fetch()
				.into(ViewActivities.class);

			long count = previousCount == -1 ? context.fetchOne("SELECT FOUND_ROWS()").into(Long.class) : previousCount;

			return new PaginatedResult<>(result, count);
		}
	}

	@GET
	@Path("/year")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response getActivityYears()
		throws SQLException
	{
		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			return Response.ok(context.select(DSL.min(DSL.year(ACTIVITIES.CREATED_ON)).as("min"), DSL.max(DSL.year(ACTIVITIES.CREATED_ON)).as("max"))
									  .from(ACTIVITIES)
									  .fetchAnyInto(ActivityYears.class))
						   .build();

		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response postActivity(ActivityPost newData)
		throws SQLException
	{
		if (newData.getActivityTypeId() == null || newData.getEventId() == null || newData.getCreatedOn() == null || CollectionUtils.isEmptyOrNull(newData.getParticipantIds()))
			return Response.status(Response.Status.BAD_REQUEST).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			EventsRecord event = context.selectFrom(EVENTS).where(EVENTS.ID.eq(newData.getEventId())).fetchAny();
			LocationsRecord location = context.selectFrom(LOCATIONS).where(LOCATIONS.ID.eq(newData.getLocationId())).fetchAny();
			ActivityTypesRecord activityType = context.selectFrom(ACTIVITY_TYPES).where(ACTIVITY_TYPES.ID.eq(newData.getActivityTypeId())).fetchAny();

			List<Participants> participants = context.selectFrom(PARTICIPANTS).where(PARTICIPANTS.ID.in(newData.getParticipantIds())).fetchInto(Participants.class);

			if (event == null || location == null || activityType == null || participants.size() != newData.getParticipantIds().size())
				return Response.status(Response.Status.NOT_FOUND).build();

			// Create the new activity
			ActivitiesRecord newActivity = context.newRecord(ACTIVITIES);
			newActivity.setActivityTypeId(activityType.getId());
			newActivity.setEventId(event.getId());
			newActivity.setLocationId(location.getId());
			if (newData.getCreatedOn() != null)
				newActivity.setCreatedOn(newData.getCreatedOn());
			else
				newActivity.setCreatedOn(new Timestamp(System.currentTimeMillis()));
			newActivity.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			newActivity.store();

			for (Participants p : participants)
			{
				// Create a connection between every participant and this activity
				ActivityParticipantsRecord ap = context.newRecord(ACTIVITY_PARTICIPANTS);
				ap.setActivityId(newActivity.getId());
				ap.setParticipantId(p.getId());
				if (newData.getCreatedOn() != null)
					ap.setCreatedOn(newData.getCreatedOn());
				else
					ap.setCreatedOn(new Timestamp(System.currentTimeMillis()));
				ap.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
				ap.store();
			}
		}

		return Response.ok().build();
	}
}
