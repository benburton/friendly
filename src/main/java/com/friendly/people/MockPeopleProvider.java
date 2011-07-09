package com.friendly.people;

import java.util.ArrayList;
import java.util.List;

import com.friendly.people.Person.Gender;

/**
 * Mock {@link PeopleProvider} using characters from NBC's The Office.
 * 
 * @author Ben Burton
 */
public class MockPeopleProvider implements PeopleProvider {

	@Override
	public List<Person> getPeople() {
		List<Person> people = new ArrayList<Person>(10);
		people.add(new Person("Michael", "Scott", Gender.MALE,
				"http://upload.wikimedia.org/wikipedia/en/d/dc/MichaelScott.png"));
		people.add(new Person("Pam", "Beesly", Gender.FEMALE,
				"http://upload.wikimedia.org/wikipedia/en/6/67/Pam_Beesley.jpg"));
		people.add(new Person("Jim", "Halpert", Gender.MALE,
				"http://upload.wikimedia.org/wikipedia/en/c/c6/Johnoffice.jpg"));
		people.add(new Person("Meredith", "Palmer", Gender.FEMALE,
				"http://upload.wikimedia.org/wikipedia/en/d/d8/Meridethpalmeroffice.jpg"));
		people.add(new Person("Dwight", "Schrute", Gender.MALE,
				"http://upload.wikimedia.org/wikipedia/en/b/be/Rainn_Wilson.jpg"));
		people.add(new Person("Angela", "Martin", Gender.FEMALE,
				"http://upload.wikimedia.org/wikipedia/en/0/0b/Angela_Martin.jpg"));
		people.add(new Person("Creed", "Bratton", Gender.MALE,
				"http://upload.wikimedia.org/wikipedia/commons/c/ca/Creed_Bratton.jpg"));
		people.add(new Person("Phyllis", "Lapin", Gender.FEMALE,
				"http://upload.wikimedia.org/wikipedia/commons/a/a5/Phyllis_smithOct07.jpg"));
		people.add(new Person("Andy", "Bernard", Gender.MALE,
				"http://upload.wikimedia.org/wikipedia/en/8/84/Andy_Bernard_photoshot.jpg"));
		people.add(new Person("Kelly", "Kapoor", Gender.FEMALE,
				"http://upload.wikimedia.org/wikipedia/en/8/87/Kellykapoor.png"));
		people.add(new Person("Stanley", "Hudson", Gender.MALE,
				"http://upload.wikimedia.org/wikipedia/en/2/23/Stanley_Hudson.jpg"));
		people.add(new Person("Karen", "Filippelli", Gender.FEMALE,
				"http://upload.wikimedia.org/wikipedia/en/2/22/TheOffice_Karen.png"));
		people.add(new Person(
				"Oscar",
				"Martinez",
				Gender.MALE,
				"http://upload.wikimedia.org/wikipedia/en/5/54/Oscar_Martinez_of_The_Office.jpg"));
		people.add(new Person("Erin", "Hannon", Gender.FEMALE,
				"http://upload.wikimedia.org/wikipedia/en/9/93/Erin_Hannon.jpg"));
		people.add(new Person("Ryan", "Howard", Gender.MALE,
				"http://upload.wikimedia.org/wikipedia/en/2/2e/Ryanoffice.jpg"));
		people.add(new Person("Jan", "Levenson", Gender.FEMALE,
				"http://upload.wikimedia.org/wikipedia/en/f/f9/Jan.PNG"));
		people.add(new Person("Darryl", "Philbin", Gender.MALE,
				"http://upload.wikimedia.org/wikipedia/en/6/65/DarrylPhilbin.jpg"));
		people.add(new Person("Holly", "Flax", Gender.FEMALE,
				"http://upload.wikimedia.org/wikipedia/en/f/f4/Hollytheoffice.jpg"));
		people.add(new Person("Kevin", "Malone", Gender.MALE,
				"http://upload.wikimedia.org/wikipedia/en/c/c6/Kevin.PNG"));
		people.add(new Person("Gabe", "Lewis", Gender.MALE,
				"http://upload.wikimedia.org/wikipedia/en/c/c2/Gabe_profile_picture.jpg"));
		people.add(new Person("Toby", "Flenderson", Gender.MALE,
				"http://upload.wikimedia.org/wikipedia/en/0/01/TobyFlendersonphotoshot.png"));
		people.add(new Person(
				"David",
				"Wallace",
				Gender.MALE,
				"http://upload.wikimedia.org/wikipedia/en/a/a0/David_Wallace_%28The_Office%29.jpg"));
		return people;
	}

}
