package com.openlibrary.test;

import java.util.List;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.BooksRead;
import com.openlibrary.managers.BooksReadManager;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BooksReadManagerTest extends TestCase {
	BooksReadManager booksReadManager = new BooksReadManager();

	public static void main(String[] args){
		junit.textui.TestRunner.run(suite());

	}

	public static Test suite(){
		return new TestSuite(BooksReadManagerTest.class);
	}
	public void testGetAllBooks(){
		List<BooksRead> bookList = booksReadManager.getAllBooksRead();
		assertNotNull(bookList);
		assertTrue(bookList.size() > 0);
	}

	public void testGetBooksReadByUserId(){
		List<BooksRead> bookList = booksReadManager.getAllBooksRead();
		assertNotNull(bookList);
		BooksRead book = bookList.get(0);
		assertNotNull(book);
		bookList = booksReadManager.getBooksRead(book.getUser());
		for(int i = 0; i < bookList.size(); i++){
			assertEquals(book.getUser().getUserId(), bookList.get(i).getUser().getUserId());
		}
	}

}

