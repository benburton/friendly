package com.friendly.people;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.friendly.people.Person.Gender;

public class PersonQuestionFactoryTest {

	@Test
	public void testRandomIndex() {
		List<Person> people = givenAListOfPeople();
		for (int i = 0; i < 500; i++) {
			int randomIndex = whenARandomIndexIsSelected(people);
			thenTheRandomIndexIsInTheCorrectRange(randomIndex, people.size());
		}
	}

	@Test
	public void testPersonQuestionBuilder() {
		PeopleProvider peopleProvider = givenAPeopleProvider();
		PersonQuestion personQuestion = whenPersonQuestionBuilderProcessesPeople(peopleProvider);
		thenPersonQuestionHasAnAnswer(personQuestion);
	}

	@Test
	public void testQuestionOptionsAreUnique() {
		PeopleProvider peopleProvider = givenAPeopleProvider();
		PersonQuestion personQuestion = whenPersonQuestionBuilderProcessesPeople(peopleProvider);
		thenPersonQuestionHasUniqueOptions(personQuestion);
	}

	@Test
	public void testQuestionOptionsAreAllSameGender() {
		PeopleProvider peopleProvider = givenAPeopleProvider();
		PersonQuestion personQuestion = whenPersonQuestionBuilderProcessesPeople(peopleProvider);
		thenPersonQuestionHasAllTheSameGender(personQuestion);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test() {
		PeopleProvider peopleProvider = givenAPeopleProviderWithoutEnoughMales();
		whenPersonQuestionBuilderProcessesPeople(peopleProvider);
	}

	private PeopleProvider givenAPeopleProvider() {
		return new MockPeopleProvider();
	}

	private List<Person> givenAListOfPeople() {
		return givenAPeopleProvider().getPeople();
	}

	private PeopleProvider givenAPeopleProviderWithoutEnoughMales() {
		return new PeopleProvider() {
			@Override
			@SuppressWarnings("serial")
			public List<Person> getPeople() {
				return new ArrayList<Person>() {
					{
						add(new Person("Michael", "Scott", Gender.MALE, null));
					}
				};
			}
		};
	}

	private PersonQuestion whenPersonQuestionBuilderProcessesPeople(
			PeopleProvider peopleProvider) {
		return LocalPersonQuestionFactory.getQuestion(peopleProvider);
	}

	private int whenARandomIndexIsSelected(List<Person> people) {
		return LocalPersonQuestionFactory.getNextRandomIndex(people, Gender.MALE,
				null);
	}

	private void thenPersonQuestionHasAnAnswer(PersonQuestion question) {
		assertNotNull(question.getAnswer());
	}

	private void thenTheRandomIndexIsInTheCorrectRange(int randomIndex,
			int upperBound) {
		assertTrue(randomIndex >= 0);
		assertTrue(randomIndex <= upperBound);
	}

	private void thenPersonQuestionHasUniqueOptions(
			PersonQuestion personQuestion) {
		Set<Person> seenPeople = new HashSet<Person>();
		for (Person option : personQuestion.getOptions()) {
			assertFalse(seenPeople.contains(option));
			seenPeople.add(option);
		}
	}

	private void thenPersonQuestionHasAllTheSameGender(
			PersonQuestion personQuestion) {
		Gender gender = null;
		for (Person person : personQuestion.getOptions()) {
			if (gender == null) {
				gender = person.getGender();
			} else {
				assertEquals(gender, person.getGender());
			}
		}
	}
}
