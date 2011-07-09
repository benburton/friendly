package com.friendly.people;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.friendly.people.Person.Gender;

public class PersonTest {

	@Test
	public void testFullName() {
		Person person = givenAPersonWithNoFirstOrLastName();
		thenFullNameIsEmptyString(person);

		person = givenAPersonWithOnlyAFirstName();
		thenOnlyFirstNameIsInFullName(person);

		person = givenAPersonWithOnlyALastName();
		thenOnlyLastNameIsInFullName(person);

		person = givenAPersonWithFirstAndLastName();
		thenFullNameHasBothNames(person);
	}

	private Person givenAPersonWithNoFirstOrLastName() {
		return new Person(null, null, Gender.MALE, "");
	}

	private Person givenAPersonWithOnlyAFirstName() {
		return new Person("Michael", null, Gender.MALE, "");
	}

	private Person givenAPersonWithOnlyALastName() {
		return new Person(null, "Scott", Gender.MALE, "");
	}

	private Person givenAPersonWithFirstAndLastName() {
		return new Person("Michael", "Scott", Gender.MALE, "");
	}

	private void thenFullNameIsEmptyString(Person person) {
		assertEquals(person.getName(), "");
	}

	private void thenOnlyFirstNameIsInFullName(Person person) {
		assertEquals(person.getName(), person.getFirstName());
	}

	private void thenOnlyLastNameIsInFullName(Person person) {
		assertEquals(person.getName(), person.getLastName());
	}

	private void thenFullNameHasBothNames(Person person) {
		assertEquals(person.getName(),
				person.getFirstName() + " " + person.getLastName());
	}

}
