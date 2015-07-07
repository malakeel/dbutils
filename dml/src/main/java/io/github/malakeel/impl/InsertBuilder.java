package io.github.malakeel.impl;



import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import io.github.malakeel.IInTo;
import io.github.malakeel.IInsert;
import io.github.malakeel.IValues;

public class InsertBuilder implements IInsert, IInTo, IValues
{

	private String				tableName;

	private List<String>		columns	= new LinkedList<String>();

	private List<List<Object>>	values	= new LinkedList<List<Object>>();

	private String				newLine	= System.getProperty("line.separator");

	@Override
	public IInTo insert(String... columns)
	{
		for (String col : columns)
			this.addColumn(col);
		return this;
	}

	private void addColumn(String col)
	{
		String[] columns = col.split(",");
		for (int i = 0; i < columns.length; i++)
		{
			String cName = columns[i].trim();
			this.columns.add(cName);
		}
	}

	@Override
	public IValues into(String table)
	{
		if (this.tableName != null)
			throw new IllegalStateException("Table Name already set");

		this.tableName = table;

		return this;

	}

	@Override
	public IValues values(Object... vals)
	{
		if (vals.length != this.columns.size())
		{
			String msg = String.format("Values count (%d) do not match Columns Count (%d).", //
					vals.length, columns.size());

			throw new IllegalArgumentException(msg);
		}

		List<Object> record = Arrays.asList(vals);
		this.values.add(record);

		return this;
	}

	@Override
	public String toString()
	{
		StringBuilder bldr = new StringBuilder();

		bldr.append("INSERT ");
		bldr.append("INTO " + this.tableName);
		bldr.append(" (");

		for (Object value : this.columns)
		{
			bldr.append(value);
			if (!this.isLast(this.columns, value))
			{
				bldr.append(",");
			}
		}

		bldr.append(")");
		bldr.append(this.newLine);
		bldr.append("VALUES ");

		for (List<Object> record : this.values)
		{
			bldr.append(this.newLine);
			bldr.append("(");

			for (Object v : record)
			{
				if (v instanceof Number)
				{
					bldr.append(v);
				} else
				{
					bldr.append("'" + v + "'");
				}

				if (!this.isLast(record, v))
				{
					bldr.append(",");
				}
			}

			bldr.append(")");

			if (!this.isLast(this.values, record))
			{
				bldr.append(",");
			}
		}

		return bldr.toString();
	}

	private boolean isLast(List<?> list, Object v)
	{
		return list.indexOf(v) == (list.size() - 1);
	}
}


