/*
 * This file is generated by jOOQ.
 */
package uk.co.raubach.tractivity.server.database.codegen.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;


// @formatter:off
/**
 * VIEW
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ViewActivityTypes implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer   id;
    private String    name;
    private byte[]    image;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private Long      count;

    public ViewActivityTypes() {}

    public ViewActivityTypes(ViewActivityTypes value) {
        this.id = value.id;
        this.name = value.name;
        this.image = value.image;
        this.createdOn = value.createdOn;
        this.updatedOn = value.updatedOn;
        this.count = value.count;
    }

    public ViewActivityTypes(
        Integer   id,
        String    name,
        byte[]    image,
        Timestamp createdOn,
        Timestamp updatedOn,
        Long      count
    ) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.count = count;
    }

    /**
     * Getter for <code>tractivity_db.view_activity_types.id</code>.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Setter for <code>tractivity_db.view_activity_types.id</code>.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter for <code>tractivity_db.view_activity_types.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>tractivity_db.view_activity_types.name</code>.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>tractivity_db.view_activity_types.image</code>.
     */
    public byte[] getImage() {
        return this.image;
    }

    /**
     * Setter for <code>tractivity_db.view_activity_types.image</code>.
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * Getter for <code>tractivity_db.view_activity_types.created_on</code>.
     */
    public Timestamp getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for <code>tractivity_db.view_activity_types.created_on</code>.
     */
    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Getter for <code>tractivity_db.view_activity_types.updated_on</code>.
     */
    public Timestamp getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for <code>tractivity_db.view_activity_types.updated_on</code>.
     */
    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    /**
     * Getter for <code>tractivity_db.view_activity_types.count</code>.
     */
    public Long getCount() {
        return this.count;
    }

    /**
     * Setter for <code>tractivity_db.view_activity_types.count</code>.
     */
    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ViewActivityTypes (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append("[binary...]");
        sb.append(", ").append(createdOn);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(count);

        sb.append(")");
        return sb.toString();
    }
    // @formatter:on
}
