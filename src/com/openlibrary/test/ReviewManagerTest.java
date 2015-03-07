package com.openlibrary.test;

import java.util.List;

import com.openlibrary.domain.Review;
import com.openlibrary.managers.ReviewManager;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ReviewManagerTest extends TestCase {
	ReviewManager reviewManager = new ReviewManager();
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(suite());
		
	}
	
	public static Test suite(){
		return new TestSuite(ReviewManagerTest.class);
	}
	public void testGetAllReviews(){
		List<Review> reviewList = reviewManager.getAllReviews();
		assertNotNull(reviewList);
		assertTrue(reviewList.size() > 0);
	}
	
	public void testGetReviewsByUserId(){
		List<Review> reviewList = reviewManager.getAllReviews();
		assertNotNull(reviewList);
		Review review = reviewList.get(0);
		assertNotNull(review);
		reviewList = reviewManager.getReviewsByUser(review.getUser());
		for(int i = 0; i < reviewList.size(); i++){
			assertEquals(review.getUser().getUserId(), reviewList.get(i).getUser().getUserId());
		}
	}
	
	public void testGetReviewByBook(){
		List<Review> reviewList = reviewManager.getAllReviews();
		assertNotNull(reviewList);
		Review review = null;
		for(int i = 0; i < reviewList.size(); i++){
			review = reviewList.get(i);
			assertNotNull(review);
			assertNotNull(reviewManager.getReviewByBook(review.getBook()));
		}
	}
}
