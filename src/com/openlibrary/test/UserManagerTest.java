package com.openlibrary.test;

import java.sql.Date;
import java.util.List;


import com.openlibrary.domain.User;
import com.openlibrary.managers.UserManager;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UserManagerTest extends TestCase {
	UserManager userManager = new UserManager();
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(suite());
		
	}
	
	public static Test suite(){
		return new TestSuite(UserManagerTest.class);
	}
	public void testGetAllUsers(){
		List<User> userList = userManager.getAllUsers();
		assertNotNull(userList);
		assertTrue(userList.size() > 0);
	}
	
	public void testGetUserByUserId(){
		List<User> userList = userManager.getAllUsers();
		assertNotNull(userList);
		User user1 = userList.get(0);
		assertNotNull(user1);
		User user2 = userManager.getUser(user1.getUserId());
		for(int i = 0; i < userList.size(); i++){
			assertEquals(user1.getUserId(), user2.getUserId());
		}
	}
	
	public void testUserCode(){
		List<User> userList = userManager.getAllUsers();
		assertNotNull(userList);
		char code;
		for(int i = 0; i < userList.size(); i++){
			code = userList.get(i).getUserCode();
			assertNotNull(code);
		}
	}
	
	public void testSaveAndDeleteUser(){
		User user = new User();
		user.setEmail("email@domain.com");
		user.setfName("First");
		user.setlName("Last");
		user.setUsername("username");
		user.setPassword("pass");
		user.setDob(new Date(0L));
		user.setUserCode('S');
		
		User user1 = userManager.getUserByUsername(user.getUsername());
		if (user1 == null) {
			
			userManager.saveUser(user);
		} else {
			System.out.println("User record found: " + user.getUsername());
			
		}
		
		
		
		User userInDB = userManager.getUserByUsername(user.getUsername());
		assertNotNull(userInDB);
		assertEquals(user.getEmail(), userInDB.getEmail());
		assertEquals(user.getfName(), userInDB.getfName());
		assertEquals(user.getlName(), userInDB.getlName());
		assertEquals(user.getUsername(), userInDB.getUsername());
		assertEquals(user.getPassword(), userInDB.getPassword());
		assertEquals(user.getUserCode(), userInDB.getUserCode());
		userManager.deleteUser(userInDB.getUserId());
		assertNull(userManager.getUser(userInDB.getUserId()));
	}
}
