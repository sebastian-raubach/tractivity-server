package uk.co.raubach.tractivity.server.resource;

import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import uk.co.raubach.tractivity.server.pojo.Filter;
import uk.co.raubach.tractivity.server.util.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Sebastian Raubach
 */
public interface IFilteredResource
{
	default <T extends Record> void filter(SelectConditionStep<T> step, Filter[] filters)
	{
		filter(step, filters, false);
	}

	default <T extends Record> void filter(SelectJoinStep<T> step, Filter[] filters)
	{
		filter(step, filters, false);
	}

	default <T extends Record> void filter(SelectConditionStep<T> step, Filter[] filters, boolean jsonOperationAllowed)
	{
		if (filters != null && filters.length > 0)
		{
			Condition overall = filterIndividual(filters[0], jsonOperationAllowed);

			for (int i = 1; i < filters.length; i++)
			{
				Condition condition = filterIndividual(filters[i], jsonOperationAllowed);

				if (condition != null)
				{
					switch (filters[i - 1].getOperator())
					{
						case "and":
							overall = overall.and(condition);
							break;
						case "or":
							overall = overall.or(condition);
							break;
					}
				}
			}

			step.and(overall);
		}
	}

	default <T extends Record> void filter(SelectJoinStep<T> step, Filter[] filters, boolean jsonOperationAllowed)
	{
		if (filters != null && filters.length > 0)
		{
			Condition overall = filterIndividual(filters[0], jsonOperationAllowed);

			for (int i = 1; i < filters.length; i++)
			{
				Condition condition = filterIndividual(filters[i], jsonOperationAllowed);

				if (condition != null)
				{
					switch (filters[i - 1].getOperator())
					{
						case "and":
							overall = overall.and(condition);
							break;
						case "or":
							overall = overall.or(condition);
							break;
					}
				}
			}

			step.where(overall);
		}
	}

	default Condition filterIndividual(Filter filter, boolean jsonOperationAllowed)
	{
		Field<String> field = DSL.field(filter.getSafeColumn(), String.class);
		List<String> values = new ArrayList<>();

		if (!CollectionUtils.isEmpty(filter.getValues()))
			values = Arrays.stream(filter.getValues())
						   .filter(v -> !StringUtils.isEmpty(v))
						   .map(String::strip)
						   .collect(Collectors.toList());

		if (CollectionUtils.isEmpty(values))
			values.add("");

		String first = values.get(0);
		String second = values.size() > 1 ? values.get(1) : null;

		switch (filter.getComparator())
		{
			case "isNull":
				return field.isNull();
			case "isNotNull":
				return field.isNotNull();
			case "equals":
				return field.eq(first);
			case "contains":
				return DSL.lower(field).like("%" + (first == null ? "" : first.toLowerCase()) + "%");
			case "between":
				return field.between(first, second);
			case "greaterThan":
				return field.greaterThan(first);
			case "greaterOrEquals":
				return field.greaterOrEqual(first);
			case "lessThan":
				return field.lessThan(first);
			case "lessOrEquals":
				return field.lessOrEqual(first);
			case "jsonSearch":
				if (jsonOperationAllowed)
				{
					List<Condition> conditions = values.stream()
													   .map(v -> v.replaceAll("[^a-zA-Z0-9_-]", "")) // Replace all non letters and numbers
													   .map(v -> DSL.condition("JSON_SEARCH(LOWER(" + field.getName() + "), 'one', LOWER('%" + v + "%')) IS NOT NULL"))
													   .collect(Collectors.toList());

					Condition result = conditions.get(0);

					for (int i = 1; i < conditions.size(); i++)
					{
						result = result.or(conditions.get(i));
					}

					return result;
				}
				else
				{
					return null;
				}
			case "arrayContains":
				if (jsonOperationAllowed)
				{
					List<Condition> conditions = values.stream()
													   .map(v -> v.replaceAll("[^a-zA-Z0-9_-]", "")) // Replace all non letters and numbers
													   .map(v -> DSL.condition("JSON_CONTAINS(" + field.getName() + ", '" + v + "')"))
													   .collect(Collectors.toList());

					Condition result = conditions.get(0);

					for (int i = 1; i < conditions.size(); i++)
					{
						result = result.or(conditions.get(i));
					}

					return result;
				}
				else
				{
					return null;
				}
			case "inSet":
				List<String> temp;

				if (values.size() > 1)
				{
					// If there are multiple values, just use them
					temp = values;
				}
				else
				{
					// Otherwise, try and split the first one on commas and then use the individual entries
					temp = Arrays.stream(first.split(","))
								 .filter(v -> !StringUtils.isEmpty(v))
								 .map(String::strip)
								 .collect(Collectors.toList());
				}

				return field.in(temp);
		}

		return null;
	}
}
