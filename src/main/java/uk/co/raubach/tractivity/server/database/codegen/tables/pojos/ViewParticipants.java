/*
 * This file is generated by jOOQ.
 */
package uk.co.raubach.tractivity.server.database.codegen.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

import uk.co.raubach.tractivity.server.database.codegen.enums.ViewParticipantsParticipantGender;


// @formatter:off
/**
 * VIEW
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ViewParticipants implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer                           participantId;
    private String                            participantName;
    private Timestamp                         participantDob;
    private ViewParticipantsParticipantGender participantGender;
    private Timestamp                         createdOn;
    private Map<String, Integer>              activityCounts;

    public ViewParticipants() {}

    public ViewParticipants(ViewParticipants value) {
        this.participantId = value.participantId;
        this.participantName = value.participantName;
        this.participantDob = value.participantDob;
        this.participantGender = value.participantGender;
        this.createdOn = value.createdOn;
        this.activityCounts = value.activityCounts;
    }

    public ViewParticipants(
        Integer                           participantId,
        String                            participantName,
        Timestamp                         participantDob,
        ViewParticipantsParticipantGender participantGender,
        Timestamp                         createdOn,
        Map<String, Integer>              activityCounts
    ) {
        this.participantId = participantId;
        this.participantName = participantName;
        this.participantDob = participantDob;
        this.participantGender = participantGender;
        this.createdOn = createdOn;
        this.activityCounts = activityCounts;
    }

    /**
     * Getter for <code>tractivity_db.view_participants.participant_id</code>.
     */
    public Integer getParticipantId() {
        return this.participantId;
    }

    /**
     * Setter for <code>tractivity_db.view_participants.participant_id</code>.
     */
    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    /**
     * Getter for <code>tractivity_db.view_participants.participant_name</code>.
     */
    public String getParticipantName() {
        return this.participantName;
    }

    /**
     * Setter for <code>tractivity_db.view_participants.participant_name</code>.
     */
    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    /**
     * Getter for <code>tractivity_db.view_participants.participant_dob</code>.
     */
    public Timestamp getParticipantDob() {
        return this.participantDob;
    }

    /**
     * Setter for <code>tractivity_db.view_participants.participant_dob</code>.
     */
    public void setParticipantDob(Timestamp participantDob) {
        this.participantDob = participantDob;
    }

    /**
     * Getter for
     * <code>tractivity_db.view_participants.participant_gender</code>.
     */
    public ViewParticipantsParticipantGender getParticipantGender() {
        return this.participantGender;
    }

    /**
     * Setter for
     * <code>tractivity_db.view_participants.participant_gender</code>.
     */
    public void setParticipantGender(ViewParticipantsParticipantGender participantGender) {
        this.participantGender = participantGender;
    }

    /**
     * Getter for <code>tractivity_db.view_participants.created_on</code>.
     */
    public Timestamp getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for <code>tractivity_db.view_participants.created_on</code>.
     */
    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Getter for <code>tractivity_db.view_participants.activity_counts</code>.
     */
    public Map<String, Integer> getActivityCounts() {
        return this.activityCounts;
    }

    /**
     * Setter for <code>tractivity_db.view_participants.activity_counts</code>.
     */
    public void setActivityCounts(Map<String, Integer> activityCounts) {
        this.activityCounts = activityCounts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ViewParticipants (");

        sb.append(participantId);
        sb.append(", ").append(participantName);
        sb.append(", ").append(participantDob);
        sb.append(", ").append(participantGender);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(activityCounts);

        sb.append(")");
        return sb.toString();
    }
    // @formatter:on
}
