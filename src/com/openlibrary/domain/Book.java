package com.openlibrary.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private int bookId;				// Unique identifier for each book in the system
	private String isbn;				// isbn number
	private String title;			// book's title
	private String author;			// "" author
	private String publisher;		// "" publisher
	private int edition;			// edition of book
	private int pages;				// number of pages
	private List<Book> series;		// if book is part of a series, list of other books in the series
	private String description;		// a short description/ synopsis of book
	private List<Review> reviews;	// user or published reviews of a book
	private User user;				// user who added the book to the system
	private String statusCode;	// book's status upon being submitted for addition to the library
	private BookStatus bookStatus;
	
	public Book(){}
	
	public Book(Book book){
		this.bookId = book.bookId;
		this.isbn = book.isbn;
		this.title = book.title;
		this.author = book.author;
		this.publisher = book.publisher;
		this.edition = book.edition;
		this.pages = book.pages;
		this.series = book.series;
		this.description = book.description;
		this.reviews = book.reviews;
		this.user = book.user;
		this.statusCode = book.statusCode;
	}


	public Book(String isbn, String title, String author,
			String publisher, int edition, int pages,
			List<Book> series, String description, List<Review> reviews,
			 User user, String statusCode) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.edition = edition;
		this.pages = pages;
		this.series = series;
		this.description = description;
		this.reviews = reviews;
		this.user = user;
		this.statusCode = statusCode;
	}
	
	
	
	public Book getBook(){
		return this;
	}
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public BookStatus getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(BookStatus bookStatus) {
		this.bookStatus = bookStatus;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getEdition() {
		return edition;
	}
	public void setEdition(int edition) {
		this.edition = edition;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public List<Book> getSeries() {
		return series;
	}
	public void setSeries(List<Book> series) {
		this.series = series;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	
	

	
}
