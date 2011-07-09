package com.friendly.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import com.friendly.people.Person;
import com.friendly.people.PersonQuestion;
import com.friendly.swing.event.QuestionAnswerEvent;
import com.friendly.swing.event.QuestionAnswerEventListener;

/**
 * A {@link JPanel} used to select an answer to a {@link PersonQuestion}.
 * 
 * @author Ben Burton
 */
public class QuestionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final PersonQuestion question;

	private List<JRadioButtonWithObject<Person>> buttons = new ArrayList<JRadioButtonWithObject<Person>>();

	protected EventListenerList eventListenerList = new EventListenerList();

	public QuestionPanel(PersonQuestion question) {
		super(new BorderLayout());
		this.question = question;
		add(getImageLabel(question), BorderLayout.CENTER);
		add(getRadioButtons(), BorderLayout.LINE_END);
	}

	private JLabel getImageLabel(PersonQuestion question) {
		JLabel label = new JLabel("", question.getAnswerImage(), JLabel.CENTER);
		return label;
	}

	private JPanel getRadioButtons() {
		List<Person> people = question.getOptions();
		ButtonGroup buttonGroup = new ButtonGroup();
		JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
		for (Person person : people) {
			JRadioButtonWithObject<Person> radioButton = createRadioButton(person);
			buttonPanel.add(radioButton);
			buttonGroup.add(radioButton);
			buttons.add(radioButton);
		}
		return buttonPanel;
	}

	private JRadioButtonWithObject<Person> createRadioButton(Person person) {
		final JRadioButtonWithObject<Person> radioButton = new JRadioButtonWithObject<Person>(
				person.getName(), person);
		radioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				submit(radioButton);
			}
		});
		return radioButton;
	}

	private void submit(JRadioButtonWithObject<Person> userChoice) {
		for (JRadioButtonWithObject<Person> button : buttons) {
			if (button == userChoice) {
				if (isAnswerCorrect(userChoice.getObject())) {
					userChoice.setForeground(Color.GREEN);
				} else {
					userChoice.setForeground(Color.RED);
				}
			} else if (button.getObject().equals(question.getAnswer())) {
				button.setForeground(Color.GREEN);
			} else {
				button.setEnabled(false);
			}
		}
		fireQuestionAnswerEvent(new QuestionAnswerEvent(this));
	}

	private boolean isAnswerCorrect(Person person) {
		return question.getAnswer().equals(person);
	}

	public void addQuestionAnswerEventListener(
			QuestionAnswerEventListener questionAnswerEventListener) {
		eventListenerList.add(QuestionAnswerEventListener.class,
				questionAnswerEventListener);
	}

	public void removeQuestionAnswerEventListener(
			QuestionAnswerEventListener questionAnswerEventListener) {
		eventListenerList.remove(QuestionAnswerEventListener.class,
				questionAnswerEventListener);
	}

	private void fireQuestionAnswerEvent(QuestionAnswerEvent questionAnswerEvent) {
		Object[] eventListeners = eventListenerList.getListenerList();
		for (int i = 0; i < eventListeners.length; i += 2) {
			if (eventListeners[i] == QuestionAnswerEventListener.class) {
				((QuestionAnswerEventListener) eventListeners[i + 1])
						.questionAnswerEventOccurred(questionAnswerEvent);
			}
		}
	}

}
