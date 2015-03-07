package com.openlibrary.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Manages setting, getting and removal of Employee object in a HTTP session.
 * 
 * @author anil
 * @see com.visualpatterns.timex.model.Employee
 */
public class ApplicationSecurityManager {
	public static final String USER = "user";

	public Object getUser(HttpServletRequest request) {
		return request.getSession(true).getAttribute(USER);
	}

	public void setUser(HttpServletRequest request, Object user) {
		request.getSession(true).setAttribute(USER, user);
	}

	public void removeUser(HttpServletRequest request) {
		request.getSession(true).removeAttribute(USER);
	}
}
