package com.openlibrary.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.Review;
import com.openlibrary.domain.User;
import com.openlibrary.managers.BookManager;
import com.openlibrary.managers.ReviewManager;
import com.openlibrary.util.ApplicationSecurityManager;





/**
 * Controller for the Review List screen.
 * 
 */
public class WriteReviewController extends SimpleFormController {
	ApplicationSecurityManager applicationSecurityManager;
	private ReviewManager reviewManager;
	private BookManager bookManager;
	public static final String BID = "bid";
	public static final String USR_KEY = "user";


	private String successView;

	public Object formBackingObject(HttpServletRequest request) {
		Book book = null;
		if (request.getParameter(BID) != null && request.getParameter(BID).trim().length() > 0)
			book = bookManager.getBookById(Integer.parseInt(request.getParameter(BID)), false);
		User myUser = (User) applicationSecurityManager.getUser(request);
		Review review = new Review();
		review.setBook(book);
		review.setTitle("Title");
		review.setContent("");
		review.setRating(0);
		review.setUser(myUser);
		return review;
	}
	/**
	 * Returns a list of Review database objects in ModelAndView.
	 * 
	 */

	
	public BookManager getBookManager() {
		return bookManager;
	}


	public void setBookManager(BookManager bookManager) {
		this.bookManager = bookManager;
	}


	/**
	 * Accepts parameter from form data and saves new review object to database
	 */
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) {
		Review review = (Review) command;
		User myUser = (User)applicationSecurityManager.getUser(request);
		reviewManager.addReview(review);
		return new ModelAndView(getSuccessView()).addObject(USR_KEY, myUser);
	}
	
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(int.class, new IntgerPropertyEditor());
	}

	public ReviewManager getReviewManager() {
		return reviewManager;
	}

	public void setReviewManager(ReviewManager reviewManager) {
		this.reviewManager = reviewManager;
	}


	public ApplicationSecurityManager getApplicationSecurityManager() {
		return applicationSecurityManager;
	}


	public void setApplicationSecurityManager(
			ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}
	
	

}
