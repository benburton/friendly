package com.friendly.people;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * A {@link Serializable} representation of a person which contains information
 * about first name, last name, gender, and a URL to an image of the person.
 * 
 * @author Ben Burton
 */
@XStreamAlias("person")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Gender {
		MALE, FEMALE, UNKNOWN;
	}

	@XStreamAlias("firstName")
	private String firstName;

	@XStreamAlias("lastName")
	private String lastName;

	@XStreamAlias("gender")
	private Gender gender;

	@XStreamAlias("imagePath")
	private String imagePath;

	public Person() {

	}

	public Person(String firstName, String lastName) {
		this(firstName, lastName, Gender.UNKNOWN, null);
	}

	public Person(String firstName, String lastName, Gender gender,
			String imagePath) {
		setFirstName(firstName);
		setLastName(lastName);
		setGender(gender);
		setImagePath(imagePath);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isEmpty(getFirstName())) {
			sb.append(getFirstName());
		}
		if (!StringUtils.isEmpty(getLastName())) {
			if (sb.length() != 0) {
				sb.append(" ");
			}
			sb.append(getLastName());
		}

		return sb.toString();
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
