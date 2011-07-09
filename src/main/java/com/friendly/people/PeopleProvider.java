package com.friendly.people;

import java.util.List;

/**
 * An interface to provide {@link Person}s to the application.
 * 
 * @author Ben Burton
 */
public interface PeopleProvider {

	List<Person> getPeople();

}
