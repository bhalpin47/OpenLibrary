package com.openlibrary.controllers;

import org.springframework.validation.Errors;

import com.openlibrary.domain.User;




public class LoginValidator implements org.springframework.validation.Validator {
	public boolean supports(Class clazz) {
		return clazz.equals(User.class);
	}

	/**
	 * Validates an User command object. Ensures that userId is greater
	 * than zero and that a password is specified.
	 * 
	 * @see User
	 */
	public void validate(Object command, Errors errors) {
		User user = (User) command;
		if (user == null)
			return;

		String username = user.getUsername();
		String password = user.getPassword();

		if (username.length() < 1)
			errors.reject("error.login.invalid");
		else if (password == null || password.trim().length() < 1 || password.trim().length() > 10)
			errors.reject("error.login.invalid");
	}
}
