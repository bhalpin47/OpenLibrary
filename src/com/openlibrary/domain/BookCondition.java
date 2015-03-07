package com.openlibrary.domain;

import java.io.Serializable;

public class BookCondition implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String AS_NEW = "AN";
	public static final String BINDING_COPY = "BC";
	public static final String BOOK_CLUB = "BL";
	public static final String EX_LIBRARY = "EL";
	public static final String FAIR = "F";
	public static final String FINE = "FN";
	public static final String GOOD = "G";
	public static final String POOR = "P";
	public static final String VERY_GOOD = "VG";
	
	private String conditionCode;
	private String name;

	public BookCondition(){
	}
	
	public BookCondition(String conditionCode){
		this.conditionCode = conditionCode;
	}
	public String getConditionCode() {
		return conditionCode;
	}
	public void setConditionCode(String conditionCode) {
		this.conditionCode = conditionCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}