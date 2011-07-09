package com.friendly.swing.event;

import java.util.EventListener;

/**
 * An {@link EventListener} for {@link QuestionAnswerEvent}s.
 * 
 * @author Ben Burton
 */
public interface QuestionAnswerEventListener extends EventListener {

	/**
	 * Method to be called whenever a {@link QuestionAnswerEvent} is fired.
	 * 
	 * @param questionAnswerEvent
	 *            the fired {@link QuestionAnswerEvent}
	 */
	public void questionAnswerEventOccurred(
			QuestionAnswerEvent questionAnswerEvent);

}
