
package com.openlibrary.test;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.BookCondition;
import com.openlibrary.domain.BookStatus;
import com.openlibrary.domain.BooksOwned;
import com.openlibrary.domain.Review;
import com.openlibrary.domain.User;
import com.openlibrary.managers.BookManager;
import com.openlibrary.managers.BooksOwnedManager;
import com.openlibrary.managers.UserManager;

public class BooksOwnedManagerTest extends TestCase {
	BooksOwnedManager booksOwnedManager = new BooksOwnedManager();
	BookManager bookManager = new BookManager();
	UserManager userManager = new UserManager();

	public static void main(String[] args){
		junit.textui.TestRunner.run(suite());

	}

	public static Test suite(){
		return new TestSuite(BooksOwnedManagerTest.class);
	}
	public void testGetAllBooks(){
		List<BooksOwned> bookList = booksOwnedManager.getAllBooksOwned();
		assertNotNull(bookList);
		assertTrue(bookList.size() > 0);
	}

	public void testGetBooksOwnedByUserId(){
		List<BooksOwned> bookList = booksOwnedManager.getAllBooksOwned();
		assertNotNull(bookList);
		BooksOwned book = bookList.get(0);
		assertNotNull(book);
		bookList = booksOwnedManager.getBooksOwned(book.getUser());
		for(int i = 0; i < bookList.size(); i++){
			assertEquals(book.getUser().getUserId(), bookList.get(i).getUser().getUserId());
		}
	}
	
	public void testGetSingleBooksOwned(){
		List<BooksOwned> bookList = booksOwnedManager.getAllBooksOwned();
		assertNotNull(bookList);
		BooksOwned book1 = bookList.get(0);
		assertNotNull(book1);
		User user = book1.getUser();
		assertNotNull(user);
		BooksOwned book2 = booksOwnedManager.getBookOwned(user, book1.getBook().getBookId());
		assertEquals(book1.getBook().getBookId(), book2.getBook().getBookId());	
	}
	
	public void testSaveAndDeleteBookOwned(){
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
		//bookManager.deleteBook(bookInDB.getBookId());
		booksOwnedManager.addBookOwned(bookInDB, bookInDB.getUser(), BookCondition.AS_NEW);
		
		BooksOwned ownedInDB = booksOwnedManager.getBookOwned(bookInDB.getUser(), bookInDB.getBookId());
		assertNotNull(ownedInDB);
		
		assertEquals(bookInDB.getBookId(), ownedInDB.getBook().getBookId());
		
		booksOwnedManager.deleteBookOwned(ownedInDB.getOwnedId());
		assertNull(booksOwnedManager.getBookOwned(ownedInDB.getUser(), ownedInDB.getBook().getBookId()));
		
		bookManager.deleteBookById(bookInDB.getBookId());
		
	}

}
