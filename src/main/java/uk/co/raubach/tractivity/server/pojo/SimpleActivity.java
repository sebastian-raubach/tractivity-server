package uk.co.raubach.tractivity.server.pojo;

public class SimpleActivity
{
	private Integer             activityId;
	private String              activityName;
	private SimpleParticipant[] activityParticipants;

	public Integer getActivityId()
	{
		return activityId;
	}

	public SimpleActivity setActivityId(Integer activityId)
	{
		this.activityId = activityId;
		return this;
	}

	public String getActivityName()
	{
		return activityName;
	}

	public SimpleActivity setActivityName(String activityName)
	{
		this.activityName = activityName;
		return this;
	}

	public SimpleParticipant[] getActivityParticipants()
	{
		return activityParticipants;
	}

	public SimpleActivity setActivityParticipants(SimpleParticipant[] activityParticipants)
	{
		this.activityParticipants = activityParticipants;
		return this;
	}
}
