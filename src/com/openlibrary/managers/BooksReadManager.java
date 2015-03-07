package com.openlibrary.managers;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.BookCondition;
import com.openlibrary.domain.Review;

import com.openlibrary.domain.BooksRead;
import com.openlibrary.domain.User;
import com.openlibrary.util.HibernateUtil;

public class BooksReadManager {

	
	@SuppressWarnings("unchecked")
	public List<BooksRead> getAllBooksRead(){
		List<BooksRead> bookList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			bookList = session.createQuery(
					"from BooksRead").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bookList;	
	}
	
	@SuppressWarnings("unchecked")
	public List<BooksRead> getBooksRead(User user){
		List<BooksRead> bookList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			bookList = session.createQuery(
					"from BooksRead where user = ?").setEntity(0, user).list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bookList;	
	}

	public BooksRead getBookRead(User user, int bookId) {
		BooksRead bookRead = null;
		Book book = new BookManager().getBookById(bookId);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			bookRead = (BooksRead)session.createQuery(
					"from BooksRead where user = ? and book = ?").setEntity(0, user).setEntity(1, book)
					.uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bookRead;	
	}
	
	public void addBookRead(Book book, User user){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		BooksRead bookRead = new BooksRead(book, user);
		session.beginTransaction();
		try {
			session.saveOrUpdate(bookRead);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	public void addBookRead(BooksRead bookRead){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.saveOrUpdate(bookRead);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	public void deleteBookRead(int ownedId){
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			try {
				session.delete(session.load(BooksRead.class, new Integer(ownedId)));//change to BooksRead
				session.flush();
				session.getTransaction().commit();
			} catch (HibernateException e) {
				session.getTransaction().rollback();
				throw e;
			}
		
	}
}
