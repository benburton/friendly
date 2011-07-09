package com.friendly.swing.event;

import java.util.EventObject;

/**
 * A custom {@link EventObject} to be fired when an answer to a question is
 * selected.
 * 
 * @author Ben Burton
 */
public class QuestionAnswerEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	public QuestionAnswerEvent(Object source) {
		super(source);
	}

}
