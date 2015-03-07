package com.openlibrary.controllers;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.User;
import com.openlibrary.managers.BookManager;
import com.openlibrary.managers.UserManager;
import com.openlibrary.util.ApplicationSecurityManager;





/**
 * Controller for the Book List screen.
 * 
 */
public class EditProfileController extends SimpleFormController {
	UserManager userManager;
	ApplicationSecurityManager applicationSecurityManager;
	public static final String USR_KEY = "user";


	private String successView;

	public Object formBackingObject(HttpServletRequest request) {
		User user = (User)applicationSecurityManager.getUser(request);
		return user;
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
		User user = (User) command;
		userManager.saveUser(user);
		user = userManager.getUserByUsername(user.getUsername());
		applicationSecurityManager.setUser(request, user);
		return new ModelAndView(getSuccessView()).addObject(USR_KEY, user);
	}
	
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(int.class, new IntgerPropertyEditor());
	}

	public UserManager getUserManager() {
		return userManager;
	}


	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}


	public ApplicationSecurityManager getApplicationSecurityManager() {
		return applicationSecurityManager;
	}


	public void setApplicationSecurityManager(
			ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}
	
	

}