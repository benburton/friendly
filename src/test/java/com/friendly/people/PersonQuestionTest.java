package com.friendly.people;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class PersonQuestionTest {

	@Test
	public void testSerialization() throws IOException {
		PersonQuestion question = givenAPersonQuestion();
		thenQuestionCanBeSerialized(question);
		System.err.println(get());
	}

	private PersonQuestion givenAPersonQuestion() {
		PeopleProvider peopleProvider = new MockPeopleProvider();
		return LocalPersonQuestionFactory.getQuestion(peopleProvider);
	}

	private void thenQuestionCanBeSerialized(PersonQuestion question) {
		XStream xstream = new XStream();
		xstream.toXML(question);
	}

	private String get() throws IOException {
		return readFileAsString("testquestion.xml");
	}

	private String readFileAsString(String filePath) throws IOException {
		byte[] buffer = new byte[(int) new File(filePath).length()];
		BufferedInputStream f = null;
		try {
			f = new BufferedInputStream(getClass()
					.getResourceAsStream(filePath));
			f.read(buffer);
		} finally {
			if (f != null)
				try {
					f.close();
				} catch (IOException ignored) {
					ignored.printStackTrace();
				}
		}
		return new String(buffer);
	}
}
