package com.openlibrary.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.openlibrary.controllers.AddBookController;
import com.openlibrary.controllers.AddBooksWantedController;
import com.openlibrary.domain.Book;
import com.openlibrary.domain.BooksWanted;
import com.openlibrary.domain.User;
import com.openlibrary.managers.BookManager;
import com.openlibrary.managers.BooksWantedManager;
import com.openlibrary.managers.UserManager;
import com.openlibrary.util.ApplicationSecurityManager;



/**
 * Test class for AddBookController
 */
public class AddBooksWantedControllerTest extends TestCase {
	private MockHttpServletRequest mockHttpServletRequest;
	private AddBooksWantedController addBooksWantedController;
	private BookManager bookManager;
	private BooksWantedManager booksWantedManager;
	private UserManager userManager;
	private ApplicationSecurityManager applicationSecurityManager;	
	private final String ISBN = "5";
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
	public void testAddBookWanted() throws Exception {
		BooksWanted bookWanted = (BooksWanted)addBooksWantedController.formBackingObject(mockHttpServletRequest);		
		assertNotNull(bookWanted);
		
		ModelAndView modelAndView = addBooksWantedController.onSubmit(mockHttpServletRequest, null, bookWanted, null);
		assertNotNull(modelAndView.getModel());
		
		@SuppressWarnings("unchecked")
		BooksWanted bookWanted1 =  booksWantedManager.getBookWanted(bookWanted.getUser(), bookWanted.getBookId());
		assertNotNull(bookWanted1);
		
		bookId1 = bookWanted1.getBookId();
		booksWantedManager.deleteBookWanted(bookWanted1.getWantedId());

	}
	
	protected void setUp() throws Exception {
		addBooksWantedController = new AddBooksWantedController();
		mockHttpServletRequest = new MockHttpServletRequest("GET", "/addbookwanted.htm");
		bookManager = new BookManager();
		userManager = new UserManager();
		booksWantedManager = new BooksWantedManager();
		
		applicationSecurityManager = new ApplicationSecurityManager();
		applicationSecurityManager.setUser(mockHttpServletRequest, userManager.getUser(1));
		addBooksWantedController.setApplicationSecurityManager(applicationSecurityManager);
		addBooksWantedController.setBookManager(bookManager);
		addBooksWantedController.setBooksWantedManager(booksWantedManager);
		
		
/*
		
		*/
	}

	/**
	 * Delete test Book objects from DB.
	 */
	protected void tearDown() throws Exception {
		bookManager.deleteBookById(bookId1);
		super.tearDown();
		//bookManager.deleteBook(bookId2);
	}
}

