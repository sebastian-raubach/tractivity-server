package uk.co.raubach.tractivity.server.pojo;

import uk.co.raubach.tractivity.server.database.codegen.enums.MeasuresType;

public class SimpleMeasures
{
	private Integer      activityMeasureId;
	private Integer      measureId;
	private String       measureName;
	private MeasuresType measureType;
	private String       measuredValue;

	public Integer getActivityMeasureId()
	{
		return activityMeasureId;
	}

	public SimpleMeasures setActivityMeasureId(Integer activityMeasureId)
	{
		this.activityMeasureId = activityMeasureId;
		return this;
	}

	public Integer getMeasureId()
	{
		return measureId;
	}

	public SimpleMeasures setMeasureId(Integer measureId)
	{
		this.measureId = measureId;
		return this;
	}

	public String getMeasureName()
	{
		return measureName;
	}

	public SimpleMeasures setMeasureName(String measureName)
	{
		this.measureName = measureName;
		return this;
	}

	public MeasuresType getMeasureType()
	{
		return measureType;
	}

	public SimpleMeasures setMeasureType(MeasuresType measureType)
	{
		this.measureType = measureType;
		return this;
	}

	public String getMeasuredValue()
	{
		return measuredValue;
	}

	public SimpleMeasures setMeasuredValue(String measuredValue)
	{
		this.measuredValue = measuredValue;
		return this;
	}
}
