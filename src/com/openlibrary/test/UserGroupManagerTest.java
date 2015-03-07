package com.openlibrary.test;

import java.util.List;

import com.openlibrary.managers.UserGroupManager;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



public class UserGroupManagerTest extends TestCase {
	UserGroupManager userGroupManager = new UserGroupManager();

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		return new TestSuite(UserGroupManagerTest.class);
	}

	public void testGetAllUserGroups() {
		List userGroupList = userGroupManager.getAllUserGroups();
		assertNotNull(userGroupList);
		assertTrue(userGroupList.size() > 0);

	}
}