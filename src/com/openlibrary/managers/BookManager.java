package com.openlibrary.managers;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.BookStatus;
import com.openlibrary.domain.User;
import com.openlibrary.util.HibernateUtil;




public class BookManager {
	
	@SuppressWarnings("unchecked")
	
	public List<Book> getAllBooks(){
		List<Book> bookList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			bookList = session.createQuery(
					"from Book").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bookList;
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Book> getPendingBooks(){
		List<Book> bookList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			bookList = session.createQuery(
					"from Book where statusCode = ?").setString(0, BookStatus.PENDING).list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bookList;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Book> getBooks(User user){
		List<Book> bookList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			bookList = session.createQuery(
					"from Book where user = ?").
					setEntity(0, user).list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bookList;
		
	}
	
	@SuppressWarnings("unchecked")
	public Book getBookByIsbn(String isbn){
		Book book = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			book = (Book)session.createQuery(
					"from Book where isbn = ?").
					setString(0, isbn).uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return book;
		
	}
	
	@SuppressWarnings("unchecked")
	public Book getBookById(int id){
		Book book = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			book = (Book)session.createQuery(
					"from Book where bookId = ?").
					setInteger(0, id).uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return book;
		
	}
	
	public Book getBookById(int bookId, boolean doLock) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Book book = null;
		try {
			if (doLock)
				book = (Book) session.get(Book.class, new Integer(bookId), LockMode.UPGRADE);
			else
				book = (Book) session.get(Book.class, new Integer(bookId));
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return book;
	}
	
	public void saveBook(Book book) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.saveOrUpdate(book);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	public void deleteBookById(int bookId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.createQuery("delete from Book where bookId = ?").setInteger(0, bookId).executeUpdate();
			session.flush();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	public void deleteBookByIsbn(String isbn) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.createQuery("delete from Book where isbn = ?").setString(0, isbn).executeUpdate();
			session.flush();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}


	
}
