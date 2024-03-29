/*
 * This file is generated by jOOQ.
 */
package uk.co.raubach.tractivity.server.database.codegen.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

import uk.co.raubach.tractivity.server.pojo.SimpleParticipant;


// @formatter:off
/**
 * VIEW
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ViewActivities implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer             activityId;
    private Integer             activityTypeId;
    private String              activityTypeName;
    private Timestamp           activityCreatedOn;
    private Integer             locationId;
    private String              locationName;
    private Integer             eventId;
    private String              eventName;
    private Timestamp           eventCreatedOn;
    private SimpleParticipant[] participants;

    public ViewActivities() {}

    public ViewActivities(ViewActivities value) {
        this.activityId = value.activityId;
        this.activityTypeId = value.activityTypeId;
        this.activityTypeName = value.activityTypeName;
        this.activityCreatedOn = value.activityCreatedOn;
        this.locationId = value.locationId;
        this.locationName = value.locationName;
        this.eventId = value.eventId;
        this.eventName = value.eventName;
        this.eventCreatedOn = value.eventCreatedOn;
        this.participants = value.participants;
    }

    public ViewActivities(
        Integer             activityId,
        Integer             activityTypeId,
        String              activityTypeName,
        Timestamp           activityCreatedOn,
        Integer             locationId,
        String              locationName,
        Integer             eventId,
        String              eventName,
        Timestamp           eventCreatedOn,
        SimpleParticipant[] participants
    ) {
        this.activityId = activityId;
        this.activityTypeId = activityTypeId;
        this.activityTypeName = activityTypeName;
        this.activityCreatedOn = activityCreatedOn;
        this.locationId = locationId;
        this.locationName = locationName;
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventCreatedOn = eventCreatedOn;
        this.participants = participants;
    }

    /**
     * Getter for <code>tractivity_db.view_activities.activity_id</code>.
     */
    public Integer getActivityId() {
        return this.activityId;
    }

    /**
     * Setter for <code>tractivity_db.view_activities.activity_id</code>.
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * Getter for <code>tractivity_db.view_activities.activity_type_id</code>.
     */
    public Integer getActivityTypeId() {
        return this.activityTypeId;
    }

    /**
     * Setter for <code>tractivity_db.view_activities.activity_type_id</code>.
     */
    public void setActivityTypeId(Integer activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    /**
     * Getter for <code>tractivity_db.view_activities.activity_type_name</code>.
     */
    public String getActivityTypeName() {
        return this.activityTypeName;
    }

    /**
     * Setter for <code>tractivity_db.view_activities.activity_type_name</code>.
     */
    public void setActivityTypeName(String activityTypeName) {
        this.activityTypeName = activityTypeName;
    }

    /**
     * Getter for
     * <code>tractivity_db.view_activities.activity_created_on</code>.
     */
    public Timestamp getActivityCreatedOn() {
        return this.activityCreatedOn;
    }

    /**
     * Setter for
     * <code>tractivity_db.view_activities.activity_created_on</code>.
     */
    public void setActivityCreatedOn(Timestamp activityCreatedOn) {
        this.activityCreatedOn = activityCreatedOn;
    }

    /**
     * Getter for <code>tractivity_db.view_activities.location_id</code>.
     */
    public Integer getLocationId() {
        return this.locationId;
    }

    /**
     * Setter for <code>tractivity_db.view_activities.location_id</code>.
     */
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    /**
     * Getter for <code>tractivity_db.view_activities.location_name</code>.
     */
    public String getLocationName() {
        return this.locationName;
    }

    /**
     * Setter for <code>tractivity_db.view_activities.location_name</code>.
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    /**
     * Getter for <code>tractivity_db.view_activities.event_id</code>.
     */
    public Integer getEventId() {
        return this.eventId;
    }

    /**
     * Setter for <code>tractivity_db.view_activities.event_id</code>.
     */
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    /**
     * Getter for <code>tractivity_db.view_activities.event_name</code>.
     */
    public String getEventName() {
        return this.eventName;
    }

    /**
     * Setter for <code>tractivity_db.view_activities.event_name</code>.
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Getter for <code>tractivity_db.view_activities.event_created_on</code>.
     */
    public Timestamp getEventCreatedOn() {
        return this.eventCreatedOn;
    }

    /**
     * Setter for <code>tractivity_db.view_activities.event_created_on</code>.
     */
    public void setEventCreatedOn(Timestamp eventCreatedOn) {
        this.eventCreatedOn = eventCreatedOn;
    }

    /**
     * Getter for <code>tractivity_db.view_activities.participants</code>.
     */
    public SimpleParticipant[] getParticipants() {
        return this.participants;
    }

    /**
     * Setter for <code>tractivity_db.view_activities.participants</code>.
     */
    public void setParticipants(SimpleParticipant[] participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ViewActivities (");

        sb.append(activityId);
        sb.append(", ").append(activityTypeId);
        sb.append(", ").append(activityTypeName);
        sb.append(", ").append(activityCreatedOn);
        sb.append(", ").append(locationId);
        sb.append(", ").append(locationName);
        sb.append(", ").append(eventId);
        sb.append(", ").append(eventName);
        sb.append(", ").append(eventCreatedOn);
        sb.append(", ").append(Arrays.toString(participants));

        sb.append(")");
        return sb.toString();
    }
    // @formatter:on
}
