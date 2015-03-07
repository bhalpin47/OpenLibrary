package com.openlibrary.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.BookCondition;
import com.openlibrary.domain.BookStatus;
import com.openlibrary.domain.BooksOwned;
import com.openlibrary.domain.User;
import com.openlibrary.managers.BookConditionManager;
import com.openlibrary.managers.BookManager;
import com.openlibrary.managers.BooksOwnedManager;
import com.openlibrary.managers.UserManager;
import com.openlibrary.util.ApplicationSecurityManager;





/**
 * Controller for the Book List screen.
 * 
 */
public class AddBooksOwnedController extends SimpleFormController {
	ApplicationSecurityManager applicationSecurityManager;
	private BookManager bookManager;
	private BooksOwnedManager	booksOwnedManager;
	private BookConditionManager bookConditionManager;
	public static final String BID = "bid";
	public static final String USR_KEY = "user";


	private String successView;

	public Object formBackingObject(HttpServletRequest request) {
		User myUser = (User) applicationSecurityManager.getUser(request);
		if (request.getParameter(BID) != null && request.getParameter(BID).trim().length() > 0){
			Book book = bookManager.getBookById(Integer.parseInt(request.getParameter(BID)));
			BooksOwned bookOwned = new BooksOwned(book, myUser, BookCondition.GOOD);
			return bookOwned;
		}
		Book book = bookManager.getBookByIsbn("0");
		if(book == null){
			book = new Book();
			book.setIsbn("0");
			book.setTitle("Title");
			book.setAuthor("Author");
			book.setPublisher("Publisher");
			book.setPages(1);
			book.setDescription("Description");
			book.setUser(new UserManager().getUser(1));
			book.setStatusCode(BookStatus.PENDING);
			bookManager.saveBook(book);
			book = bookManager.getBookByIsbn("0");
		}
			bookManager.deleteBookByIsbn("0");
		BooksOwned bookOwned = new BooksOwned(book, myUser, BookCondition.GOOD);
		return bookOwned;
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
		BooksOwned bookOwned = (BooksOwned) command;
		User myUser = (User)applicationSecurityManager.getUser(request);
		if(bookManager.getBookByIsbn(bookOwned.getBook().getIsbn()) == null){			
			bookManager.saveBook(bookOwned.getBook());
		}
		bookOwned.setBook(bookManager.getBookById(bookOwned.getBookId()));
		booksOwnedManager.addBookOwned(bookOwned);
		return new ModelAndView(getSuccessView()).addObject(USR_KEY, myUser);
	}
	
	public Map referenceData(HttpServletRequest request) throws Exception {
		HashMap model = new HashMap();
		model.put("conditions", bookConditionManager.getAllBookConditions());
		return model;
	}
	
	
	
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(int.class, new IntgerPropertyEditor());
	}

	
	public BookConditionManager getBookConditionManager() {
		return bookConditionManager;
	}


	public void setBookConditionManager(BookConditionManager bookConditionManager) {
		this.bookConditionManager = bookConditionManager;
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
