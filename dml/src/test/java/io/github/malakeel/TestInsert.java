package io.github.malakeel;



import io.github.malakeel.impl.InsertBuilder;

import org.junit.Test;

public class TestInsert
{

	@Test
	public void insertTest1()
	{
		InsertBuilder insert = new InsertBuilder();

		insert.insert("id , first_name , last_name ", " date_of_birth", "email", "phone").into("customer");

		insert.values(1, "Kelsey", "Price", "Dec 31, 1969", "quis.accumsan@penatibus.com", "(367) 871-6294");
		insert.values(2, "Roth", "Saunders", "Dec 31, 1969", "erat@eratnonummyultricies.com", "(856) 576-8455");
		insert.values(3, "Ingrid", "Olsen", "Dec 31, 1969", "fermentum.arcu.Vestibulum@elit.edu", "(292) 619-9663");
		insert.values(4, "Donna", "Sharp", "Dec 31, 1969", "leo.in@mattisvelit.ca", "(609) 727-9295");
		insert.values(5, "Tatiana", "Avery", "Dec 31, 1969", "Vivamus.nisi@eu.ca", "(683) 346-7111");
		insert.values(6, "Wesley", "Martinez", "Dec 31, 1969", "non.dui@elitpharetraut.net", "(907) 953-3305");
		insert.values(7, "Hoyt", "Cleveland", "Dec 31, 1969", "quis.urna.Nunc@elitpellentesquea.ca", "(230) 846-6319");
		insert.values(8, "Bradley", "Glass", "Dec 31, 1969", "nec.imperdiet@mienimcondimentum.net", "(239) 634-1050");
		insert.values(9, "Sacha", "Trujillo", "Dec 31, 1969", "ipsum@sagittis.com", "(539) 733-7145");
		insert.values(10, "Cally", "Dorsey", "Dec 31, 1969", "rhoncus.Donec.est@uterat.co.uk", "(890) 159-2475");

		String expected = TestUtils.loadFromFile("insert1.sql");

		String actual = insert.toString();

//		TestUtils.printSQLDiff(expected, actual);
		TestUtils.assertSQLEquals(expected, actual);
//		System.out.println(insert.toString());

	}
}
