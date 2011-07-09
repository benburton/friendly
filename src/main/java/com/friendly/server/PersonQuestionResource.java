package com.friendly.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.friendly.people.LocalPersonQuestionFactory;
import com.friendly.people.PeopleProvider;
import com.friendly.people.PersonQuestion;
import com.thoughtworks.xstream.XStream;

/**
 * Represents a RESTful resource to provide remote access to
 * {@link PeopleProvider}.
 * 
 * @author Ben Burton
 */
@Path("/people")
public class PersonQuestionResource {

	private final PeopleProvider peopleProvider;

	public PersonQuestionResource(PeopleProvider peopleProvider) {
		this.peopleProvider = peopleProvider;
	}

	@GET
	@Path("question")
	@Produces("application/xml")
	public String getQuestion() {
		PersonQuestion question = LocalPersonQuestionFactory
				.getQuestion(peopleProvider);
		XStream xstream = new XStream();
		return xstream.toXML(question);
	}

}
