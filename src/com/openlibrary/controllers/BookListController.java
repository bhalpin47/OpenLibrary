package com.openlibrary.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.openlibrary.domain.User;
import com.openlibrary.managers.BookManager;
import com.openlibrary.util.ApplicationSecurityManager;




/**
 * Controller for the Book List screen.
 * 
 */
public class BookListController implements Controller {
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
		List books = bookManager.getAllBooks();
		User myUser = (User)applicationSecurityManager.getUser(request);
		return new ModelAndView(getSuccessView(), MAP_KEY, books).addObject(USR_KEY, myUser);
	}

	public BookManager getBookManager() {
		return bookManager;
	}

	public void setBookManager(BookManager bookManager) {
		this.bookManager = bookManager;
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
