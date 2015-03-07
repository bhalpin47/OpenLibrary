package com.openlibrary.test;

import java.util.List;

import com.openlibrary.domain.BookCondition;
import com.openlibrary.managers.BookConditionManager;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



public class BookConditionManagerTest extends TestCase {
	BookConditionManager bookConditionManager = new BookConditionManager();

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		return new TestSuite(BookConditionManagerTest.class);
	}

	public void testGetAllBookConditions() {
		List<BookCondition> bookConditionList = bookConditionManager.getAllBookConditions();
		assertNotNull(bookConditionList);
		assertTrue(bookConditionList.size() > 0);

	}
}