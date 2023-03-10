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
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import uk.co.raubach.tractivity.server.database.codegen.TractivityDb;
import uk.co.raubach.tractivity.server.database.codegen.tables.records.ActivityParticipantsRecord;


// @formatter:off
/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ActivityParticipants extends TableImpl<ActivityParticipantsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>tractivity_db.activity_participants</code>
     */
    public static final ActivityParticipants ACTIVITY_PARTICIPANTS = new ActivityParticipants();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ActivityParticipantsRecord> getRecordType() {
        return ActivityParticipantsRecord.class;
    }

    /**
     * The column <code>tractivity_db.activity_participants.activity_id</code>.
     */
    public final TableField<ActivityParticipantsRecord, Integer> ACTIVITY_ID = createField(DSL.name("activity_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column
     * <code>tractivity_db.activity_participants.participant_id</code>.
     */
    public final TableField<ActivityParticipantsRecord, Integer> PARTICIPANT_ID = createField(DSL.name("participant_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>tractivity_db.activity_participants.created_on</code>.
     */
    public final TableField<ActivityParticipantsRecord, Timestamp> CREATED_ON = createField(DSL.name("created_on"), SQLDataType.TIMESTAMP(0).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>tractivity_db.activity_participants.updated_on</code>.
     */
    public final TableField<ActivityParticipantsRecord, Timestamp> UPDATED_ON = createField(DSL.name("updated_on"), SQLDataType.TIMESTAMP(0).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.TIMESTAMP)), this, "");

    private ActivityParticipants(Name alias, Table<ActivityParticipantsRecord> aliased) {
        this(alias, aliased, null);
    }

    private ActivityParticipants(Name alias, Table<ActivityParticipantsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>tractivity_db.activity_participants</code> table
     * reference
     */
    public ActivityParticipants(String alias) {
        this(DSL.name(alias), ACTIVITY_PARTICIPANTS);
    }

    /**
     * Create an aliased <code>tractivity_db.activity_participants</code> table
     * reference
     */
    public ActivityParticipants(Name alias) {
        this(alias, ACTIVITY_PARTICIPANTS);
    }

    /**
     * Create a <code>tractivity_db.activity_participants</code> table reference
     */
    public ActivityParticipants() {
        this(DSL.name("activity_participants"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : TractivityDb.TRACTIVITY_DB;
    }

    @Override
    public UniqueKey<ActivityParticipantsRecord> getPrimaryKey() {
        return Internal.createUniqueKey(ActivityParticipants.ACTIVITY_PARTICIPANTS, DSL.name("KEY_activity_participants_PRIMARY"), new TableField[] { ActivityParticipants.ACTIVITY_PARTICIPANTS.ACTIVITY_ID, ActivityParticipants.ACTIVITY_PARTICIPANTS.PARTICIPANT_ID }, true);
    }

    @Override
    public ActivityParticipants as(String alias) {
        return new ActivityParticipants(DSL.name(alias), this);
    }

    @Override
    public ActivityParticipants as(Name alias) {
        return new ActivityParticipants(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ActivityParticipants rename(String name) {
        return new ActivityParticipants(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ActivityParticipants rename(Name name) {
        return new ActivityParticipants(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, Integer, Timestamp, Timestamp> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
    // @formatter:on
}
