package com.openlibrary.job;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.openlibrary.domain.User;
import com.openlibrary.managers.BookManager;
import com.openlibrary.managers.UserManager;



/**
 * Sending reminder emails to users.
 */
public class ManagerEmail {

	private UserManager userManager;
	private MailSender mailSender;
	private SimpleMailMessage managerReminderMessage;
	private BookManager bookManager;
	private static final Logger logger = Logger.getLogger(ReminderEmail.class.getName());

	/**
	 * Sends emails to users who have a book on their wanted list that is available from another user
	 * Sends on mondays at noon
	 */
	public void sendMail() {
		System.out.println("Sending email!");
		List<User> users = userManager.getAllAdmins();
		if (users == null || users.size() == 0)
			return;
		
		List<String> emailAddressesList = new ArrayList<String>();
		User user;
		for (Object obj : users) {
			user = (User) obj;
			emailAddressesList.add(user.getEmail());	
		}

		// preparing and sending an email
		if (emailAddressesList.size() > 0) {
			String emailAddresses[] = new String[emailAddressesList.size()];
			for (int i = 0; i < emailAddressesList.size(); ++i) {
				emailAddresses[i] = emailAddressesList.get(i);
				System.out.println(emailAddresses[i]);
			}
			managerReminderMessage.setTo(emailAddresses);
			SimpleMailMessage threadSafeMailMessage = new SimpleMailMessage(managerReminderMessage);
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

	public BookManager getBookManager() {
		return bookManager;
	}

	public void setBookManager(BookManager bookManager) {
		this.bookManager = bookManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public SimpleMailMessage getManagerReminderMessage() {
		return managerReminderMessage;
	}

	public void setManagerReminderMessage(SimpleMailMessage managerReminderMessage) {
		this.managerReminderMessage = managerReminderMessage;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}




}

