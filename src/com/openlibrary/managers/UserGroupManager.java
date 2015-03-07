package com.openlibrary.managers;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.openlibrary.util.HibernateUtil;

import com.openlibrary.domain.UserGroup;






public class UserGroupManager{
	
	@SuppressWarnings("unchecked")
	public List<UserGroup> getAllUserGroups() {
		List<UserGroup> grouproupList = null;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			grouproupList = session.createQuery("from UserGroup").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}

		return grouproupList;
	}
}
