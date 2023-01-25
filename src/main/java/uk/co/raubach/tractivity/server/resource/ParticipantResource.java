package uk.co.raubach.tractivity.server.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.*;
import org.jooq.*;
import org.jooq.impl.DSL;
import uk.co.raubach.tractivity.server.AuthenticationFilter;
import uk.co.raubach.tractivity.server.database.Database;
import uk.co.raubach.tractivity.server.database.codegen.tables.ActivityParticipants;
import uk.co.raubach.tractivity.server.database.codegen.tables.Participants;
import uk.co.raubach.tractivity.server.database.codegen.tables.pojos.ViewActivityMeasures;
import uk.co.raubach.tractivity.server.database.codegen.tables.pojos.ViewActivityParticipantMeasures;
import uk.co.raubach.tractivity.server.database.codegen.tables.pojos.ViewParticipants;
import uk.co.raubach.tractivity.server.database.codegen.tables.records.*;
import uk.co.raubach.tractivity.server.pojo.*;
import uk.co.raubach.tractivity.server.util.*;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static uk.co.raubach.tractivity.server.database.codegen.tables.Activities.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.ActivityMeasures.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.ActivityParticipants.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.ActivityTypes.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.Measures.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.Participants.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.ViewActivityMeasures.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.ViewActivityParticipantMeasures.*;
import static uk.co.raubach.tractivity.server.database.codegen.tables.ViewParticipants.*;


@Path("participant")
public class ParticipantResource extends BaseResource implements IFilteredResource
{
	@GET
	@Path("/{participantId:\\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response getParticipantById(@PathParam("participantId") Integer participantId)
		throws SQLException
	{
		if (participantId == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			return Response.ok(context.selectFrom(VIEW_PARTICIPANTS)
									  .where(VIEW_PARTICIPANTS.PARTICIPANT_ID.eq(participantId))
									  .fetchAnyInto(ViewParticipants.class))
						   .build();
		}
	}

	@PATCH
	@Path("/{participantId:\\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response patchParticipant(@PathParam("participantId") Integer participantId, uk.co.raubach.tractivity.server.database.codegen.tables.pojos.Participants update)
		throws SQLException
	{
		if (update == null || update.getId() == null || !Objects.equals(update.getId(), participantId) || StringUtils.isEmpty(update.getName()))
			return Response.status(Response.Status.BAD_REQUEST).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			// Check user exists
			ParticipantsRecord existingParticipant = context.selectFrom(PARTICIPANTS)
															.where(PARTICIPANTS.ID.eq(participantId))
															.fetchAny();

			if (existingParticipant == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			// Update
			if (!StringUtils.isEmpty(update.getName()))
				existingParticipant.setName(update.getName());
			if (update.getDob() != null)
				existingParticipant.setDob(update.getDob());
			if (update.getGender() != null)
				existingParticipant.setGender(update.getGender());
			if (existingParticipant.changed())
				existingParticipant.store();

			return Response.ok().build();
		}
	}

	@POST
	@Path("/{participantId:\\d+}/activity")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response getParticipantActivities(@PathParam("participantId") Integer participantId, PaginatedRequest request)
		throws SQLException
	{
		if (participantId == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		processRequest(request);
		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);
			SelectSelectStep<Record> select = context.select();

			if (previousCount == -1)
				select.hint("SQL_CALC_FOUND_ROWS");

			SelectConditionStep<Record> from = select.from(VIEW_ACTIVITY_PARTICIPANT_MEASURES)
													 .where(DSL.condition("json_contains(" + VIEW_ACTIVITY_PARTICIPANT_MEASURES.PARTICIPANT_MEASURES.getName() + ", json_object('participantId', " + participantId + "))"));

			filter(from, filters);

			List<ViewActivityParticipantMeasures> result = setPaginationAndOrderBy(from)
				.fetch()
				.into(ViewActivityParticipantMeasures.class);

			long count = previousCount == -1 ? context.fetchOne("SELECT FOUND_ROWS()").into(Long.class) : previousCount;

			return Response.ok(new PaginatedResult<>(result, count)).build();
		}
	}

	@GET
	@Path("/{participantId:\\d+}/participantcount")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response getParticipantParticipantCounts(@PathParam("participantId") Integer participantId)
		throws SQLException
	{
		if (participantId == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			Participants p = PARTICIPANTS.as("p");
			Participants op = PARTICIPANTS.as("op");
			ActivityParticipants ap = ACTIVITY_PARTICIPANTS.as("ap");
			ActivityParticipants aop = ACTIVITY_PARTICIPANTS.as("aop");

			Field<Integer> id = op.ID.as("participantId");
			Field<Integer> count = DSL.count().as("count");

			return Response.ok(context.select(
										  id,
										  op.NAME.as("participantName"),
										  op.GENDER.as("participantGender"),
										  count
									  ).from(p).leftJoin(ap).on(p.ID.eq(ap.PARTICIPANT_ID))
									  .leftJoin(ACTIVITIES).on(ACTIVITIES.ID.eq(ap.ACTIVITY_ID))
									  .leftJoin(aop).on(aop.ACTIVITY_ID.eq(ACTIVITIES.ID))
									  .leftJoin(op).on(op.ID.eq(aop.PARTICIPANT_ID))
									  .where(p.ID.eq(participantId))
									  .and(p.ID.notEqual(op.ID))
									  .groupBy(id)
									  .orderBy(count.desc())
									  .fetchInto(ParticipantCount.class)).build();
		}
	}

	@GET
	@Path("/{participantId:\\d+}/activitycount")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response getParticipantActivityCounts(@PathParam("participantId") Integer participantId)
		throws SQLException
	{
		if (participantId == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			Field<Integer> id = ACTIVITY_TYPES.ID.as("activityTypeId");
			Field<Integer> count = DSL.count().as("count");

			return Response.ok(context.select(
										  id,
										  ACTIVITY_TYPES.NAME.as("activityTypeName"),
										  count
									  ).from(PARTICIPANTS).leftJoin(ACTIVITY_PARTICIPANTS).on(ACTIVITY_PARTICIPANTS.PARTICIPANT_ID.eq(PARTICIPANTS.ID))
									  .leftJoin(ACTIVITIES).on(ACTIVITIES.ID.eq(ACTIVITY_PARTICIPANTS.ACTIVITY_ID))
									  .leftJoin(ACTIVITY_TYPES).on(ACTIVITY_TYPES.ID.eq(ACTIVITIES.ACTIVITY_TYPE_ID))
									  .where(PARTICIPANTS.ID.eq(participantId))
									  .groupBy(id)
									  .orderBy(count.desc())
									  .fetchInto(ActivityCount.class)).build();
		}
	}

	@POST
	@Path("/table")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public PaginatedResult<List<ViewParticipants>> getParticipants(PaginatedRequest request)
		throws SQLException
	{
		processRequest(request);
		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);
			SelectSelectStep<Record> select = context.select();

			if (previousCount == -1)
				select.hint("SQL_CALC_FOUND_ROWS");

			SelectJoinStep<Record> from = select.from(VIEW_PARTICIPANTS);

			filter(from, filters);

			List<ViewParticipants> result = setPaginationAndOrderBy(from)
				.fetch()
				.into(ViewParticipants.class);

			long count = previousCount == -1 ? context.fetchOne("SELECT FOUND_ROWS()").into(Long.class) : previousCount;

			return new PaginatedResult<>(result, count);
		}
	}

	@GET
	@Path("/{participantId:\\d+}/img")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({"image/png", "image/jpeg", "image/*"})
	public Response getParticipantImage(@PathParam("participantId") Integer participantId, @QueryParam("imageToken") String imageToken)
		throws SQLException
	{
		// Check the image token
		if (!AuthenticationFilter.isValidImageToken(imageToken))
			return Response.status(Response.Status.FORBIDDEN).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			ParticipantsRecord participant = context.selectFrom(PARTICIPANTS).where(PARTICIPANTS.ID.eq(participantId)).fetchAny();

			if (participant == null || participant.getImage() == null)
				return Response.status(Response.Status.NOT_FOUND).build();
			else
				return Response.ok((StreamingOutput) output -> {
								   output.write(participant.getImage());
								   output.flush();
							   })
							   .type("image/png")
							   .build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response postParticipant(uk.co.raubach.tractivity.server.database.codegen.tables.pojos.Participants newParticipant)
		throws SQLException
	{
		if (newParticipant == null || newParticipant.getId() != null || StringUtils.isEmpty(newParticipant.getName()) || newParticipant.getDob() == null || newParticipant.getGender() == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);
			// Email address already in use by a different user

			ParticipantsRecord record = context.newRecord(PARTICIPANTS, newParticipant);
			return Response.ok(record.store() > 0).build();
		}
	}

	@DELETE
	@Path("/{participantId:\\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteParticipant(@PathParam("participantId") Integer participantId)
		throws SQLException
	{
		if (participantId == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);
			// Email address already in use by a different user

			ParticipantsRecord record = context.selectFrom(PARTICIPANTS).where(PARTICIPANTS.ID.eq(participantId)).fetchAny();

			if (record == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			return Response.ok(record.delete() > 0).build();
		}
	}

	@POST
	@Path("/{participantId:\\d+}/img")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postParticipantImage(@PathParam("participantId") Integer participantId, @FormDataParam("image") InputStream fileIs, @FormDataParam("image") FormDataContentDisposition fileDetails)
		throws SQLException, IOException
	{
		if (participantId == null || fileIs == null || fileDetails == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		if (fileDetails.getSize() >= 4194304)
			return Response.status(Response.Status.REQUEST_ENTITY_TOO_LARGE).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			ParticipantsRecord record = context.selectFrom(PARTICIPANTS).where(PARTICIPANTS.ID.eq(participantId)).fetchAny();

			if (record == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			record.setImage(IOUtils.toByteArray(fileIs));
			return Response.ok(record.store(PARTICIPANTS.IMAGE) > 0).build();
		}
	}

	@GET
	@Path("/{participantId:\\d+}/{activityId:\\d+}/measure")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response postParticipantMeasure(@PathParam("participantId") Integer participantId, @PathParam("activityId") Integer activityId)
		throws SQLException
	{
		if (participantId == null || activityId == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			return Response.ok(context.selectFrom(VIEW_ACTIVITY_MEASURES)
									  .where(VIEW_ACTIVITY_MEASURES.ACTIVITY_ID.eq(activityId))
									  .and(VIEW_ACTIVITY_MEASURES.PARTICIPANT_ID.eq(participantId))
									  .fetchInto(ViewActivityMeasures.class))
						   .build();
		}
	}

	@POST
	@Path("/{participantId:\\d+}/{activityId:\\d+}/measure")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response postParticipantMeasure(@PathParam("participantId") Integer participantId, @PathParam("activityId") Integer activityId, SimpleMeasures[] toAdd)
		throws SQLException
	{
		if (participantId == null || activityId == null || toAdd == null || CollectionUtils.isEmptyOrNull(toAdd))
			return Response.status(Response.Status.BAD_REQUEST).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			Set<Integer> measureIds = Arrays.stream(toAdd).map(m -> m.getMeasureId()).collect(Collectors.toSet());
			ParticipantsRecord participant = context.selectFrom(PARTICIPANTS).where(PARTICIPANTS.ID.eq(participantId)).fetchAny();
			List<MeasuresRecord> measures = context.selectFrom(MEASURES).where(MEASURES.ID.in(measureIds)).fetch();
			ActivitiesRecord activity = context.selectFrom(ACTIVITIES).where(ACTIVITIES.ID.eq(activityId)).fetchAny();

			if (participant == null || measures.size() != measureIds.size() || activity == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			for (SimpleMeasures m : toAdd)
			{
				ActivityMeasuresRecord am = context.newRecord(ACTIVITY_MEASURES);
				am.setActivityId(activity.getId());
				am.setParticipantId(participant.getId());
				am.setMeasureId(m.getMeasureId());
				am.setMeasuredValue(m.getMeasuredValue());
				am.setCreatedOn(new Timestamp(System.currentTimeMillis()));
				am.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
				am.store();
			}

			return Response.ok().build();
		}
	}
}
