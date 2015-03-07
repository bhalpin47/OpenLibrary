package com.openlibrary.test;

import java.util.List;

import com.openlibrary.managers.BookStatusManager;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



public class BookStatusManagerTest extends TestCase {
	BookStatusManager bookStatusManager = new BookStatusManager();

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		return new TestSuite(BookStatusManagerTest.class);
	}

	public void testGetAllBookStatuss() {
		List bookStatusList = bookStatusManager.getAllBookStatuss();
		assertNotNull(bookStatusList);
		assertTrue(bookStatusList.size() > 0);

	}
}