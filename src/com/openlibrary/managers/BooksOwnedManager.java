package com.openlibrary.managers;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.BookCondition;
import com.openlibrary.domain.BooksOwned;
import com.openlibrary.domain.User;
import com.openlibrary.util.HibernateUtil;

public class BooksOwnedManager {

	
	
	@SuppressWarnings("unchecked")
	public List<BooksOwned> getAllBooksOwned(){
		List<BooksOwned> bookList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			bookList = session.createQuery(
					"from BooksOwned").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bookList;	
	}
	
	@SuppressWarnings("unchecked")
	public List<BooksOwned> getBooksOwned(User user){
		List<BooksOwned> bookList = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			bookList = session.createQuery(
					"from BooksOwned where user = ?").setEntity(0, user).list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bookList;	
	}
	
	public boolean isBookOwned(int bookId){
		BooksOwned bookOwned = null;
		Book book = new BookManager().getBookById(bookId);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			bookOwned = (BooksOwned)session.createQuery(
					"from BooksOwned where book = ?").setEntity(0, book)
					.uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		if(bookOwned == null){
			return false;
		}else{
			return true;
		}
		
	}
	
	public BooksOwned getBookOwned(User user, int bookId){
		BooksOwned bookOwned = null;
		Book book = new BookManager().getBookById(bookId);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			bookOwned = (BooksOwned)session.createQuery(
					"from BooksOwned where user = ? and book = ?").setEntity(0, user).setEntity(1, book)
					.uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bookOwned;	
	}
	
	
	public void addBookOwned(Book book, User user, String condition){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		BooksOwned bookOwned = new BooksOwned(book, user, condition);
		session.beginTransaction();
		try {
			session.saveOrUpdate(bookOwned);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	public void addBookOwned(BooksOwned bookOwned){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.saveOrUpdate(bookOwned);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	public void deleteBookOwned(int ownedId){
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			try {
				session.delete(session.load(BooksOwned.class, new Integer(ownedId)));//change to BooksOwned
				session.flush();
				session.getTransaction().commit();
			} catch (HibernateException e) {
				session.getTransaction().rollback();
				throw e;
			}
		
	}
	
}
