package com.openlibrary.managers;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.User;
import com.openlibrary.util.HibernateUtil;


public class UserManager {
	
	@SuppressWarnings("unchecked")
	
	public List<User> getAllUsers(){
		List<User> userList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			userList = session.createQuery(
					"from User").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return userList;
		
	}
	
	public List<User> getAllAdmins(){
		List<User> userList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			userList = session.createQuery(
					"from User where userCode = ?").setString(0, "A").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return userList;
		
	}
	
	public User getUserByUsername(String username){
		User user = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			user = (User)session.createQuery(
					"from User where username = ?").
					setString(0, username).uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public User getUser(int userId){
		User user = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			user = (User)session.createQuery(
					"from User where userId = ?").
					setInteger(0, userId).uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return user;
		
	}
	
	public void saveUser(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.saveOrUpdate(user);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	public void deleteUser(int userId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.createQuery("delete from User where userId = ?").setInteger(0, userId).executeUpdate();
			session.flush();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	
}

