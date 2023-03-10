/*
 * This file is generated by jOOQ.
 */
package uk.co.raubach.tractivity.server.database.codegen.tables.records;


import java.sql.Timestamp;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;

import uk.co.raubach.tractivity.server.database.codegen.enums.ParticipantsGender;
import uk.co.raubach.tractivity.server.database.codegen.tables.Participants;


// @formatter:off
/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ParticipantsRecord extends UpdatableRecordImpl<ParticipantsRecord> implements Record7<Integer, String, Timestamp, ParticipantsGender, byte[], Timestamp, Timestamp> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>tractivity_db.participants.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>tractivity_db.participants.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>tractivity_db.participants.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>tractivity_db.participants.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>tractivity_db.participants.dob</code>.
     */
    public void setDob(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>tractivity_db.participants.dob</code>.
     */
    public Timestamp getDob() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>tractivity_db.participants.gender</code>.
     */
    public void setGender(ParticipantsGender value) {
        set(3, value);
    }

    /**
     * Getter for <code>tractivity_db.participants.gender</code>.
     */
    public ParticipantsGender getGender() {
        return (ParticipantsGender) get(3);
    }

    /**
     * Setter for <code>tractivity_db.participants.image</code>.
     */
    public void setImage(byte[] value) {
        set(4, value);
    }

    /**
     * Getter for <code>tractivity_db.participants.image</code>.
     */
    public byte[] getImage() {
        return (byte[]) get(4);
    }

    /**
     * Setter for <code>tractivity_db.participants.created_on</code>.
     */
    public void setCreatedOn(Timestamp value) {
        set(5, value);
    }

    /**
     * Getter for <code>tractivity_db.participants.created_on</code>.
     */
    public Timestamp getCreatedOn() {
        return (Timestamp) get(5);
    }

    /**
     * Setter for <code>tractivity_db.participants.updated_on</code>.
     */
    public void setUpdatedOn(Timestamp value) {
        set(6, value);
    }

    /**
     * Getter for <code>tractivity_db.participants.updated_on</code>.
     */
    public Timestamp getUpdatedOn() {
        return (Timestamp) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, String, Timestamp, ParticipantsGender, byte[], Timestamp, Timestamp> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<Integer, String, Timestamp, ParticipantsGender, byte[], Timestamp, Timestamp> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Participants.PARTICIPANTS.ID;
    }

    @Override
    public Field<String> field2() {
        return Participants.PARTICIPANTS.NAME;
    }

    @Override
    public Field<Timestamp> field3() {
        return Participants.PARTICIPANTS.DOB;
    }

    @Override
    public Field<ParticipantsGender> field4() {
        return Participants.PARTICIPANTS.GENDER;
    }

    @Override
    public Field<byte[]> field5() {
        return Participants.PARTICIPANTS.IMAGE;
    }

    @Override
    public Field<Timestamp> field6() {
        return Participants.PARTICIPANTS.CREATED_ON;
    }

    @Override
    public Field<Timestamp> field7() {
        return Participants.PARTICIPANTS.UPDATED_ON;
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
        return getDob();
    }

    @Override
    public ParticipantsGender component4() {
        return getGender();
    }

    @Override
    public byte[] component5() {
        return getImage();
    }

    @Override
    public Timestamp component6() {
        return getCreatedOn();
    }

    @Override
    public Timestamp component7() {
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
        return getDob();
    }

    @Override
    public ParticipantsGender value4() {
        return getGender();
    }

    @Override
    public byte[] value5() {
        return getImage();
    }

    @Override
    public Timestamp value6() {
        return getCreatedOn();
    }

    @Override
    public Timestamp value7() {
        return getUpdatedOn();
    }

    @Override
    public ParticipantsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public ParticipantsRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public ParticipantsRecord value3(Timestamp value) {
        setDob(value);
        return this;
    }

    @Override
    public ParticipantsRecord value4(ParticipantsGender value) {
        setGender(value);
        return this;
    }

    @Override
    public ParticipantsRecord value5(byte[] value) {
        setImage(value);
        return this;
    }

    @Override
    public ParticipantsRecord value6(Timestamp value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    public ParticipantsRecord value7(Timestamp value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    public ParticipantsRecord values(Integer value1, String value2, Timestamp value3, ParticipantsGender value4, byte[] value5, Timestamp value6, Timestamp value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ParticipantsRecord
     */
    public ParticipantsRecord() {
        super(Participants.PARTICIPANTS);
    }

    /**
     * Create a detached, initialised ParticipantsRecord
     */
    public ParticipantsRecord(Integer id, String name, Timestamp dob, ParticipantsGender gender, byte[] image, Timestamp createdOn, Timestamp updatedOn) {
        super(Participants.PARTICIPANTS);

        setId(id);
        setName(name);
        setDob(dob);
        setGender(gender);
        setImage(image);
        setCreatedOn(createdOn);
        setUpdatedOn(updatedOn);
    }

    /**
     * Create a detached, initialised ParticipantsRecord
     */
    public ParticipantsRecord(uk.co.raubach.tractivity.server.database.codegen.tables.pojos.Participants value) {
        super(Participants.PARTICIPANTS);

        if (value != null) {
            setId(value.getId());
            setName(value.getName());
            setDob(value.getDob());
            setGender(value.getGender());
            setImage(value.getImage());
            setCreatedOn(value.getCreatedOn());
            setUpdatedOn(value.getUpdatedOn());
        }
    }
    // @formatter:on
}
