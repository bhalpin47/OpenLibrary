package com.openlibrary.test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.openlibrary.controllers.BookListController;
import com.openlibrary.domain.*;
import com.openlibrary.managers.BookManager;
import com.openlibrary.util.ApplicationSecurityManager;


/**
 * Test class for TimeListController
 */
public class BookListControllerTest extends TestCase {
	private MockHttpServletRequest mockHttpServletRequest = null;
	private BookListController bookListController = null;
	private BookManager bookManager = new BookManager();
	private ApplicationSecurityManager applicationSecurityManager = new ApplicationSecurityManager();
	private final int USER_ID = 5;
	private static int bookId1;
	private static int bookId2;

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		return new TestSuite(BookListControllerTest.class);
	}

	/**
	 * Test GET of BookList screen for an employee
	 */
	public void testBrowseBooks() throws Exception {
		mockHttpServletRequest = new MockHttpServletRequest("GET", "/booklist.htm");


		// inject objects Spring normally would
		bookListController = new BookListController();
		bookListController.setBookManager(bookManager);
		bookListController.setApplicationSecurityManager(applicationSecurityManager);

		ModelAndView modelAndView = bookListController.handleRequest(mockHttpServletRequest, null);

		assertNotNull(modelAndView);
		assertNotNull(modelAndView.getModel());

		@SuppressWarnings("unchecked")
		List<Book> books = (List<Book>) modelAndView.getModel().get(BookListController.MAP_KEY);
		assertNotNull(books);

		Book book;
		for (int i = 0; i < books.size(); i++) {
			book = (Book) books.get(i);
			assertNotNull(book);
		}
	}

	/**
	 * Create test Book objects in DB
	 */
	/*
	protected void setUp() throws Exception {
		Book book = null;

		book = new Book();
		book.setEmployeeId(EMPLOYEE_ID);
		book.setPeriodEndingDate(new GregorianCalendar(2006, Calendar.MARCH, 04).getTime());
		book.setStatusCode("P");
		book.settDepartmentCode("IT");
		book.setMinutesMon(480);
		book.setMinutesTue(480);
		book.setMinutesWed(480);
		book.setMinutesThu(480);
		book.setMinutesFri(480);
		book.setMinutesSat(0);
		book.setMinutesSun(0);
		bookManager.saveBook(book);
		bookId1 = book.getBookId();

		book = new Book();
		book.setEmployeeId(EMPLOYEE_ID);
		book.setPeriodEndingDate(new GregorianCalendar(2006, Calendar.MARCH, 11).getTime());
		book.setStatusCode("A");
		book.settDepartmentCode("IT");
		book.setMinutesMon(480);
		book.setMinutesTue(480);
		book.setMinutesWed(480);
		book.setMinutesThu(480);
		book.setMinutesFri(480);
		book.setMinutesSat(0);
		book.setMinutesSun(0);
		bookManager.saveBook(book);
		bookId2 = book.getBookId();
	}

	/**
	 * Delete test Book objects from DB.
	 
	protected void tearDown() throws Exception {
		bookManager.deleteBook(bookId1);
		bookManager.deleteBook(bookId2);
	}
	*/
}
