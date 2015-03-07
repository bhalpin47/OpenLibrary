package com.openlibrary.domain;

import java.io.Serializable;

public class BooksWanted implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int wantedId;
	private Book book;
	private User user;
	
	public BooksWanted(){}
	
	public BooksWanted(Book book, User user) {
		this.book = book;
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


	public int getWantedId() {
		return wantedId;
	}
	public void setWantedId(int wantedId) {
		this.wantedId = wantedId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

}
