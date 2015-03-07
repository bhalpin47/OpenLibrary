package com.openlibrary.test;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.openlibrary.controllers.AddBookController;
import com.openlibrary.domain.Book;
import com.openlibrary.domain.BookStatus;
import com.openlibrary.domain.Review;
import com.openlibrary.domain.User;
import com.openlibrary.managers.BookManager;
import com.openlibrary.managers.UserManager;
import com.openlibrary.util.ApplicationSecurityManager;



/**
 * Test class for AddBookController
 */
public class AddBookControllerTest extends TestCase {
	private MockHttpServletRequest mockHttpServletRequest;
	private AddBookController addBookController;
	private BookManager bookManager;
	private UserManager userManager;
	private ApplicationSecurityManager applicationSecurityManager;	
	private final String ISBN = "5";
	private static int bookId1;
	private Book book1;

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		return new TestSuite(AddBookControllerTest.class);
	}
	
	public void testFormBackingObject(){
		Book book = (Book)addBookController.formBackingObject(mockHttpServletRequest);		
		assertNotNull(book);
	}

	/**
	 * Test GET of BookList screen for an employee
	 */
	public void testAddBook() throws Exception {
		
		
		ModelAndView modelAndView = addBookController.onSubmit(mockHttpServletRequest, null, book1, null);
		assertNotNull(modelAndView.getModel());
		
		@SuppressWarnings("unchecked")
		Book book =  bookManager.getBookByIsbn(book1.getIsbn());
		assertNotNull(book);
		
		bookManager.deleteBookById(book.getBookId());

	}
	
	protected void setUp() throws Exception {
		mockHttpServletRequest = new MockHttpServletRequest("GET", "/addbook.htm");
		addBookController = new AddBookController();
		bookManager = new BookManager();
		userManager = new UserManager();
		
		applicationSecurityManager = new ApplicationSecurityManager();
		applicationSecurityManager.setUser(mockHttpServletRequest, userManager.getUser(1));
		addBookController.setApplicationSecurityManager(applicationSecurityManager);
		addBookController.setBookManager(bookManager);
		

		Book book = null;

		book = new Book();
		book.setIsbn(ISBN);
		book.setTitle("Title");
		book.setAuthor("Author");
		book.setPublisher("Publisher");
		book.setPages(1);
		book.setDescription("Description");
		book.setUser(new UserManager().getUser(1));
		book.setStatusCode("P");
		
	
		
		book1 = book;

	}

	/**
	 * Delete test Book objects from DB.
	 */
	protected void tearDown() throws Exception {
		
		super.tearDown();
		
	}
}
