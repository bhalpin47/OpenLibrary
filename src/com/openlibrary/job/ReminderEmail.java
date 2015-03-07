package com.openlibrary.job;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;


import com.openlibrary.domain.BooksWanted;
import com.openlibrary.domain.User;
import com.openlibrary.managers.BooksOwnedManager;
import com.openlibrary.managers.BooksWantedManager;
import com.openlibrary.managers.UserManager;



/**
 * Sending reminder emails to users.
 */
public class ReminderEmail {

	private UserManager userManager;
	private MailSender mailSender;
	private SimpleMailMessage userReminderMessage;
	private BooksOwnedManager booksOwnedManager;
	private BooksWantedManager booksWantedManager;
	private static final Logger logger = Logger.getLogger(ReminderEmail.class.getName());

	/**
	 * Sends emails to users who have a book on their wanted list that is available from another user
	 * Sends on mondays at noon
	 */
	public void sendMail() {
		System.out.println("Sending email!");
		List<User> users = userManager.getAllUsers();
		if (users == null || users.size() == 0)
			return;
		
		List<String> emailAddressesList = new ArrayList<String>();
		List<BooksWanted> booksWanted;
		User user;
		for (Object obj : users) {
			user = (User) obj;
			booksWanted = booksWantedManager.getBooksWanted(user);
			for(int i = 0; i < booksWanted.size(); i++){
				if (booksOwnedManager.isBookOwned(booksWanted.get(i).getBookId())) {
					emailAddressesList.add(user.getEmail());
					break;
				}
				
			}
		}

		// preparing and sending an email
		if (emailAddressesList.size() > 0) {
			String emailAddresses[] = new String[emailAddressesList.size()];
			for (int i = 0; i < emailAddressesList.size(); ++i) {
				emailAddresses[i] = emailAddressesList.get(i);
				System.out.println(emailAddresses[i]);
			}
			userReminderMessage.setTo(emailAddresses);
			SimpleMailMessage threadSafeMailMessage = new SimpleMailMessage(userReminderMessage);
			try {
				mailSender.send(threadSafeMailMessage);
			} catch (MailException e) {
				logger.warning("Send email failure: from " + threadSafeMailMessage.getFrom() + ", to "
						+ threadSafeMailMessage.getTo());
			}
		}
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setUserReminderMessage(SimpleMailMessage userReminderMessage) {
		this.userReminderMessage = userReminderMessage;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void setBooksOwnedManager(BooksOwnedManager booksOwnedManager) {
		this.booksOwnedManager = booksOwnedManager;
	}

	public void setBooksWantedManager(BooksWantedManager booksWantedManager) {
		this.booksWantedManager = booksWantedManager;
	}


}
