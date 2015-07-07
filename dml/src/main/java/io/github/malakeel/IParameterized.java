package io.github.malakeel;



public interface IParameterized
{

	IParameterized withParam(String name, String value);

	IParameterized withParam(String name, Number value);

}
