package io.github.malakeel.impl;



import io.github.malakeel.IExpression;
import io.github.malakeel.IFrom;
import io.github.malakeel.IHaving;
import io.github.malakeel.IParameterized;
import io.github.malakeel.ISelect;
import io.github.malakeel.ISelectable;
import io.github.malakeel.IWhere;

import java.nio.charset.CoderMalfunctionError;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SelectBuilder implements ISelect, IFrom, IHaving, IExpression, IWhere, IParameterized
{

	// private StringBuilder bldr = new StringBuilder();

	private List<Column>				columns				= new LinkedList<Column>();

	private List<String>				whereStatments		= new LinkedList<String>();

	private List<String>				havingStatements	= new LinkedList<String>();

	private List<String>				groupByStatments	= new LinkedList<String>();

	private List<String>				fromStatments		= new LinkedList<String>();

	private final Map<String, Object>	parameters			= new HashMap<String, Object>();

	private Column						currentColument;

	private List<String>				windowStatements	= new LinkedList<String>();

	private String						newLine				= System.getProperty("line.separator");

	// private void append(String str)
	// {
	// this.bldr.append(str);
	// }

	// private void line()
	// {
	// this.append(System.getProperty("line.separator"));
	// }

	@Override
	public IFrom select(String str)
	{
		if (str.trim().isEmpty())
			return this;
		
		this.currentColument = new Column(str);
		this.columns.add(this.currentColument);
		return this;
	}

	@Override
	public IFrom column(String col)
	{
		return this.select(col);
	}

	@Override
	public ISelectable as(String alias)
	{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implmented yet");
		// return null;
	}

	@Override
	public IExpression from(String from)
	{
		this.fromStatments.add(from);
		return this;
	}

	@Override
	public IExpression where(String filter)
	{
		this.whereStatments.add(filter);
		return this;
	}

	@Override
	public IHaving groupBy(String group)
	{
		this.groupByStatments.add(group);
		return this;
	}

	@Override
	public IExpression window(String wndo)
	{
		this.windowStatements.add(wndo);
		return this;
	}

	@Override
	public String toString()
	{

		StringBuilder bldr = new StringBuilder();
		String sql = "";

		if (this.columns.size() == 0)
			throw new IllegalStateException("No Columns Added");

		bldr.append("SELECT ");

		for (Column c : this.columns)
		{
			bldr.append(c);
			// if(is)
			bldr.append(",");
		}

		if (this.fromStatments.size() != 0)
		{
			bldr.append(this.newLine);
			bldr.append("FROM ");
			for (String frm : this.fromStatments)
			{
				bldr.append(frm);
			}

			if (this.whereStatments.size() != 0)
			{

				bldr.append(this.newLine);
				bldr.append("WHERE ");
				for (String wr : this.whereStatments)
				{
					bldr.append(wr);
				}

				bldr.append(this.newLine);

				sql = bldr.toString();

				for (String k : this.parameters.keySet())
				{
					Object v = this.parameters.get(k);
					String n = ":" + k + ":";
					sql = sql.replace(n, v.toString());
				}
			}
		}

		return sql;
	}

	@Override
	public IParameterized withParam(String name, String value)
	{
		value = "'" + value + "'";
		this.parameters.put(name, value);
		return this;
	}

	public IParameterized withParam(String name, Number value)
	{
		this.parameters.put(name, value);
		return this;
	}

}
