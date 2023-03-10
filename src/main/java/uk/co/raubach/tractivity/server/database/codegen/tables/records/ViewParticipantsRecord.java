/*
 * This file is generated by jOOQ.
 */
package uk.co.raubach.tractivity.server.database.codegen.tables.records;


import java.sql.Timestamp;
import java.util.Map;

import org.jooq.Field;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.TableRecordImpl;

import uk.co.raubach.tractivity.server.database.codegen.enums.ViewParticipantsParticipantGender;
import uk.co.raubach.tractivity.server.database.codegen.tables.ViewParticipants;


// @formatter:off
/**
 * VIEW
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ViewParticipantsRecord extends TableRecordImpl<ViewParticipantsRecord> implements Record6<Integer, String, Timestamp, ViewParticipantsParticipantGender, Timestamp, Map<String, Integer>> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>tractivity_db.view_participants.participant_id</code>.
     */
    public void setParticipantId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>tractivity_db.view_participants.participant_id</code>.
     */
    public Integer getParticipantId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>tractivity_db.view_participants.participant_name</code>.
     */
    public void setParticipantName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>tractivity_db.view_participants.participant_name</code>.
     */
    public String getParticipantName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>tractivity_db.view_participants.participant_dob</code>.
     */
    public void setParticipantDob(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>tractivity_db.view_participants.participant_dob</code>.
     */
    public Timestamp getParticipantDob() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for
     * <code>tractivity_db.view_participants.participant_gender</code>.
     */
    public void setParticipantGender(ViewParticipantsParticipantGender value) {
        set(3, value);
    }

    /**
     * Getter for
     * <code>tractivity_db.view_participants.participant_gender</code>.
     */
    public ViewParticipantsParticipantGender getParticipantGender() {
        return (ViewParticipantsParticipantGender) get(3);
    }

    /**
     * Setter for <code>tractivity_db.view_participants.created_on</code>.
     */
    public void setCreatedOn(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>tractivity_db.view_participants.created_on</code>.
     */
    public Timestamp getCreatedOn() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>tractivity_db.view_participants.activity_counts</code>.
     */
    public void setActivityCounts(Map<String, Integer> value) {
        set(5, value);
    }

    /**
     * Getter for <code>tractivity_db.view_participants.activity_counts</code>.
     */
    public Map<String, Integer> getActivityCounts() {
        return (Map<String, Integer>) get(5);
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, Timestamp, ViewParticipantsParticipantGender, Timestamp, Map<String, Integer>> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Integer, String, Timestamp, ViewParticipantsParticipantGender, Timestamp, Map<String, Integer>> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return ViewParticipants.VIEW_PARTICIPANTS.PARTICIPANT_ID;
    }

    @Override
    public Field<String> field2() {
        return ViewParticipants.VIEW_PARTICIPANTS.PARTICIPANT_NAME;
    }

    @Override
    public Field<Timestamp> field3() {
        return ViewParticipants.VIEW_PARTICIPANTS.PARTICIPANT_DOB;
    }

    @Override
    public Field<ViewParticipantsParticipantGender> field4() {
        return ViewParticipants.VIEW_PARTICIPANTS.PARTICIPANT_GENDER;
    }

    @Override
    public Field<Timestamp> field5() {
        return ViewParticipants.VIEW_PARTICIPANTS.CREATED_ON;
    }

    @Override
    public Field<Map<String, Integer>> field6() {
        return ViewParticipants.VIEW_PARTICIPANTS.ACTIVITY_COUNTS;
    }

    @Override
    public Integer component1() {
        return getParticipantId();
    }

    @Override
    public String component2() {
        return getParticipantName();
    }

    @Override
    public Timestamp component3() {
        return getParticipantDob();
    }

    @Override
    public ViewParticipantsParticipantGender component4() {
        return getParticipantGender();
    }

    @Override
    public Timestamp component5() {
        return getCreatedOn();
    }

    @Override
    public Map<String, Integer> component6() {
        return getActivityCounts();
    }

    @Override
    public Integer value1() {
        return getParticipantId();
    }

    @Override
    public String value2() {
        return getParticipantName();
    }

    @Override
    public Timestamp value3() {
        return getParticipantDob();
    }

    @Override
    public ViewParticipantsParticipantGender value4() {
        return getParticipantGender();
    }

    @Override
    public Timestamp value5() {
        return getCreatedOn();
    }

    @Override
    public Map<String, Integer> value6() {
        return getActivityCounts();
    }

    @Override
    public ViewParticipantsRecord value1(Integer value) {
        setParticipantId(value);
        return this;
    }

    @Override
    public ViewParticipantsRecord value2(String value) {
        setParticipantName(value);
        return this;
    }

    @Override
    public ViewParticipantsRecord value3(Timestamp value) {
        setParticipantDob(value);
        return this;
    }

    @Override
    public ViewParticipantsRecord value4(ViewParticipantsParticipantGender value) {
        setParticipantGender(value);
        return this;
    }

    @Override
    public ViewParticipantsRecord value5(Timestamp value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    public ViewParticipantsRecord value6(Map<String, Integer> value) {
        setActivityCounts(value);
        return this;
    }

    @Override
    public ViewParticipantsRecord values(Integer value1, String value2, Timestamp value3, ViewParticipantsParticipantGender value4, Timestamp value5, Map<String, Integer> value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ViewParticipantsRecord
     */
    public ViewParticipantsRecord() {
        super(ViewParticipants.VIEW_PARTICIPANTS);
    }

    /**
     * Create a detached, initialised ViewParticipantsRecord
     */
    public ViewParticipantsRecord(Integer participantId, String participantName, Timestamp participantDob, ViewParticipantsParticipantGender participantGender, Timestamp createdOn, Map<String, Integer> activityCounts) {
        super(ViewParticipants.VIEW_PARTICIPANTS);

        setParticipantId(participantId);
        setParticipantName(participantName);
        setParticipantDob(participantDob);
        setParticipantGender(participantGender);
        setCreatedOn(createdOn);
        setActivityCounts(activityCounts);
    }

    /**
     * Create a detached, initialised ViewParticipantsRecord
     */
    public ViewParticipantsRecord(uk.co.raubach.tractivity.server.database.codegen.tables.pojos.ViewParticipants value) {
        super(ViewParticipants.VIEW_PARTICIPANTS);

        if (value != null) {
            setParticipantId(value.getParticipantId());
            setParticipantName(value.getParticipantName());
            setParticipantDob(value.getParticipantDob());
            setParticipantGender(value.getParticipantGender());
            setCreatedOn(value.getCreatedOn());
            setActivityCounts(value.getActivityCounts());
        }
    }
    // @formatter:on
}
