package uk.co.raubach.tractivity.server.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.jooq.*;
import org.jooq.impl.DSL;
import uk.co.raubach.tractivity.server.database.Database;
import uk.co.raubach.tractivity.server.database.codegen.tables.pojos.*;
import uk.co.raubach.tractivity.server.pojo.*;
import uk.co.raubach.tractivity.server.util.*;

import java.sql.*;
import java.util.List;

import static uk.co.raubach.tractivity.server.database.codegen.tables.Activities.*;
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

//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	@Secured
//	public Response postActivity(ViewActivityParticipantMeasures newData)
//		throws SQLException
//	{
//		if (newData.getActivityId() == null || newData.getEventId() == null || newData.getActivityTypeId() == null || newData.getActivityCreatedOn() == null || CollectionUtils.isEmptyOrNull(newData.getParticipantMeasures()))
//			return Response.status(Response.Status.BAD_REQUEST).build();
//
//		try (Connection conn = Database.getConnection())
//		{
//			DSLContext context = Database.getContext(conn);
//		}
//	}
}
