package uk.co.raubach.tractivity.server.pojo;

public class ActivityCount
{
	private Integer activityTypeId;
	private String activityTypeName;
	private Integer count;

	public Integer getActivityTypeId()
	{
		return activityTypeId;
	}

	public ActivityCount setActivityTypeId(Integer activityTypeId)
	{
		this.activityTypeId = activityTypeId;
		return this;
	}

	public String getActivityTypeName()
	{
		return activityTypeName;
	}

	public ActivityCount setActivityTypeName(String activityTypeName)
	{
		this.activityTypeName = activityTypeName;
		return this;
	}

	public Integer getCount()
	{
		return count;
	}

	public ActivityCount setCount(Integer count)
	{
		this.count = count;
		return this;
	}
}
