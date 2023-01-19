package uk.co.raubach.tractivity.server.pojo;

import java.util.Date;

public class MeasureRestrictions
{
	private String[] categories;
	private Double minValue;
	private Double maxValue;
	private String minDate;
	private String maxDate;

	public String[] getCategories()
	{
		return categories;
	}

	public MeasureRestrictions setCategories(String[] categories)
	{
		this.categories = categories;
		return this;
	}

	public Double getMinValue()
	{
		return minValue;
	}

	public MeasureRestrictions setMinValue(Double minValue)
	{
		this.minValue = minValue;
		return this;
	}

	public Double getMaxValue()
	{
		return maxValue;
	}

	public MeasureRestrictions setMaxValue(Double maxValue)
	{
		this.maxValue = maxValue;
		return this;
	}

	public String getMinDate()
	{
		return minDate;
	}

	public MeasureRestrictions setMinDate(String minDate)
	{
		this.minDate = minDate;
		return this;
	}

	public String getMaxDate()
	{
		return maxDate;
	}

	public MeasureRestrictions setMaxDate(String maxDate)
	{
		this.maxDate = maxDate;
		return this;
	}
}
