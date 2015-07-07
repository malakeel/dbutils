package io.github.malakeel;



import io.github.malakeel.impl.SelectBuilder;

public class SQL
{

	public static IFrom Select(String projection)
	{
		SelectBuilder query = new SelectBuilder();
		IFrom slct = query.select(projection);

		return slct;
	}

	public static String format(String sql)
	{

		SQLFormatter formatter = new SQLFormatter();

		sql = sql.replaceAll("\\s+", " ");

		return formatter.prettyPrint(sql).toString();

	}

}
