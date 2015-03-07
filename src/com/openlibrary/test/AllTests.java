package com.openlibrary.test;

import com.openlibrary.controllers.*;

import junit.framework.Test;
import junit.framework.TestSuite;



public class AllTests {
	public static Test suite() {
		TestSuite modelTestSuite = new TestSuite("Model Tests");
		modelTestSuite.addTestSuite(BookManagerTest.class);
		modelTestSuite.addTestSuite(BookConditionManagerTest.class);
		modelTestSuite.addTestSuite(BooksOwnedManagerTest.class);
		modelTestSuite.addTestSuite(BooksReadManagerTest.class);
		modelTestSuite.addTestSuite(BooksWantedManagerTest.class);
		modelTestSuite.addTestSuite(BookStatusManagerTest.class);
		modelTestSuite.addTestSuite(ReviewManagerTest.class);
		modelTestSuite.addTestSuite(UserManagerTest.class);
		modelTestSuite.addTestSuite(UserGroupManagerTest.class);

		 TestSuite controllerTestSuite = new TestSuite("Controller Tests");
		 controllerTestSuite.addTestSuite(BookListControllerTest.class);
		 controllerTestSuite.addTestSuite(AddBookControllerTest.class);
		 controllerTestSuite.addTestSuite(AddBooksOwnedControllerTest.class);
		 controllerTestSuite.addTestSuite(AddBooksReadControllerTest.class);
		 controllerTestSuite.addTestSuite(AddBooksWantedControllerTest.class);
		 controllerTestSuite.addTestSuite(ApproveControllerTest.class);

		// Full test suite
		TestSuite fullSuite = new TestSuite("All Tests");
		fullSuite.addTest(modelTestSuite);
		fullSuite.addTest(controllerTestSuite);
		// fullSuite.addTest(controllerTestSuite);
		return fullSuite;
	}
}
