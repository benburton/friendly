package com.friendly.swing;

import javax.swing.JRadioButton;

/**
 * Simple {@link JRadioButton} containing a typed reference to an associated
 * {@link Object}.
 * 
 * @author Ben Burton
 * 
 * @param <T>
 *            the type of the associated {@link Object}
 */
public class JRadioButtonWithObject<T> extends JRadioButton {

	private static final long serialVersionUID = 1L;

	private final T object;

	public JRadioButtonWithObject(String text, T object) {
		super(text);
		this.object = object;
	}

	public T getObject() {
		return object;
	}

}
