package com.openlibrary.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.BookCondition;
import com.openlibrary.domain.BookStatus;
import com.openlibrary.domain.User;
import com.openlibrary.managers.BookManager;
import com.openlibrary.managers.BooksOwnedManager;
import com.openlibrary.util.ApplicationSecurityManager;





/**
 * Controller for the Book List screen.
 * 
 */
public class AddBookController extends SimpleFormController {
	ApplicationSecurityManager applicationSecurityManager;
	private BookManager bookManager;
	private BooksOwnedManager	booksOwnedManager;
	public static final String BID = "bid";
	public static final String USR_KEY = "user";


	private String successView;

	public Object formBackingObject(HttpServletRequest request) {
		if (request.getParameter(BID) != null && request.getParameter(BID).trim().length() > 0)
			return bookManager.getBookById(Integer.parseInt(request.getParameter(BID)), false);
		User myUser = (User) applicationSecurityManager.getUser(request);
		Book book = new Book();
		book.setIsbn("0");
		book.setTitle("Title");
		book.setAuthor("Author");
		book.setPublisher("Publisher");
		book.setPages(0);
		book.setDescription("Description");
		book.setUser(myUser);
		book.setStatusCode(BookStatus.PENDING);
		return book;
	}
	/**
	 * Returns a list of Book database objects in ModelAndView.
	 * 
	 */

	
	/**
	 * Accepts parameter from form data and saves new book object to database
	 */
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) {
		Book book = (Book) command;
		User myUser = (User)applicationSecurityManager.getUser(request);
		bookManager.saveBook(book);
		return new ModelAndView(getSuccessView()).addObject(USR_KEY, myUser);
	}
	
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(int.class, new IntgerPropertyEditor());
	}

	public BookManager getBookManager() {
		return bookManager;
	}

	public void setBookManager(BookManager bookManager) {
		this.bookManager = bookManager;
	}
	
	public BooksOwnedManager getBooksOwnedManager() {
		return booksOwnedManager;
	}

	public void setBooksOwnedManager(BooksOwnedManager booksOwnedManager) {
		this.booksOwnedManager = booksOwnedManager;
	}


	public ApplicationSecurityManager getApplicationSecurityManager() {
		return applicationSecurityManager;
	}


	public void setApplicationSecurityManager(
			ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}
	
	

}
