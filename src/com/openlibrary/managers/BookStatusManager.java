package com.openlibrary.managers;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.openlibrary.util.HibernateUtil;

import com.openlibrary.domain.BookStatus;






public class BookStatusManager{
	
	@SuppressWarnings("unchecked")
	public List<BookStatus> getAllBookStatuss() {
		List<BookStatus> conditionList = null;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			conditionList = session.createQuery("from BookStatus").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}

		return conditionList;
	}
}