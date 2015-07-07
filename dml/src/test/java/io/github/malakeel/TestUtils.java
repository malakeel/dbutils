package io.github.malakeel;



import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtils
{

	static void assertSQLEquals(String expected, String actual)
	{
		expected = clean(expected);
		actual = clean(actual);

		assertEquals(expected, actual);
	}

	private static String clean(String str)
	{
		str = str.replaceAll("^\\s", "");
		str = str.replaceAll("\\s\\)", ")");
		str = str.replaceAll("\\(\\s", "(");
		str = str.replaceAll("\\s+", " ");

		return str.trim();
	}

	static void printSQLDiff(String actual, String expected)
	{

		actual = clean(actual);
		expected = clean(expected);

		String[] sql1_a = actual.split("\\s");
		String[] sql2_a = expected.split("\\s");

		int size = Math.min(sql1_a.length, sql2_a.length);

		for (int i = 0; i < size; i++)
		{
			String v1 = sql1_a[i].trim();
			String v2 = sql2_a[i].trim();

			System.out.printf("%-30.30s  %-30.30s%n", v1, v2);
		}
	}

	static String loadFromFile(String fileName)
	{
		URL url = TestSelect.class.getResource("/" + fileName);

		byte[] encoded;
		try
		{
			encoded = Files.readAllBytes(Paths.get(url.getPath()));
			return new String(encoded, StandardCharsets.UTF_8);

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}
}
