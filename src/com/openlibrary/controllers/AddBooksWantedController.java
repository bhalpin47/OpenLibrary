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
import com.openlibrary.domain.BookStatus;
import com.openlibrary.domain.BooksWanted;
import com.openlibrary.domain.Review;
import com.openlibrary.domain.User;
import com.openlibrary.managers.BookManager;
import com.openlibrary.managers.BooksWantedManager;
import com.openlibrary.managers.ReviewManager;
import com.openlibrary.managers.UserManager;
import com.openlibrary.util.ApplicationSecurityManager;





/**
 * Controller for the Book List screen.
 * 
 */
public class AddBooksWantedController extends SimpleFormController {
	ApplicationSecurityManager applicationSecurityManager;
	private BookManager bookManager;
	private BooksWantedManager booksWantedManager;
	public static final String BID = "bid";
	public static final String USR_KEY = "user";


	private String successView;

	public Object formBackingObject(HttpServletRequest request) {
		User myUser = (User) applicationSecurityManager.getUser(request);
		if (request.getParameter(BID) != null && request.getParameter(BID).trim().length() > 0){
			Book book = bookManager.getBookById(Integer.parseInt(request.getParameter(BID)));
			return new BooksWanted(book, myUser);
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
		BooksWanted bookWanted = new BooksWanted(book, myUser);
		return bookWanted;
	}

	
	/**
	 * Accepts parameter from form data and saves new book object to database
	 */
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) {
		BooksWanted bookWanted = (BooksWanted) command;
		User myUser = (User)applicationSecurityManager.getUser(request);
		if(bookManager.getBookByIsbn(bookWanted.getBook().getIsbn()) == null){			
			bookManager.saveBook(bookWanted.getBook());
		}
		bookWanted.setBook(bookManager.getBookById(bookWanted.getBookId()));
		booksWantedManager.addBookWanted(bookWanted);
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
	
	public BooksWantedManager getBooksWantedManager() {
		return booksWantedManager;
	}

	public void setBooksWantedManager(BooksWantedManager booksWantedManager) {
		this.booksWantedManager = booksWantedManager;
	}


	public ApplicationSecurityManager getApplicationSecurityManager() {
		return applicationSecurityManager;
	}


	public void setApplicationSecurityManager(
			ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}
	
	

}

