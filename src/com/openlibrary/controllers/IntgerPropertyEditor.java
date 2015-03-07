package com.openlibrary.controllers;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditorSupport;

public class IntgerPropertyEditor extends PropertyEditorSupport {


	@Override
	public String getAsText() {
		Integer value = (Integer) getValue();
		return value.toString();
	}


	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			int newValue = Integer.parseInt(text);
			setValue(newValue);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Invalid number: " + text);
		}

	}



}
