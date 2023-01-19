package uk.co.raubach.tractivity.server.database.binding;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.impl.DSL;
import uk.co.raubach.tractivity.server.pojo.MeasureRestrictions;

import java.sql.*;
import java.util.*;

/**
 * @author Sebastian Raubach
 */
public class MeasureRestrictionsBinding implements Binding<JSON, MeasureRestrictions>
{
	@Override
	public Converter<JSON, MeasureRestrictions> converter()
	{
		Gson gson = new Gson();
		return new Converter<>()
		{
			@Override
			public MeasureRestrictions from(JSON o)
			{
				return o == null ? null : gson.fromJson(Objects.toString(o), new TypeToken<MeasureRestrictions>(){}.getType());
			}

			@Override
			public JSON to(MeasureRestrictions o)
			{
				return o == null ? null : JSON.json(gson.toJson(o));
			}

			@Override
			public Class<JSON> fromType()
			{
				return JSON.class;
			}

			@Override
			public Class<MeasureRestrictions> toType()
			{
				return MeasureRestrictions.class;
			}
		};
	}

	@Override
	public void sql(BindingSQLContext<MeasureRestrictions> ctx)
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
	public void register(BindingRegisterContext<MeasureRestrictions> ctx)
		throws SQLException
	{
		ctx.statement().registerOutParameter(ctx.index(), Types.VARCHAR);
	}

	@Override
	public void set(BindingSetStatementContext<MeasureRestrictions> ctx)
		throws SQLException
	{
		ctx.statement().setString(ctx.index(), Objects.toString(ctx.convert(converter()).value(), null));
	}

	@Override
	public void set(BindingSetSQLOutputContext<MeasureRestrictions> ctx)
		throws SQLException
	{
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void get(BindingGetResultSetContext<MeasureRestrictions> ctx)
		throws SQLException
	{
		ctx.convert(converter()).value(JSON.json(ctx.resultSet().getString(ctx.index())));
	}

	@Override
	public void get(BindingGetStatementContext<MeasureRestrictions> ctx)
		throws SQLException
	{
		ctx.convert(converter()).value(JSON.json(ctx.statement().getString(ctx.index())));
	}

	@Override
	public void get(BindingGetSQLInputContext<MeasureRestrictions> ctx)
		throws SQLException
	{
		throw new SQLFeatureNotSupportedException();
	}
}
