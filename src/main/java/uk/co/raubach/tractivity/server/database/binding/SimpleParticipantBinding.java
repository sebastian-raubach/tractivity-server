package uk.co.raubach.tractivity.server.database.binding;

import com.google.gson.*;
import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.impl.DSL;
import uk.co.raubach.tractivity.server.pojo.SimpleParticipant;

import java.sql.*;
import java.util.Objects;

/**
 * @author Sebastian Raubach
 */
public class SimpleParticipantBinding implements Binding<JSON, SimpleParticipant[]>
{
	@Override
	public Converter<JSON, SimpleParticipant[]> converter()
	{
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return new Converter<>()
		{
			@Override
			public SimpleParticipant[] from(JSON o)
			{
				return o == null ? null : gson.fromJson(Objects.toString(o), SimpleParticipant[].class);
			}

			@Override
			public JSON to(SimpleParticipant[] importJobDetails)
			{
				return importJobDetails == null ? null : JSON.json(gson.toJson(importJobDetails));
			}

			@Override
			public Class<JSON> fromType()
			{
				return JSON.class;
			}

			@Override
			public Class<SimpleParticipant[]> toType()
			{
				return SimpleParticipant[].class;
			}
		};
	}

	@Override
	public void sql(BindingSQLContext<SimpleParticipant[]> ctx)
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
	public void register(BindingRegisterContext<SimpleParticipant[]> ctx)
		throws SQLException
	{
		ctx.statement().registerOutParameter(ctx.index(), Types.VARCHAR);
	}

	@Override
	public void set(BindingSetStatementContext<SimpleParticipant[]> ctx)
		throws SQLException
	{
		ctx.statement().setString(ctx.index(), Objects.toString(ctx.convert(converter()).value(), null));
	}

	@Override
	public void set(BindingSetSQLOutputContext<SimpleParticipant[]> ctx)
		throws SQLException
	{
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void get(BindingGetResultSetContext<SimpleParticipant[]> ctx)
		throws SQLException
	{
		ctx.convert(converter()).value(JSON.json(ctx.resultSet().getString(ctx.index())));
	}

	@Override
	public void get(BindingGetStatementContext<SimpleParticipant[]> ctx)
		throws SQLException
	{
		ctx.convert(converter()).value(JSON.json(ctx.statement().getString(ctx.index())));
	}

	@Override
	public void get(BindingGetSQLInputContext<SimpleParticipant[]> ctx)
		throws SQLException
	{
		throw new SQLFeatureNotSupportedException();
	}
}
