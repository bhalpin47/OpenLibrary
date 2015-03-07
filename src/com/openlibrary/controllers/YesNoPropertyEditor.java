package com.openlibrary.controllers;

import java.beans.PropertyEditorSupport;

import com.openlibrary.domain.BookStatus;

/**
 * Property editor for the Enter Hours screen; registered in the
 * EnterHoursController class.
 * 
 * @author Anil Hemrajani
 * @see com.visualpatterns.timex.controller.EnterHoursController
 */
public class YesNoPropertyEditor extends PropertyEditorSupport {

	/**
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 * @return Yes or No
	 */
	public String getAsText() {
		String value =  (String)getValue();
		String newValue = "No";
		if (value.equalsIgnoreCase(BookStatus.APPROVED))
			newValue = "Yes";
		return newValue;
	}

	/**
	 * Saves timesheet status code.
	 * 
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			String newValue = BookStatus.PENDING;
			if (text.equalsIgnoreCase("Yes")) {
				newValue = BookStatus.APPROVED;
			} else if (text.equalsIgnoreCase("No")) {
				newValue = BookStatus.DENIED;
			}
			setValue(newValue);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid option: " + text);
		}
	}

}
