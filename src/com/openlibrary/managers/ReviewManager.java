package com.openlibrary.managers;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.BooksOwned;
import com.openlibrary.domain.Review;
import com.openlibrary.domain.User;
import com.openlibrary.util.HibernateUtil;


public class ReviewManager {
	
	@SuppressWarnings("unchecked")
	
	public List<Review> getAllReviews(){
		List<Review> reviewList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			reviewList = session.createQuery(
					"from Review").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return reviewList;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Review> getReviewsByUser(User user){
		List<Review> reviewList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			reviewList = session.createQuery(
					"from Review where user = ?").
					setEntity(0, user).list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return reviewList;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Review> getReviewByBook(Book book){
		List<Review> review = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			review = session.createQuery(
					"from Review where book = ?").
					setEntity(0, book).list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return review;
		
	}
	
	public void addReview(Review review){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.saveOrUpdate(review);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	public void deleteReview(int reviewId){
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			try {
				session.delete(session.load(BooksOwned.class, new Integer(reviewId)));//change to BooksOwned
				session.flush();
				session.getTransaction().commit();
			} catch (HibernateException e) {
				session.getTransaction().rollback();
				throw e;
			}
		
	}
	
}

