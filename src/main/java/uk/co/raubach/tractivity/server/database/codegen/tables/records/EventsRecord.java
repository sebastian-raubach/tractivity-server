/*
 * This file is generated by jOOQ.
 */
package uk.co.raubach.tractivity.server.database.codegen.tables.records;


import java.sql.Timestamp;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;

import uk.co.raubach.tractivity.server.database.codegen.tables.Events;


// @formatter:off
/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EventsRecord extends UpdatableRecordImpl<EventsRecord> implements Record4<Integer, String, Timestamp, Timestamp> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>tractivity_db.events.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>tractivity_db.events.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>tractivity_db.events.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>tractivity_db.events.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>tractivity_db.events.created_on</code>.
     */
    public void setCreatedOn(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>tractivity_db.events.created_on</code>.
     */
    public Timestamp getCreatedOn() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>tractivity_db.events.updated_on</code>.
     */
    public void setUpdatedOn(Timestamp value) {
        set(3, value);
    }

    /**
     * Getter for <code>tractivity_db.events.updated_on</code>.
     */
    public Timestamp getUpdatedOn() {
        return (Timestamp) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, Timestamp, Timestamp> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, String, Timestamp, Timestamp> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Events.EVENTS.ID;
    }

    @Override
    public Field<String> field2() {
        return Events.EVENTS.NAME;
    }

    @Override
    public Field<Timestamp> field3() {
        return Events.EVENTS.CREATED_ON;
    }

    @Override
    public Field<Timestamp> field4() {
        return Events.EVENTS.UPDATED_ON;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
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
        return getId();
    }

    @Override
    public String value2() {
        return getName();
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
    public EventsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public EventsRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public EventsRecord value3(Timestamp value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    public EventsRecord value4(Timestamp value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    public EventsRecord values(Integer value1, String value2, Timestamp value3, Timestamp value4) {
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
     * Create a detached EventsRecord
     */
    public EventsRecord() {
        super(Events.EVENTS);
    }

    /**
     * Create a detached, initialised EventsRecord
     */
    public EventsRecord(Integer id, String name, Timestamp createdOn, Timestamp updatedOn) {
        super(Events.EVENTS);

        setId(id);
        setName(name);
        setCreatedOn(createdOn);
        setUpdatedOn(updatedOn);
    }

    /**
     * Create a detached, initialised EventsRecord
     */
    public EventsRecord(uk.co.raubach.tractivity.server.database.codegen.tables.pojos.Events value) {
        super(Events.EVENTS);

        if (value != null) {
            setId(value.getId());
            setName(value.getName());
            setCreatedOn(value.getCreatedOn());
            setUpdatedOn(value.getUpdatedOn());
        }
    }
    // @formatter:on
}
