package com.friendly.people;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.friendly.people.Person.Gender;

/**
 * Represents a factory to generate {@link PersonQuestion}s from a local
 * dataset.
 * 
 * @author Ben Burton
 */
public class LocalPersonQuestionFactory {

	protected static final int QUESTION_CHOICE_COUNT = 5;

	private static final Random random;

	static {
		random = new Random();
	}

	/**
	 * Takes a {@link PeopleProvider} and creates a {@link PersonQuestion}.
	 * 
	 * @param peopleProvider
	 *            {@link PeopleProvider} used to generate a
	 *            {@link PersonQuestion}
	 * @return {@link PersonQuestion}
	 */
	public static PersonQuestion getQuestion(PeopleProvider peopleProvider) {
		checkChoices(peopleProvider.getPeople());
		List<Person> choices = getRandomSubsetByGender(peopleProvider,
				getRandomGender());
		Person answer = getRandomAnswer(choices);
		return new PersonQuestion(choices, answer);
	}

	private static void checkChoices(List<Person> people) {
		checkChoicesByGender(people, Gender.FEMALE);
		checkChoicesByGender(people, Gender.MALE);
	}

	private static void checkChoicesByGender(List<Person> people, Gender gender) {
		int count = 0;
		for (Person person : people) {
			if (person.getGender() == gender) {
				count++;
			}
		}
		if (count < QUESTION_CHOICE_COUNT) {
			throw new IllegalArgumentException(String.format(
					"Collection does not contain enough choices for %s",
					gender.name()));
		}
	}

	private static Gender getRandomGender() {
		return random.nextInt(2) == 1 ? Gender.MALE : Gender.FEMALE;
	}

	private static Person getRandomAnswer(List<Person> people) {
		return people.get(getRandomIndex(QUESTION_CHOICE_COUNT));
	}

	private static List<Person> getRandomSubsetByGender(
			PeopleProvider peopleProvider, Gender gender) {
		List<Person> people = peopleProvider.getPeople();
		List<Person> peopleSubset = new ArrayList<Person>(QUESTION_CHOICE_COUNT);
		List<Integer> indexCache = new ArrayList<Integer>(QUESTION_CHOICE_COUNT);

		while (!hasEnoughPeople(peopleSubset)) {
			int randomIndex = getNextRandomIndex(people, gender, indexCache);
			indexCache.add(randomIndex);
			peopleSubset.add(people.get(randomIndex));
		}

		return peopleSubset;
	}

	private static boolean hasEnoughPeople(List<Person> people) {
		return people.size() == QUESTION_CHOICE_COUNT;
	}

	protected static int getNextRandomIndex(List<Person> people, Gender gender,
			List<Integer> indexCache) {
		if (indexCache == null) {
			indexCache = new ArrayList<Integer>();
		}

		int index = getRandomIndex(people.size());
		while (indexCache.contains(index)
				|| people.get(index).getGender() != gender) {
			index = getRandomIndex(people.size());
		}

		return index;
	}

	private static int getRandomIndex(int upperBound) {
		return random.nextInt(upperBound);
	}

}
