package com.openlibrary.controllers;

import java.util.List;

import org.springframework.validation.Errors;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.BookList;

/**

 */
public class ApproveValidator implements org.springframework.validation.Validator {

	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return clazz.equals(BookList.class);
	}

	/**
	 * Validates the Book command object. 
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 *      org.springframework.validation.Errors)
	 */
	public void validate(Object command, Errors errors) {
		BookList appBooks = (BookList) command;
		List<Book> books = appBooks.getBooks();
		if (books == null || books.size() <= 0)
			return;
		for (Book t : books)
			if (t.getStatusCode() == null || t.getStatusCode().trim().length() < 1)
				errors.reject("error.administration.invalidapprove");
	}

}
