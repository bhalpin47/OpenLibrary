package com.openlibrary.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String username;
	private String password;
	private String fName;
	private String lName;
	private Date dob;
	private List<BooksOwned> owned;
	private List<BooksRead> read;
	private List<BooksWanted> wanted;
	private String email;
	private int groupId;
	private char userCode;
	
	public User getUser(){
		return this;
	}

	public char getUserCode() {
		return userCode;
	}

	public void setUserCode(char userCode) {
		this.userCode = userCode;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public List<BooksOwned> getOwned() {
		return owned;
	}
	public void setOwned(List<BooksOwned> owned) {
		this.owned = owned;
	}
	public List<BooksRead> getRead() {
		return read;
	}
	public void setRead(List<BooksRead> read) {
		this.read = read;
	}
	public List<BooksWanted> getWanted() {
		return wanted;
	}
	public void setWanted(List<BooksWanted> wanted) {
		this.wanted = wanted;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
}
