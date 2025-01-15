/*
 * This file is generated by jOOQ.
 */
package uk.co.raubach.tractivity.server.database.codegen.tables.records;


import java.sql.Timestamp;

import org.jooq.Field;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.TableRecordImpl;

import uk.co.raubach.tractivity.server.database.codegen.tables.ViewActivityTypes;


// @formatter:off
/**
 * VIEW
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ViewActivityTypesRecord extends TableRecordImpl<ViewActivityTypesRecord> implements Record6<Integer, String, byte[], Timestamp, Timestamp, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>tractivity_db.view_activity_types.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>tractivity_db.view_activity_types.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>tractivity_db.view_activity_types.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>tractivity_db.view_activity_types.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>tractivity_db.view_activity_types.image</code>.
     */
    public void setImage(byte[] value) {
        set(2, value);
    }

    /**
     * Getter for <code>tractivity_db.view_activity_types.image</code>.
     */
    public byte[] getImage() {
        return (byte[]) get(2);
    }

    /**
     * Setter for <code>tractivity_db.view_activity_types.created_on</code>.
     */
    public void setCreatedOn(Timestamp value) {
        set(3, value);
    }

    /**
     * Getter for <code>tractivity_db.view_activity_types.created_on</code>.
     */
    public Timestamp getCreatedOn() {
        return (Timestamp) get(3);
    }

    /**
     * Setter for <code>tractivity_db.view_activity_types.updated_on</code>.
     */
    public void setUpdatedOn(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>tractivity_db.view_activity_types.updated_on</code>.
     */
    public Timestamp getUpdatedOn() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>tractivity_db.view_activity_types.count</code>.
     */
    public void setCount(Long value) {
        set(5, value);
    }

    /**
     * Getter for <code>tractivity_db.view_activity_types.count</code>.
     */
    public Long getCount() {
        return (Long) get(5);
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, byte[], Timestamp, Timestamp, Long> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Integer, String, byte[], Timestamp, Timestamp, Long> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return ViewActivityTypes.VIEW_ACTIVITY_TYPES.ID;
    }

    @Override
    public Field<String> field2() {
        return ViewActivityTypes.VIEW_ACTIVITY_TYPES.NAME;
    }

    @Override
    public Field<byte[]> field3() {
        return ViewActivityTypes.VIEW_ACTIVITY_TYPES.IMAGE;
    }

    @Override
    public Field<Timestamp> field4() {
        return ViewActivityTypes.VIEW_ACTIVITY_TYPES.CREATED_ON;
    }

    @Override
    public Field<Timestamp> field5() {
        return ViewActivityTypes.VIEW_ACTIVITY_TYPES.UPDATED_ON;
    }

    @Override
    public Field<Long> field6() {
        return ViewActivityTypes.VIEW_ACTIVITY_TYPES.COUNT;
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
    public byte[] component3() {
        return getImage();
    }

    @Override
    public Timestamp component4() {
        return getCreatedOn();
    }

    @Override
    public Timestamp component5() {
        return getUpdatedOn();
    }

    @Override
    public Long component6() {
        return getCount();
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
    public byte[] value3() {
        return getImage();
    }

    @Override
    public Timestamp value4() {
        return getCreatedOn();
    }

    @Override
    public Timestamp value5() {
        return getUpdatedOn();
    }

    @Override
    public Long value6() {
        return getCount();
    }

    @Override
    public ViewActivityTypesRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public ViewActivityTypesRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public ViewActivityTypesRecord value3(byte[] value) {
        setImage(value);
        return this;
    }

    @Override
    public ViewActivityTypesRecord value4(Timestamp value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    public ViewActivityTypesRecord value5(Timestamp value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    public ViewActivityTypesRecord value6(Long value) {
        setCount(value);
        return this;
    }

    @Override
    public ViewActivityTypesRecord values(Integer value1, String value2, byte[] value3, Timestamp value4, Timestamp value5, Long value6) {
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
     * Create a detached ViewActivityTypesRecord
     */
    public ViewActivityTypesRecord() {
        super(ViewActivityTypes.VIEW_ACTIVITY_TYPES);
    }

    /**
     * Create a detached, initialised ViewActivityTypesRecord
     */
    public ViewActivityTypesRecord(Integer id, String name, byte[] image, Timestamp createdOn, Timestamp updatedOn, Long count) {
        super(ViewActivityTypes.VIEW_ACTIVITY_TYPES);

        setId(id);
        setName(name);
        setImage(image);
        setCreatedOn(createdOn);
        setUpdatedOn(updatedOn);
        setCount(count);
    }

    /**
     * Create a detached, initialised ViewActivityTypesRecord
     */
    public ViewActivityTypesRecord(uk.co.raubach.tractivity.server.database.codegen.tables.pojos.ViewActivityTypes value) {
        super(ViewActivityTypes.VIEW_ACTIVITY_TYPES);

        if (value != null) {
            setId(value.getId());
            setName(value.getName());
            setImage(value.getImage());
            setCreatedOn(value.getCreatedOn());
            setUpdatedOn(value.getUpdatedOn());
            setCount(value.getCount());
        }
    }
    // @formatter:on
}
