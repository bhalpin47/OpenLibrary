package com.openlibrary.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.openlibrary.controllers.AddBookController;
import com.openlibrary.controllers.AddBooksReadController;
import com.openlibrary.domain.Book;
import com.openlibrary.domain.BooksRead;
import com.openlibrary.domain.User;
import com.openlibrary.managers.BookManager;
import com.openlibrary.managers.BooksReadManager;
import com.openlibrary.managers.UserManager;
import com.openlibrary.util.ApplicationSecurityManager;



/**
 * Test class for AddBookController
 */
public class AddBooksReadControllerTest extends TestCase {
	private MockHttpServletRequest mockHttpServletRequest;
	private AddBooksReadController addBooksReadController;
	private BookManager bookManager;
	private BooksReadManager booksReadManager;
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
	public void testAddBookRead() throws Exception {
		BooksRead bookRead = (BooksRead)addBooksReadController.formBackingObject(mockHttpServletRequest);		
		assertNotNull(bookRead);
		
		ModelAndView modelAndView = addBooksReadController.onSubmit(mockHttpServletRequest, null, bookRead, null);
		assertNotNull(modelAndView.getModel());
		
		@SuppressWarnings("unchecked")
		BooksRead bookRead1 =  booksReadManager.getBookRead(bookRead.getUser(), bookRead.getBookId());
		assertNotNull(bookRead1);
		
		booksReadManager.deleteBookRead(bookRead1.getReadId());

	}
	
	protected void setUp() throws Exception {
		addBooksReadController = new AddBooksReadController();
		mockHttpServletRequest = new MockHttpServletRequest("GET", "/addbookread.htm");
		bookManager = new BookManager();
		userManager = new UserManager();
		booksReadManager = new BooksReadManager();
		
		applicationSecurityManager = new ApplicationSecurityManager();
		applicationSecurityManager.setUser(mockHttpServletRequest, userManager.getUser(1));
		addBooksReadController.setApplicationSecurityManager(applicationSecurityManager);
		addBooksReadController.setBookManager(bookManager);
		addBooksReadController.setBooksReadManager(booksReadManager);
		
		
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
