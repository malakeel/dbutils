package io.github.malakeel;



public interface ISelectable
{

	ISelectable column(String col);

	ISelectable as(String alias);
}
