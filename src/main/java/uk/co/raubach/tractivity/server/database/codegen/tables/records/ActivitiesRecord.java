/*
 * This file is generated by jOOQ.
 */
package uk.co.raubach.tractivity.server.database.codegen.tables.records;


import java.sql.Timestamp;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;

import uk.co.raubach.tractivity.server.database.codegen.tables.Activities;


// @formatter:off
/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ActivitiesRecord extends UpdatableRecordImpl<ActivitiesRecord> implements Record6<Integer, Integer, Integer, Integer, Timestamp, Timestamp> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>tractivity_db.activities.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>tractivity_db.activities.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>tractivity_db.activities.activity_type_id</code>.
     */
    public void setActivityTypeId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>tractivity_db.activities.activity_type_id</code>.
     */
    public Integer getActivityTypeId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>tractivity_db.activities.event_id</code>.
     */
    public void setEventId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>tractivity_db.activities.event_id</code>.
     */
    public Integer getEventId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>tractivity_db.activities.location_id</code>.
     */
    public void setLocationId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>tractivity_db.activities.location_id</code>.
     */
    public Integer getLocationId() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>tractivity_db.activities.created_on</code>.
     */
    public void setCreatedOn(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>tractivity_db.activities.created_on</code>.
     */
    public Timestamp getCreatedOn() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>tractivity_db.activities.updated_on</code>.
     */
    public void setUpdatedOn(Timestamp value) {
        set(5, value);
    }

    /**
     * Getter for <code>tractivity_db.activities.updated_on</code>.
     */
    public Timestamp getUpdatedOn() {
        return (Timestamp) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, Integer, Integer, Integer, Timestamp, Timestamp> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Integer, Integer, Integer, Integer, Timestamp, Timestamp> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Activities.ACTIVITIES.ID;
    }

    @Override
    public Field<Integer> field2() {
        return Activities.ACTIVITIES.ACTIVITY_TYPE_ID;
    }

    @Override
    public Field<Integer> field3() {
        return Activities.ACTIVITIES.EVENT_ID;
    }

    @Override
    public Field<Integer> field4() {
        return Activities.ACTIVITIES.LOCATION_ID;
    }

    @Override
    public Field<Timestamp> field5() {
        return Activities.ACTIVITIES.CREATED_ON;
    }

    @Override
    public Field<Timestamp> field6() {
        return Activities.ACTIVITIES.UPDATED_ON;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getActivityTypeId();
    }

    @Override
    public Integer component3() {
        return getEventId();
    }

    @Override
    public Integer component4() {
        return getLocationId();
    }

    @Override
    public Timestamp component5() {
        return getCreatedOn();
    }

    @Override
    public Timestamp component6() {
        return getUpdatedOn();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getActivityTypeId();
    }

    @Override
    public Integer value3() {
        return getEventId();
    }

    @Override
    public Integer value4() {
        return getLocationId();
    }

    @Override
    public Timestamp value5() {
        return getCreatedOn();
    }

    @Override
    public Timestamp value6() {
        return getUpdatedOn();
    }

    @Override
    public ActivitiesRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public ActivitiesRecord value2(Integer value) {
        setActivityTypeId(value);
        return this;
    }

    @Override
    public ActivitiesRecord value3(Integer value) {
        setEventId(value);
        return this;
    }

    @Override
    public ActivitiesRecord value4(Integer value) {
        setLocationId(value);
        return this;
    }

    @Override
    public ActivitiesRecord value5(Timestamp value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    public ActivitiesRecord value6(Timestamp value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    public ActivitiesRecord values(Integer value1, Integer value2, Integer value3, Integer value4, Timestamp value5, Timestamp value6) {
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
     * Create a detached ActivitiesRecord
     */
    public ActivitiesRecord() {
        super(Activities.ACTIVITIES);
    }

    /**
     * Create a detached, initialised ActivitiesRecord
     */
    public ActivitiesRecord(Integer id, Integer activityTypeId, Integer eventId, Integer locationId, Timestamp createdOn, Timestamp updatedOn) {
        super(Activities.ACTIVITIES);

        setId(id);
        setActivityTypeId(activityTypeId);
        setEventId(eventId);
        setLocationId(locationId);
        setCreatedOn(createdOn);
        setUpdatedOn(updatedOn);
    }

    /**
     * Create a detached, initialised ActivitiesRecord
     */
    public ActivitiesRecord(uk.co.raubach.tractivity.server.database.codegen.tables.pojos.Activities value) {
        super(Activities.ACTIVITIES);

        if (value != null) {
            setId(value.getId());
            setActivityTypeId(value.getActivityTypeId());
            setEventId(value.getEventId());
            setLocationId(value.getLocationId());
            setCreatedOn(value.getCreatedOn());
            setUpdatedOn(value.getUpdatedOn());
        }
    }
    // @formatter:on
}
