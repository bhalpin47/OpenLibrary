package com.openlibrary.domain;

import java.io.Serializable;

public class BookStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String PENDING = "P";
	public static final String DENIED = "D";
	public static final String APPROVED = "A";
	public static final String RETURNED = "R";
	
	private String statusCode;
	private String name;

	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
