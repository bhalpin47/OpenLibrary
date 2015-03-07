package com.openlibrary.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.BooksWanted;
import com.openlibrary.domain.User;
import com.openlibrary.managers.BookManager;
import com.openlibrary.managers.BooksWantedManager;
import com.openlibrary.util.ApplicationSecurityManager;




/**
 * Controller for the Book List screen.
 * 
 */
public class BooksWantedController implements Controller {
	private BooksWantedManager booksWantedManager;
	private BookManager bookManager;



	private ApplicationSecurityManager applicationSecurityManager;

	public static final String MAP_KEY = "books";
	public static final String USR_KEY = "user";

	private String successView;

	/**
	 * Returns a list of Book database objects in ModelAndView.
	 * 
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User myUser = (User) applicationSecurityManager.getUser(request);
		List<BooksWanted> booksWanted = booksWantedManager.getBooksWanted(myUser);
		List<Book> books = new ArrayList<Book>();
		for(int i = 0; i < booksWanted.size(); i++){
			books.add(bookManager.getBookById(booksWanted.get(i).getBook().getBookId()));
		}
		return new ModelAndView(getSuccessView(), MAP_KEY, books).addObject(USR_KEY, myUser);
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
	
	public String getSuccessView() {
		return successView;
	}

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public ApplicationSecurityManager getApplicationSecurityManager() {
		return applicationSecurityManager;
	}

	public void setApplicationSecurityManager(
			ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}
	
	
}