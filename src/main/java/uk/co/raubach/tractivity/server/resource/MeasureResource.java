package uk.co.raubach.tractivity.server.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.*;
import org.jooq.DSLContext;
import uk.co.raubach.tractivity.server.AuthenticationFilter;
import uk.co.raubach.tractivity.server.database.Database;
import uk.co.raubach.tractivity.server.database.codegen.enums.MeasuresType;
import uk.co.raubach.tractivity.server.database.codegen.tables.pojos.Measures;
import uk.co.raubach.tractivity.server.database.codegen.tables.records.*;
import uk.co.raubach.tractivity.server.pojo.MeasureRestrictions;
import uk.co.raubach.tractivity.server.util.*;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import static uk.co.raubach.tractivity.server.database.codegen.tables.Measures.*;

@Path("measure")
public class MeasureResource
{
	@PATCH
	@Path("/{measureId:\\d+}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response patchMeasure(
		@PathParam("measureId") Integer measureId,
		@FormDataParam("name") String name,
		@FormDataParam("measureType") MeasuresType measureType,
		@FormDataParam("minValue") Double minValue,
		@FormDataParam("maxValue") Double maxValue,
		@FormDataParam("minDate") String minDate,
		@FormDataParam("maxDate") String maxDate,
		@FormDataParam("categories") String categories,
		@FormDataParam("image") InputStream fileIs,
		@FormDataParam("image") FormDataContentDisposition fileDetails)
		throws SQLException, IOException
	{
		if (StringUtils.isEmpty(name) || name.length() >= 255)
			return Response.status(Response.Status.BAD_REQUEST).build();

		if (fileDetails != null && fileDetails.getSize() >= 4194304)
			return Response.status(Response.Status.REQUEST_ENTITY_TOO_LARGE).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			MeasuresRecord existingMeasure = context.selectFrom(MEASURES).where(MEASURES.ID.eq(measureId)).fetchAny();

			if (existingMeasure == null)
				return Response.status(Response.Status.NOT_FOUND).build();


			MeasureRestrictions restrictions = existingMeasure.getRestrictions();
			boolean hasRestrictions = restrictions != null;

			if (restrictions == null)
				restrictions = new MeasureRestrictions();

			try {
				restrictions.setMinDate(sdf.format(sdf.parse(minDate)));
			} catch (Exception e) {
				// Do nothing here
			}
			try {
				restrictions.setMaxDate(sdf.format(sdf.parse(maxDate)));
			} catch (Exception e) {
				// Do nothing here
			}
			restrictions.setMinValue(minValue);
			restrictions.setMaxValue(maxValue);
			if (!StringUtils.isEmpty(categories)) {
				String[] cats = categories.split(",");
				restrictions.setCategories(cats);
			}

			if (hasRestrictions)
				existingMeasure.setRestrictions(restrictions);

			if (!StringUtils.isEmpty(name))
				existingMeasure.setName(name);
			if (measureType != null)
				existingMeasure.setType(measureType);
			if (fileIs != null)
				existingMeasure.setImage(IOUtils.toByteArray(fileIs));
			return Response.ok(existingMeasure.store() > 0).build();
		}
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response postMeasure(
		@FormDataParam("name") String name,
		@FormDataParam("measureType") MeasuresType measureType,
		@FormDataParam("minValue") Double minValue,
		@FormDataParam("maxValue") Double maxValue,
		@FormDataParam("minDate") String minDate,
		@FormDataParam("maxDate") String maxDate,
		@FormDataParam("categories") String categories,
		@FormDataParam("image") InputStream fileIs,
		@FormDataParam("image") FormDataContentDisposition fileDetails)
		throws SQLException, IOException
	{
		if (StringUtils.isEmpty(name) || name.length() >= 255)
			return Response.status(Response.Status.BAD_REQUEST).build();

		if (fileDetails != null && fileDetails.getSize() >= 4194304)
			return Response.status(Response.Status.REQUEST_ENTITY_TOO_LARGE).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			MeasureRestrictions restrictions = new MeasureRestrictions();
			try {
				restrictions.setMinDate(sdf.format(sdf.parse(minDate)));
			} catch (Exception e) {
				// Do nothing here
			}
			try {
				restrictions.setMaxDate(sdf.format(sdf.parse(maxDate)));
			} catch (Exception e) {
				// Do nothing here
			}
			restrictions.setMinValue(minValue);
			restrictions.setMaxValue(maxValue);
			if (!StringUtils.isEmpty(categories)) {
				String[] cats = categories.split(",");
				restrictions.setCategories(cats);
			}

			MeasuresRecord record = context.newRecord(MEASURES);
			record.setName(name);
			record.setType(measureType);
			record.setRestrictions(restrictions);
			record.setCreatedOn(new Timestamp(System.currentTimeMillis()));
			record.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			if (fileIs != null)
				record.setImage(IOUtils.toByteArray(fileIs));
			return Response.ok(record.store() > 0).build();
		}
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response getMeasures()
		throws SQLException
	{
		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			return Response.ok(context.selectFrom(MEASURES)
									  .orderBy(MEASURES.NAME)
									  .fetchInto(Measures.class))
						   .build();
		}
	}

	@GET
	@Path("/{measureId:\\d+}/img")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({"image/png", "image/jpeg", "image/*"})
	public Response getMeasureImage(@PathParam("measureId") Integer measureId, @QueryParam("imageToken") String imageToken)
		throws SQLException
	{
		// Check the image token
		if (!AuthenticationFilter.isValidImageToken(imageToken))
			return Response.status(Response.Status.FORBIDDEN).build();

		try (Connection conn = Database.getConnection())
		{
			DSLContext context = Database.getContext(conn);

			MeasuresRecord measure = context.selectFrom(MEASURES).where(MEASURES.ID.eq(measureId)).fetchAny();

			if (measure == null || measure.getImage() == null)
				return Response.status(Response.Status.NOT_FOUND).build();
			else
				return Response.ok((StreamingOutput) output -> {
								   output.write(measure.getImage());
								   output.flush();
							   })
							   .type("image/png")
							   .build();
		}
	}
}
