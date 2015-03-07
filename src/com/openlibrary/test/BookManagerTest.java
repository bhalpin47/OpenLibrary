package com.openlibrary.test;

import java.sql.Date;
import java.util.List;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.BookStatus;
import com.openlibrary.domain.Review;
import com.openlibrary.domain.User;
import com.openlibrary.managers.BookManager;
import com.openlibrary.managers.UserManager;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BookManagerTest extends TestCase {
	BookManager bookManager = new BookManager();
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(suite());
		
	}
	
	public static Test suite(){
		return new TestSuite(BookManagerTest.class);
	}
	public void testGetAllBooks(){
		List<Book> bookList = bookManager.getAllBooks();
		assertNotNull(bookList);
		assertTrue(bookList.size() > 0);
	}
	
	public void testGetBooksByUserId(){
		List<Book> bookList = bookManager.getAllBooks();
		assertNotNull(bookList);
		Book book = bookList.get(0);
		assertNotNull(book);
		bookList = bookManager.getBooks(book.getUser());
		for(int i = 0; i < bookList.size(); i++){
			assertEquals(book.getUser().getUserId(), bookList.get(i).getUser().getUserId());
		}
	}
	
	public void testGetBookByIsbn(){
		List<Book> bookList = bookManager.getAllBooks();
		assertNotNull(bookList);
		Book book = null;
		for(int i = 0; i < bookList.size(); i++){
			book = bookList.get(i);
			assertNotNull(book);
			Book book2 = bookManager.getBookByIsbn(book.getIsbn());
			assertNotNull(book2);
			System.out.println("***************** isbn= " + book2.getIsbn() + "**********************");
		}
	}
	
	public void testSaveAndDeleteBook(){
		String isbn = "1";
		String title = "1"; 
		String author = "1"; 
		String publisher = "1"; 
		int edition = 1;
		int pages = 1;
		String description = "1";
		User user = new UserManager().getUserByUsername("root");
		String status = BookStatus.PENDING;
		List<Book> bookList = null;
		List<Review> reviewList = null;
		Book book = bookManager.getBookByIsbn(isbn);
		if (book == null) {
			book = new Book(isbn, title, author, publisher, edition, pages, bookList, description, reviewList, user, status);
			bookManager.saveBook(book);
		} else {
			System.out.println("Book record found: " + book.getBookId());
			
		}
		
		
		
		Book bookInDB = bookManager.getBookByIsbn(isbn);
		assertNotNull(bookInDB);
		assertEquals(book.getAuthor(), bookInDB.getAuthor());
		assertEquals(book.getTitle(), bookInDB.getTitle());
		assertEquals(book.getPublisher(), bookInDB.getPublisher());
		assertEquals(book.getEdition(), bookInDB.getEdition());
		assertEquals(book.getDescription(), bookInDB.getDescription());
		assertEquals(book.getUser().getUserId(), bookInDB.getUser().getUserId());
		bookManager.deleteBookById(bookInDB.getBookId());
		assertNull(bookManager.getBookById(bookInDB.getBookId()));
		
	}
}
