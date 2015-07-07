package io.github.malakeel;



public interface IStatementBuilder
{

	/***
	 * Indicates that the statement is built and an ";" is appended. An attempt
	 * to modify or build the internal state, should through an exception,
	 * except for parameter setting if implementing {@link IParameterized}.
	 * 
	 * @return
	 */
	String end();

}
