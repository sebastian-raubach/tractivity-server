package uk.co.raubach.tractivity.server.pojo;

import java.sql.Timestamp;
import java.util.List;

public class ActivityPost
{
	private Integer       activityTypeId;
	private Integer       locationId;
	private Integer       eventId;
	private List<Integer> participantIds;
	private Timestamp     createdOn;

	public Integer getActivityTypeId()
	{
		return activityTypeId;
	}

	public ActivityPost setActivityTypeId(Integer activityTypeId)
	{
		this.activityTypeId = activityTypeId;
		return this;
	}

	public Integer getLocationId()
	{
		return locationId;
	}

	public ActivityPost setLocationId(Integer locationId)
	{
		this.locationId = locationId;
		return this;
	}

	public Integer getEventId()
	{
		return eventId;
	}

	public ActivityPost setEventId(Integer eventId)
	{
		this.eventId = eventId;
		return this;
	}

	public List<Integer> getParticipantIds()
	{
		return participantIds;
	}

	public ActivityPost setParticipantIds(List<Integer> participantIds)
	{
		this.participantIds = participantIds;
		return this;
	}

	public Timestamp getCreatedOn()
	{
		return createdOn;
	}

	public ActivityPost setCreatedOn(Timestamp createdOn)
	{
		this.createdOn = createdOn;
		return this;
	}
}
