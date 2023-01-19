package uk.co.raubach.tractivity.server.database.binding;

import com.google.gson.*;
import uk.co.raubach.tractivity.server.pojo.SimpleActivity;
import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.impl.DSL;

import java.sql.*;
import java.util.Objects;

/**
 * @author Sebastian Raubach
 */
public class SimpleActivityBinding implements Binding<JSON, SimpleActivity[]>
{
	@Override
	public Converter<JSON, SimpleActivity[]> converter()
	{
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return new Converter<>()
		{
			@Override
			public SimpleActivity[] from(JSON o)
			{
				return o == null ? null : gson.fromJson(Objects.toString(o), SimpleActivity[].class);
			}

			@Override
			public JSON to(SimpleActivity[] importJobDetails)
			{
				return importJobDetails == null ? null : JSON.json(gson.toJson(importJobDetails));
			}

			@Override
			public Class<JSON> fromType()
			{
				return JSON.class;
			}

			@Override
			public Class<SimpleActivity[]> toType()
			{
				return SimpleActivity[].class;
			}
		};
	}

	@Override
	public void sql(BindingSQLContext<SimpleActivity[]> ctx)
		throws SQLException
	{
		// Depending on how you generate your SQL, you may need to explicitly distinguish
		// between jOOQ generating bind variables or inlined literals.
		if (ctx.render().paramType() == ParamType.INLINED)
			ctx.render().visit(DSL.inline(ctx.convert(converter()).value())).sql("");
		else
			ctx.render().sql("?");
	}

	@Override
	public void register(BindingRegisterContext<SimpleActivity[]> ctx)
		throws SQLException
	{
		ctx.statement().registerOutParameter(ctx.index(), Types.VARCHAR);
	}

	@Override
	public void set(BindingSetStatementContext<SimpleActivity[]> ctx)
		throws SQLException
	{
		ctx.statement().setString(ctx.index(), Objects.toString(ctx.convert(converter()).value(), null));
	}

	@Override
	public void set(BindingSetSQLOutputContext<SimpleActivity[]> ctx)
		throws SQLException
	{
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void get(BindingGetResultSetContext<SimpleActivity[]> ctx)
		throws SQLException
	{
		ctx.convert(converter()).value(JSON.json(ctx.resultSet().getString(ctx.index())));
	}

	@Override
	public void get(BindingGetStatementContext<SimpleActivity[]> ctx)
		throws SQLException
	{
		ctx.convert(converter()).value(JSON.json(ctx.statement().getString(ctx.index())));
	}

	@Override
	public void get(BindingGetSQLInputContext<SimpleActivity[]> ctx)
		throws SQLException
	{
		throw new SQLFeatureNotSupportedException();
	}
}
