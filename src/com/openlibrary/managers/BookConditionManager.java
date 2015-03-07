package com.openlibrary.managers;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.openlibrary.util.HibernateUtil;

import com.openlibrary.domain.BookCondition;






public class BookConditionManager{
	
	@SuppressWarnings("unchecked")
	public List<BookCondition> getAllBookConditions() {
		List<BookCondition> conditionList = null;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			conditionList = session.createQuery("from BookCondition").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}

		return conditionList;
	}
}