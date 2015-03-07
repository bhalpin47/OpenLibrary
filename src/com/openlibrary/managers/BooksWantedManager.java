package com.openlibrary.managers;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.BookCondition;
import com.openlibrary.domain.BooksWanted;
import com.openlibrary.domain.BooksWanted;
import com.openlibrary.domain.User;
import com.openlibrary.util.HibernateUtil;

public class BooksWantedManager {

	
	@SuppressWarnings("unchecked")
	public List<BooksWanted> getAllBooksWanted(){
		List<BooksWanted> bookList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			bookList = session.createQuery(
					"from BooksWanted").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bookList;	
	}
	
	@SuppressWarnings("unchecked")
	public List<BooksWanted> getBooksWanted(User user){
		List<BooksWanted> bookList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			bookList = session.createQuery(
					"from BooksWanted where user = ?").setEntity(0, user).list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bookList;	
	}
	
	public BooksWanted getBookWanted(User user, int bookId) {
		BooksWanted bookWanted = null;
		Book book = new BookManager().getBookById(bookId);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			bookWanted = (BooksWanted)session.createQuery(
					"from BooksWanted where user = ? and book = ?").setEntity(0, user).setEntity(1, book)
					.uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bookWanted;	
	}
	
	public void addBookWanted(Book book, User user){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		BooksWanted bookWanted = new BooksWanted(book, user);
		session.beginTransaction();
		try {
			session.saveOrUpdate(bookWanted);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	public void addBookWanted(BooksWanted bookWanted){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.saveOrUpdate(bookWanted);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	public void deleteBookWanted(int ownedId){
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			try {
				session.delete(session.load(BooksWanted.class, new Integer(ownedId)));
				session.flush();
				session.getTransaction().commit();
			} catch (HibernateException e) {
				session.getTransaction().rollback();
				throw e;
			}
		
	}
}

