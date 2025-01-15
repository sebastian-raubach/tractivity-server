/*
 * This file is generated by jOOQ.
 */
package uk.co.raubach.tractivity.server.database.codegen.tables;


import java.sql.Timestamp;

import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Row10;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import uk.co.raubach.tractivity.server.database.binding.SimpleParticipantBinding;
import uk.co.raubach.tractivity.server.database.codegen.TractivityDb;
import uk.co.raubach.tractivity.server.database.codegen.tables.records.ViewActivitiesRecord;
import uk.co.raubach.tractivity.server.pojo.SimpleParticipant;


// @formatter:off
/**
 * VIEW
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ViewActivities extends TableImpl<ViewActivitiesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>tractivity_db.view_activities</code>
     */
    public static final ViewActivities VIEW_ACTIVITIES = new ViewActivities();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ViewActivitiesRecord> getRecordType() {
        return ViewActivitiesRecord.class;
    }

    /**
     * The column <code>tractivity_db.view_activities.activity_id</code>.
     */
    public final TableField<ViewActivitiesRecord, Integer> ACTIVITY_ID = createField(DSL.name("activity_id"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.inline("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>tractivity_db.view_activities.activity_type_id</code>.
     */
    public final TableField<ViewActivitiesRecord, Integer> ACTIVITY_TYPE_ID = createField(DSL.name("activity_type_id"), SQLDataType.INTEGER.defaultValue(DSL.inline("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>tractivity_db.view_activities.activity_type_name</code>.
     */
    public final TableField<ViewActivitiesRecord, String> ACTIVITY_TYPE_NAME = createField(DSL.name("activity_type_name"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column
     * <code>tractivity_db.view_activities.activity_created_on</code>.
     */
    public final TableField<ViewActivitiesRecord, Timestamp> ACTIVITY_CREATED_ON = createField(DSL.name("activity_created_on"), SQLDataType.TIMESTAMP(0).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>tractivity_db.view_activities.location_id</code>.
     */
    public final TableField<ViewActivitiesRecord, Integer> LOCATION_ID = createField(DSL.name("location_id"), SQLDataType.INTEGER.defaultValue(DSL.inline("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>tractivity_db.view_activities.location_name</code>.
     */
    public final TableField<ViewActivitiesRecord, String> LOCATION_NAME = createField(DSL.name("location_name"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>tractivity_db.view_activities.event_id</code>.
     */
    public final TableField<ViewActivitiesRecord, Integer> EVENT_ID = createField(DSL.name("event_id"), SQLDataType.INTEGER.defaultValue(DSL.inline("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>tractivity_db.view_activities.event_name</code>.
     */
    public final TableField<ViewActivitiesRecord, String> EVENT_NAME = createField(DSL.name("event_name"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>tractivity_db.view_activities.event_created_on</code>.
     */
    public final TableField<ViewActivitiesRecord, Timestamp> EVENT_CREATED_ON = createField(DSL.name("event_created_on"), SQLDataType.TIMESTAMP(0).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>tractivity_db.view_activities.participants</code>.
     */
    public final TableField<ViewActivitiesRecord, SimpleParticipant[]> PARTICIPANTS = createField(DSL.name("participants"), SQLDataType.JSON, this, "", new SimpleParticipantBinding());

    private ViewActivities(Name alias, Table<ViewActivitiesRecord> aliased) {
        this(alias, aliased, null);
    }

    private ViewActivities(Name alias, Table<ViewActivitiesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("VIEW"), TableOptions.view("create view `view_activities` as select `tractivity`.`activities`.`id` AS `activity_id`,`tractivity`.`activity_types`.`id` AS `activity_type_id`,`tractivity`.`activity_types`.`name` AS `activity_type_name`,`tractivity`.`activities`.`created_on` AS `activity_created_on`,`tractivity`.`locations`.`id` AS `location_id`,`tractivity`.`locations`.`name` AS `location_name`,`tractivity`.`events`.`id` AS `event_id`,`tractivity`.`events`.`name` AS `event_name`,`tractivity`.`events`.`created_on` AS `event_created_on`,json_arrayagg(json_object('participantId',`tractivity`.`participants`.`id`,'participantName',`tractivity`.`participants`.`name`,'participantDob',`tractivity`.`participants`.`dob`,'participantGender',`tractivity`.`participants`.`gender`)) AS `participants` from (((((`tractivity`.`activities` left join `tractivity`.`activity_types` on((`tractivity`.`activity_types`.`id` = `tractivity`.`activities`.`activity_type_id`))) left join `tractivity`.`events` on((`tractivity`.`events`.`id` = `tractivity`.`activities`.`event_id`))) left join `tractivity`.`activity_participants` on((`tractivity`.`activity_participants`.`activity_id` = `tractivity`.`activities`.`id`))) left join `tractivity`.`participants` on((`tractivity`.`participants`.`id` = `tractivity`.`activity_participants`.`participant_id`))) left join `tractivity`.`locations` on((`tractivity`.`locations`.`id` = `tractivity`.`activities`.`location_id`))) group by `tractivity`.`activities`.`id`"));
    }

    /**
     * Create an aliased <code>tractivity_db.view_activities</code> table
     * reference
     */
    public ViewActivities(String alias) {
        this(DSL.name(alias), VIEW_ACTIVITIES);
    }

    /**
     * Create an aliased <code>tractivity_db.view_activities</code> table
     * reference
     */
    public ViewActivities(Name alias) {
        this(alias, VIEW_ACTIVITIES);
    }

    /**
     * Create a <code>tractivity_db.view_activities</code> table reference
     */
    public ViewActivities() {
        this(DSL.name("view_activities"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : TractivityDb.TRACTIVITY_DB;
    }

    @Override
    public ViewActivities as(String alias) {
        return new ViewActivities(DSL.name(alias), this);
    }

    @Override
    public ViewActivities as(Name alias) {
        return new ViewActivities(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ViewActivities rename(String name) {
        return new ViewActivities(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ViewActivities rename(Name name) {
        return new ViewActivities(name, null);
    }

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row10<Integer, Integer, String, Timestamp, Integer, String, Integer, String, Timestamp, SimpleParticipant[]> fieldsRow() {
        return (Row10) super.fieldsRow();
    }
    // @formatter:on
}
