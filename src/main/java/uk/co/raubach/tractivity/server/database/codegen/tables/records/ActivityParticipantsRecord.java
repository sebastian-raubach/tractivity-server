/*
 * This file is generated by jOOQ.
 */
package uk.co.raubach.tractivity.server.database.codegen.tables.records;


import java.sql.Timestamp;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;

import uk.co.raubach.tractivity.server.database.codegen.tables.ActivityParticipants;


// @formatter:off
/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ActivityParticipantsRecord extends UpdatableRecordImpl<ActivityParticipantsRecord> implements Record4<Integer, Integer, Timestamp, Timestamp> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>tractivity_db.activity_participants.activity_id</code>.
     */
    public void setActivityId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>tractivity_db.activity_participants.activity_id</code>.
     */
    public Integer getActivityId() {
        return (Integer) get(0);
    }

    /**
     * Setter for
     * <code>tractivity_db.activity_participants.participant_id</code>.
     */
    public void setParticipantId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for
     * <code>tractivity_db.activity_participants.participant_id</code>.
     */
    public Integer getParticipantId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>tractivity_db.activity_participants.created_on</code>.
     */
    public void setCreatedOn(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>tractivity_db.activity_participants.created_on</code>.
     */
    public Timestamp getCreatedOn() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>tractivity_db.activity_participants.updated_on</code>.
     */
    public void setUpdatedOn(Timestamp value) {
        set(3, value);
    }

    /**
     * Getter for <code>tractivity_db.activity_participants.updated_on</code>.
     */
    public Timestamp getUpdatedOn() {
        return (Timestamp) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Integer, Integer> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, Integer, Timestamp, Timestamp> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, Integer, Timestamp, Timestamp> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return ActivityParticipants.ACTIVITY_PARTICIPANTS.ACTIVITY_ID;
    }

    @Override
    public Field<Integer> field2() {
        return ActivityParticipants.ACTIVITY_PARTICIPANTS.PARTICIPANT_ID;
    }

    @Override
    public Field<Timestamp> field3() {
        return ActivityParticipants.ACTIVITY_PARTICIPANTS.CREATED_ON;
    }

    @Override
    public Field<Timestamp> field4() {
        return ActivityParticipants.ACTIVITY_PARTICIPANTS.UPDATED_ON;
    }

    @Override
    public Integer component1() {
        return getActivityId();
    }

    @Override
    public Integer component2() {
        return getParticipantId();
    }

    @Override
    public Timestamp component3() {
        return getCreatedOn();
    }

    @Override
    public Timestamp component4() {
        return getUpdatedOn();
    }

    @Override
    public Integer value1() {
        return getActivityId();
    }

    @Override
    public Integer value2() {
        return getParticipantId();
    }

    @Override
    public Timestamp value3() {
        return getCreatedOn();
    }

    @Override
    public Timestamp value4() {
        return getUpdatedOn();
    }

    @Override
    public ActivityParticipantsRecord value1(Integer value) {
        setActivityId(value);
        return this;
    }

    @Override
    public ActivityParticipantsRecord value2(Integer value) {
        setParticipantId(value);
        return this;
    }

    @Override
    public ActivityParticipantsRecord value3(Timestamp value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    public ActivityParticipantsRecord value4(Timestamp value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    public ActivityParticipantsRecord values(Integer value1, Integer value2, Timestamp value3, Timestamp value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ActivityParticipantsRecord
     */
    public ActivityParticipantsRecord() {
        super(ActivityParticipants.ACTIVITY_PARTICIPANTS);
    }

    /**
     * Create a detached, initialised ActivityParticipantsRecord
     */
    public ActivityParticipantsRecord(Integer activityId, Integer participantId, Timestamp createdOn, Timestamp updatedOn) {
        super(ActivityParticipants.ACTIVITY_PARTICIPANTS);

        setActivityId(activityId);
        setParticipantId(participantId);
        setCreatedOn(createdOn);
        setUpdatedOn(updatedOn);
    }

    /**
     * Create a detached, initialised ActivityParticipantsRecord
     */
    public ActivityParticipantsRecord(uk.co.raubach.tractivity.server.database.codegen.tables.pojos.ActivityParticipants value) {
        super(ActivityParticipants.ACTIVITY_PARTICIPANTS);

        if (value != null) {
            setActivityId(value.getActivityId());
            setParticipantId(value.getParticipantId());
            setCreatedOn(value.getCreatedOn());
            setUpdatedOn(value.getUpdatedOn());
        }
    }
    // @formatter:on
}
