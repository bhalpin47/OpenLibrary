package com.openlibrary.controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.openlibrary.domain.User;
import com.openlibrary.managers.UserManager;
import com.openlibrary.util.ApplicationSecurityManager;





/**
 * Controller for the Book List screen.
 * 
 */
public class HomeController extends SimpleFormController {
	private UserManager userManager;
	private ApplicationSecurityManager applicationSecurityManager;
	public static final String UID = "uid";


	private String successView;

	public Object formBackingObject(HttpServletRequest request) {
		return new User();
	}
	
	/** Forwards to success view, if already logged in */
	public ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors,
			Map controlModel) throws Exception {
		if (applicationSecurityManager.getUser(request) != null)
			return new ModelAndView(getSuccessView());

		return super.showForm(request, response, errors, controlModel);
	}

	/** Validates user/password against database */
	public void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws Exception {
		if (errors.hasErrors())
			return;

		User formUser = (User) command;
		User dbUser = (User) command;
		if ((dbUser = userManager.getUserByUsername(formUser.getUsername())) == null)
			errors.reject("error.login.invalid");
		else {
			if (formUser.getPassword().equals(dbUser.getPassword())) {
				applicationSecurityManager.setUser(request, dbUser);
			} else {
				errors.reject("error.login.invalid");
			}
		}
	}

	/**
	 * returns ModelAndView(getSuccessView())
	 */
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) {
		return new ModelAndView(getSuccessView());
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
	
	public void setApplicationSecurityManager(ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}

}

