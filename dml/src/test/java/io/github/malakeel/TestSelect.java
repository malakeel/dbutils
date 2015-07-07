package io.github.malakeel;



import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.github.malakeel.impl.SelectBuilder;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestSelect
{

	@Test
	public void selectTest1()
	{
		SelectBuilder sql = new SelectBuilder();

		sql.select("ID , first_name as name , last_name as surname, date_of_birth as dob , email ")//
				.from("USERS") //
				.where("EMAIL = :email:  AND FIRST_NAME = :first_name:");

		sql.withParam("first_name", "John");
		sql.withParam("email", "someone@example.com");

		String expected = TestUtils.loadFromFile("select1.sql");
		String actual = sql.toString();

		TestUtils.assertSQLEquals(expected, actual);
	}

	@Test
	@Ignore
	public void selectTest2()
	{
		// this is for grouping by
		SelectBuilder sql = new SelectBuilder();

		sql.select("ID , first_name as name , last_name as surname, date_of_birth as dob , count(*)")//
				.from("USERS") //
				.groupBy("surname");

		String expected = TestUtils.loadFromFile("select1.sql");

		String actual = sql.toString();

		TestUtils.assertSQLEquals(expected, actual);
	}

	@Test
	public void selectWithSubQueryTest()
	{
		SelectBuilder q = new SelectBuilder();

		SelectBuilder sub1 = new SelectBuilder();
		SelectBuilder sub2 = new SelectBuilder();

		sub1.select("PLAYERNO, SUM(AMOUNT) AS TOTAL").from("  PENALTIES").groupBy("PLAYERNO");

		sub2.select("PLAYERNO").from("PLAYERS").where("TOWN = :TOWN1: OR TOWN = :TOWN2:");

		sub2.withParam("TOWN1", "Stratford");
		sub2.withParam("TOWN2", "Inglewood");

		q.select("AVG(TOTAL)").from("(" + sub1 + ") AS TOTALS").where("PLAYERNO IN (" + sub2 + ")");

		String expected = TestUtils.loadFromFile("select_sub1.sql");

		String actual = q.toString();

		TestUtils.assertSQLEquals(expected, actual);

	}

}
