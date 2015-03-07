package com.openlibrary.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.openlibrary.domain.User;
import com.openlibrary.managers.ReviewManager;
import com.openlibrary.managers.ReviewManager;
import com.openlibrary.util.ApplicationSecurityManager;




/**
 * Controller for the Review List screen.
 * 
 */
public class ReviewListController implements Controller {
	private ReviewManager reviewManager;
	private ApplicationSecurityManager applicationSecurityManager;

	public static final String MAP_KEY = "reviews";
	public static final String USR_KEY = "user";

	private String successView;

	/**
	 * Returns a list of Review database objects in ModelAndView.
	 * 
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List reviews = reviewManager.getAllReviews();
		User myUser = (User)applicationSecurityManager.getUser(request);
		return new ModelAndView(getSuccessView(), MAP_KEY, reviews).addObject(USR_KEY, myUser);
	}

	public ReviewManager getReviewManager() {
		return reviewManager;
	}

	public void setReviewManager(ReviewManager reviewManager) {
		this.reviewManager = reviewManager;
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
