package com.openlibrary.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.openlibrary.controllers.AddBookController;
import com.openlibrary.controllers.AddBooksOwnedController;
import com.openlibrary.domain.Book;
import com.openlibrary.domain.BooksOwned;
import com.openlibrary.domain.User;
import com.openlibrary.managers.BookManager;
import com.openlibrary.managers.BooksOwnedManager;
import com.openlibrary.managers.UserManager;
import com.openlibrary.util.ApplicationSecurityManager;



/**
 * Test class for AddBookController
 */
public class AddBooksOwnedControllerTest extends TestCase {
	private MockHttpServletRequest mockHttpServletRequest;
	private AddBooksOwnedController addBooksOwnedController;
	private BookManager bookManager;
	private BooksOwnedManager booksOwnedManager;
	private UserManager userManager;
	private ApplicationSecurityManager applicationSecurityManager;	
	private final String ISBN = "0";
	private static int bookId1;

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		return new TestSuite(AddBookControllerTest.class);
	}
	
	public void testFormBackingObject(){
		
	}

	/**
	 * Test GET of BookList screen for an employee
	 */
	public void testAddBookOwned() throws Exception {
		BooksOwned bookOwned = (BooksOwned)addBooksOwnedController.formBackingObject(mockHttpServletRequest);		
		assertNotNull(bookOwned);
		
		ModelAndView modelAndView = addBooksOwnedController.onSubmit(mockHttpServletRequest, null, bookOwned, null);
		assertNotNull(modelAndView.getModel());
		
		@SuppressWarnings("unchecked")
		BooksOwned bookOwned1 =  booksOwnedManager.getBookOwned(bookOwned.getUser(), bookOwned.getBookId());
		assertNotNull(bookOwned1);
		
		booksOwnedManager.deleteBookOwned(bookOwned1.getOwnedId());

	}
	
	protected void setUp() throws Exception {
		addBooksOwnedController = new AddBooksOwnedController();
		mockHttpServletRequest = new MockHttpServletRequest("GET", "/addbookowned.htm");
		bookManager = new BookManager();
		userManager = new UserManager();
		booksOwnedManager = new BooksOwnedManager();
		
		applicationSecurityManager = new ApplicationSecurityManager();
		applicationSecurityManager.setUser(mockHttpServletRequest, userManager.getUser(1));
		addBooksOwnedController.setApplicationSecurityManager(applicationSecurityManager);
		addBooksOwnedController.setBookManager(bookManager);
		addBooksOwnedController.setBooksOwnedManager(booksOwnedManager);
		
		
/*
		
		*/
	}

	/**
	 * Delete test Book objects from DB.
	 */
	protected void tearDown() throws Exception {
		bookManager.deleteBookByIsbn("0");
		super.tearDown();
		//bookManager.deleteBook(bookId2);
	}
}
