package com.openlibrary.domain;

import java.io.Serializable;

public class BooksOwned implements Serializable {

	private static final long serialVersionUID = 1L;
	private int ownedId;
	private Book book;
	private User user;
	private String bookCondition;

	
	public BooksOwned(){}

	public BooksOwned(Book book, User owner, String bookCondition){
		this.book = book;
		user = owner;
		this.bookCondition = bookCondition;
	}
	


	public String getBookCondition() {
		return bookCondition;
	}

	public void setBookCondition(String bookCondition) {
		this.bookCondition = bookCondition;
	}

	public int getOwnedId() {
		return ownedId;
	}

	public void setOwnedId(int ownedId) {
		this.ownedId = ownedId;
	}

	
	public int getBookId(){
		return book.getBookId();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	

	
	
	
}
