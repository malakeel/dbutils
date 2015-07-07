package io.github.malakeel.impl;



import io.github.malakeel.ISelectable;

public class Column
{

	private String	name;

	private String	alias;

	public Column(String name)
	{
		this.name = name;
	}

	public ISelectable as(String alias)
	{
		this.alias = alias;
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public String toString()
	{
		if (this.alias != null && this.alias.trim().isEmpty())
			return this.name + " " + this.alias;
		return this.name;
	}
}
