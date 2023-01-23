/*
 * This file is generated by jOOQ.
 */
package uk.co.raubach.tractivity.server.database.codegen.tables;


import java.sql.Timestamp;

import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import uk.co.raubach.tractivity.server.database.binding.SimpleActivityBinding;
import uk.co.raubach.tractivity.server.database.codegen.TractivityDb;
import uk.co.raubach.tractivity.server.database.codegen.tables.records.ViewEventsRecord;
import uk.co.raubach.tractivity.server.pojo.SimpleActivity;


// @formatter:off
/**
 * VIEW
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ViewEvents extends TableImpl<ViewEventsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>tractivity_db.view_events</code>
     */
    public static final ViewEvents VIEW_EVENTS = new ViewEvents();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ViewEventsRecord> getRecordType() {
        return ViewEventsRecord.class;
    }

    /**
     * The column <code>tractivity_db.view_events.event_id</code>.
     */
    public final TableField<ViewEventsRecord, Integer> EVENT_ID = createField(DSL.name("event_id"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.inline("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>tractivity_db.view_events.event_name</code>.
     */
    public final TableField<ViewEventsRecord, String> EVENT_NAME = createField(DSL.name("event_name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>tractivity_db.view_events.event_created_on</code>.
     */
    public final TableField<ViewEventsRecord, Timestamp> EVENT_CREATED_ON = createField(DSL.name("event_created_on"), SQLDataType.TIMESTAMP(0), this, "");

    /**
     * The column <code>tractivity_db.view_events.activities</code>.
     */
    public final TableField<ViewEventsRecord, SimpleActivity[]> ACTIVITIES = createField(DSL.name("activities"), SQLDataType.JSON, this, "", new SimpleActivityBinding());

    private ViewEvents(Name alias, Table<ViewEventsRecord> aliased) {
        this(alias, aliased, null);
    }

    private ViewEvents(Name alias, Table<ViewEventsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("VIEW"), TableOptions.view("create view `view_events` as select `tractivity`.`events`.`id` AS `event_id`,`tractivity`.`events`.`name` AS `event_name`,`tractivity`.`events`.`created_on` AS `event_created_on`,if((count(`tractivity`.`activities`.`id`) = 0),json_array(),json_arrayagg(json_object('activityId',`tractivity`.`activities`.`id`,'activityTypeId',`tractivity`.`activity_types`.`id`,'activityTypeName',`tractivity`.`activity_types`.`name`,'activityParticipants',(select json_arrayagg(json_object('participantId',`tractivity`.`participants`.`id`,'participantName',`tractivity`.`participants`.`name`,'participantDob',`tractivity`.`participants`.`dob`,'participantGender',`tractivity`.`participants`.`gender`)) from (`tractivity`.`participants` left join `tractivity`.`activity_participants` on((`tractivity`.`participants`.`id` = `tractivity`.`activity_participants`.`participant_id`))) where (`tractivity`.`activity_participants`.`activity_id` = `tractivity`.`activities`.`id`))))) AS `activities` from ((`tractivity`.`events` left join `tractivity`.`activities` on((`tractivity`.`activities`.`event_id` = `tractivity`.`events`.`id`))) left join `tractivity`.`activity_types` on((`tractivity`.`activities`.`activity_type_id` = `tractivity`.`activity_types`.`id`))) group by `tractivity`.`events`.`id`"));
    }

    /**
     * Create an aliased <code>tractivity_db.view_events</code> table reference
     */
    public ViewEvents(String alias) {
        this(DSL.name(alias), VIEW_EVENTS);
    }

    /**
     * Create an aliased <code>tractivity_db.view_events</code> table reference
     */
    public ViewEvents(Name alias) {
        this(alias, VIEW_EVENTS);
    }

    /**
     * Create a <code>tractivity_db.view_events</code> table reference
     */
    public ViewEvents() {
        this(DSL.name("view_events"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : TractivityDb.TRACTIVITY_DB;
    }

    @Override
    public ViewEvents as(String alias) {
        return new ViewEvents(DSL.name(alias), this);
    }

    @Override
    public ViewEvents as(Name alias) {
        return new ViewEvents(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ViewEvents rename(String name) {
        return new ViewEvents(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ViewEvents rename(Name name) {
        return new ViewEvents(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, Timestamp, SimpleActivity[]> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
    // @formatter:on
}