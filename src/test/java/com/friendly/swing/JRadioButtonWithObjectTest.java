package com.friendly.swing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JRadioButtonWithObjectTest {

	@Test
	public void testRadioButtonStoresObject() {
		Object object = givenAnObject();
		JRadioButtonWithObject<Object> button = whenButtonWithObjectIsInstantiated(object);
		thenButtonHasAssociatedObject(button, object);
	}

	private Object givenAnObject() {
		return new Object();
	}

	private JRadioButtonWithObject<Object> whenButtonWithObjectIsInstantiated(
			Object object) {
		return new JRadioButtonWithObject<Object>("", object);
	}

	private void thenButtonHasAssociatedObject(
			JRadioButtonWithObject<Object> button, Object object) {
		assertEquals(object, button.getObject());
	}

}
