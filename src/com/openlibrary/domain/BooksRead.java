package com.openlibrary.domain;

import java.io.Serializable;

public class BooksRead implements Serializable {

	private static final long serialVersionUID = 1L;
	private int readId;
	private Book book;
	private User user;

	
	
	public BooksRead(){}
	
	public BooksRead(Book book, User user) {
		this.book = book;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getBookId() {
		return book.getBookId();
	}

	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getReadId() {
		return readId;
	}
	public void setReadId(int readId) {
		this.readId = readId;
	}

}
