package com.openlibrary.domain;

import java.io.Serializable;
import java.util.List;


public class BookList implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Book> Books = null;

	public BookList(List<Book> Books) {
		this.Books = Books;
	}

	public List<Book> getBooks() {
		return Books;
	}

	public void setBooks(List<Book> Books) {
		this.Books = Books;
	}

	public int getSize() {
		return Books.size();
	}

}

