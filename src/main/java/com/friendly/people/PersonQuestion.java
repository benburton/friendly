package com.friendly.people;

import java.awt.Dimension;
import java.awt.Image;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.swing.ImageIcon;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * A {@link Serializable} representation of a question.
 * 
 * @author Ben Burton
 */
@XStreamAlias("question")
public class PersonQuestion implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Dimension MAX_IMAGE_SIZE = new Dimension(300, 300);

	@XStreamAlias("answer")
	private Person answer;

	@XStreamAlias("options")
	private List<Person> options;

	public PersonQuestion(List<Person> options) {
		if (options == null || options.isEmpty()) {
			throw new IllegalArgumentException(
					"Cannot create a PersonQuestion with null or empty options.");
		}
		this.options = options;
	}

	public PersonQuestion(List<Person> options, Person answer) {
		this(options);
		setAnswer(answer);
	}

	public Person getAnswer() {
		return answer;
	}

	public void setAnswer(Person answer) {
		if (!options.contains(answer)) {
			throw new IllegalArgumentException(
					"Answer to a PersonQuestion must be in the collection of options.");
		}
		this.answer = answer;
	}

	public ImageIcon getAnswerImage() {
		try {
			ImageIcon unscaledImage = new ImageIcon(new URL(getAnswer()
					.getImagePath()));
			return scale(unscaledImage, MAX_IMAGE_SIZE);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public static ImageIcon scale(ImageIcon icon, Dimension maxDimension) {
		int maxWidth = (int) MAX_IMAGE_SIZE.getWidth();
		int maxHeight = (int) MAX_IMAGE_SIZE.getHeight();
		int iconWidth = icon.getIconWidth();
		int iconHeight = icon.getIconHeight();
		double xScale = (double) maxWidth / iconWidth;
		double yScale = (double) maxHeight / iconHeight;
		double scale = Math.min(xScale, yScale);
		int width = (int) (scale * iconWidth);
		int height = (int) (scale * iconHeight);

		return new ImageIcon(icon.getImage().getScaledInstance(width, height,
				Image.SCALE_DEFAULT));
	}

	public List<Person> getOptions() {
		return options;
	}

	public void setOptions(List<Person> options) {
		this.options = options;
	}

}
