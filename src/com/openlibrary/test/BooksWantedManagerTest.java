package com.openlibrary.test;

import java.util.List;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.BooksWanted;
import com.openlibrary.managers.BooksWantedManager;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BooksWantedManagerTest extends TestCase {
	BooksWantedManager booksWantedManager = new BooksWantedManager();

	public static void main(String[] args){
		junit.textui.TestRunner.run(suite());

	}

	public static Test suite(){
		return new TestSuite(BooksWantedManagerTest.class);
	}
	public void testGetAllBooks(){
		List<BooksWanted> bookList = booksWantedManager.getAllBooksWanted();
		assertNotNull(bookList);
		assertTrue(bookList.size() > 0);
	}

	public void testGetBooksWantedByUserId(){
		List<BooksWanted> bookList = booksWantedManager.getAllBooksWanted();
		assertNotNull(bookList);
		BooksWanted book = bookList.get(0);
		assertNotNull(book);
		bookList = booksWantedManager.getBooksWanted(book.getUser());
		for(int i = 0; i < bookList.size(); i++){
			assertEquals(book.getUser().getUserId(), bookList.get(i).getUser().getUserId());
		}
	}

}

