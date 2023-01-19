package uk.co.raubach.tractivity.server.pojo;

import uk.co.raubach.tractivity.server.util.StringUtils;

import java.util.Arrays;

/**
 * @author Sebastian Raubach
 */
public class Filter
{
	private String   column;
	private String   comparator;
	private String   operator;
	private String[] values;

	public Filter()
	{
	}

	public Filter(String column, String comparator, String operator, String[] values)
	{
		this.column = column;
		this.comparator = comparator;
		this.operator = operator;
		this.values = values;
	}

	public String getColumn()
	{
		return column;
	}

	public String getSafeColumn()
	{
		return getSafeColumn(this.column);
	}

	public static String getSafeColumn(String column)
	{
		if (StringUtils.isEmpty(column))
		{
			return "";
		}
		else
		{
			return column.replaceAll("[^a-zA-Z0-9._-]", "").replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
		}
	}

	public Filter setColumn(String column)
	{
		this.column = column;
		return this;
	}

	public String getComparator()
	{
		return comparator;
	}

	public Filter setComparator(String comparator)
	{
		this.comparator = comparator;
		return this;
	}

	public String getOperator()
	{
		return operator;
	}

	public Filter setOperator(String operator)
	{
		this.operator = operator;
		return this;
	}

	public String[] getValues()
	{
		return values;
	}

	public Filter setValues(String[] values)
	{
		this.values = values;
		return this;
	}

	@Override
	public String toString()
	{
		return "Filter{" +
			"column='" + column + '\'' +
			", comparator='" + comparator + '\'' +
			", operator='" + operator + '\'' +
			", values=" + Arrays.toString(values) +
			'}';
	}
}
