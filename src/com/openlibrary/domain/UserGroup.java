package com.openlibrary.domain;

import java.io.Serializable;
import java.util.List;

public class UserGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	private int groupId;	//unique group identifier
	private String name;	//name of group
	
	public UserGroup(){}
	
	public UserGroup(int groupId, String name) {
		super();
		this.groupId = groupId;
		this.name = name;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
