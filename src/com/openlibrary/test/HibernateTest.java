package com.openlibrary.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.openlibrary.domain.*;
import com.openlibrary.managers.BookManager;
import com.openlibrary.managers.UserManager;
import com.openlibrary.util.*;



public class HibernateTest {

	public static void main(String args[]) throws Exception {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession(); // changed to
		// openSession
		Transaction tx = session.beginTransaction();

		Book book;
		BookCondition condition;
		BookStatus status;
		BooksOwned owned;
		BooksRead read;
		BooksWanted wanted;
		Review review;
		User user;
		UserGroup group;

		// Demo 1: Get single book
		try{
			int id = 1;
			book = (Book) session.get(Book.class, id) ;
			System.out.println("\nTitle for bookId 1 " + book.getTitle());
		}catch (Exception e){
		}

		// Demo 2: Get all records
		System.out.println("**Testing bookManager");
		BookManager bookM = new BookManager();
		User u = new UserManager().getUser(1);
		List<Book> bookList = bookM.getBooks(u);
		for (int i = 0; i < bookList.size(); i++) {
			book = (Book) bookList.get(i);
			System.out.println("Row " + (i + 1) + "> " + book.getTitle() + " (" + book.getAuthor()
					+ ")");
		}
		
		
		
		// Demo 3: Get single BookCondition
		try{
			String id = BookCondition.AS_NEW;
			condition = (BookCondition) session.get(BookCondition.class, id) ;
			System.out.println("\nName for 'AN' conditionCode: " + condition.getName());
		}catch (Exception e){
			e.printStackTrace();
		}

		// Demo 4: Get all BookConditions

		List conditionList = session.createQuery("from BookCondition").list();
		for (int i = 0; i < conditionList.size(); i++) {
			condition = (BookCondition) conditionList.get(i);
			System.out.println("Row " + (i + 1) + "> " + condition.getConditionCode() + " (" + condition.getName()
					+ ")");
		}
		

		// Demo 4.1: Get single BookStatus
		try{
			String id = "A";
			status = (BookStatus) session.get(BookStatus.class, id) ;
			System.out.println("\nName for 'A' statusCode: " + status.getName());
		}catch (Exception e){
		}

		// Demo 4.2: Get all BookStatuses

		List statusList = session.createQuery("from BookStatus").list();
		for (int i = 0; i < statusList.size(); i++) {
			status = (BookStatus) statusList.get(i);
			System.out.println("Row " + (i + 1) + "> " + status.getStatusCode() + " (" + status.getName()
					+ ")");
		}
		
		// Demo 5: Get single BooksOwned
		try{
			int id = 1;
			owned = (BooksOwned) session.get(BooksOwned.class, id) ;
			//System.out.println("\nBooksOwned Id 1's owners userId: " + owned.getUserId());
		}catch (Exception e){
		}

		// Demo 6: Get all BooksOwned

		List ownedList = session.createQuery("from BooksOwned").list();
		for (int i = 0; i < ownedList.size(); i++) {
			owned = (BooksOwned) ownedList.get(i);
			//System.out.println("Row " + (i + 1) + "> " + "bookId: " + owned.getBookId() +
			//		" Condition:" +owned.getBookCondition() + " UserId: " + owned.getUserId()
			//);
		}
		
		// Demo 5: Get single BooksRead
		try{
			int id = 1;
			read = (BooksRead) session.get(BooksRead.class, id) ;
			System.out.println("\nBooksRead Id 2's reader's userId: " + read.getUser());
		}catch (Exception e){
		}

		// Demo 6: Get all BooksRead

		List readList = session.createQuery("from BooksRead").list();
		for (int i = 0; i < readList.size(); i++) {
			read = (BooksRead) readList.get(i);
			System.out.println("Row " + (i + 1) + "> " + "bookId:" + read.getBookId() +
					" UserId:" + read.getUser()
					);
		}
		
		// Demo 7: Get single BooksWanted
		try{
			int id = 2;
			wanted = (BooksWanted) session.get(BooksWanted.class, id) ;
			System.out.println("\nBooksWanted Id 2's reader's userId: " + wanted.getUser());
		}catch (Exception e){
		}

		// Demo 8: Get all BooksWanted

		List wantedList = session.createQuery("from BooksWanted").list();
		for (int i = 0; i < wantedList.size(); i++) {
			wanted = (BooksWanted) wantedList.get(i);
			System.out.println("Row " + (i + 1) + "> " + "isbn:" + wanted.getBookId() + " UserId:" + wanted.getUser()
					);
		}
		
		// Demo 9: Get single Review
		try{
			int id = 1;
			review = (Review) session.get(Review.class, id) ;
			System.out.println("\nReview Id 1's userId: " + review.getUser());
		}catch (Exception e){
		}

		// Demo 10: Get all Review

		List reviewList = session.createQuery("from Review").list();
		for (int i = 0; i < reviewList.size(); i++) {
			review = (Review) reviewList.get(i);
			System.out.println("Row " + (i + 1) + "> " + "bookId:" + review.getBook() +
					" Rating:" + review.getRating() + " UserId:" + review.getUser() +
					" Title:" + review.getTitle() +
					" Review content:" + review.getContent() 
					);
		}
		
		// Demo 11: Get single User
		try{
			int id = 1;
			user = (User) session.get(User.class, id) ;
			System.out.println("\nUser Id 1's userId: " + user.getUserId());
		}catch (Exception e){
		}

		// Demo 12: Get all User

		List userList = session.createQuery("from User").list();
		for (int i = 0; i < userList.size(); i++) {
			user = (User) userList.get(i);
			System.out.println("Row " + (i + 1) + "> " + "userId:" + user.getUserId() +
					" Username:" + user.getUsername() + " email:" + user.getEmail()
			);
		}





		tx.commit();
		session.close();
		sessionFactory.close();
	}
}
