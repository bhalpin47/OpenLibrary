package com.openlibrary.controllers;

import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.BookList;
import com.openlibrary.domain.BookStatus;
import com.openlibrary.domain.User;
import com.openlibrary.managers.BookManager;
import com.openlibrary.managers.UserManager;
import com.openlibrary.util.ApplicationSecurityManager;



/**
 * Controller class for the "Approve Books" screen
 * 
 * @author Alvaro Escobar
 */
public class ApproveController extends SimpleFormController {

	private BookManager bookManager;

	private UserManager userManager;

	private ApplicationSecurityManager applicationSecurityManager;

	private SimpleMailMessage approvalMessage;

	private SimpleMailMessage disapprovalMessage;

	private MailSender mailSender;

	private static final Logger logger = Logger.getLogger(ApproveController.class.getName());

	/**
	 * Returns a list of approvable Books from Users that report to
	 * signed-in User.
	 * 
	 * @see Book
	 */
	public Object formBackingObject(HttpServletRequest request) {
		User User = (User) applicationSecurityManager.getUser(request);
		List<Book> books = bookManager.getPendingBooks();	
		return new BookList(books);
	}

	/**
	 * Registers the YesNoPropertyEditor.
	 */
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(String.class, new YesNoPropertyEditor());
	}

	/**
	 * Saves <code>Book</code> command object using
	 * <code>BookManager.saveBook(Book)</code>;
	 * 
	 * @see BookManager
	 */
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) {
		String emailFrom = ((User) applicationSecurityManager.getUser(request)).getEmail();

		BookList Books = (BookList) command;
		List<Book> books = Books.getBooks();

		for (Book b : books) {
			bookManager.saveBook(b);
			System.out.println(b.getBookId() + " " + b.getStatusCode());
			if (b.getStatusCode().equalsIgnoreCase(BookStatus.APPROVED)) {
				sendApprovalEmail(emailFrom, b);
			} else {
				sendDisapprovalEmail(emailFrom, b);
			}
		}
		request.getSession().setAttribute("message",
				getMessageSourceAccessor().getMessage("message.administration.savesuccess"));
		return new ModelAndView(getSuccessView());
	}

	/**
	 * Send approval email to User and accounting department.
	 */
	private void sendApprovalEmail(String emailFrom, Book b) {
		approvalMessage.setFrom(emailFrom);
		approvalMessage.setTo(new String[] { approvalMessage.getTo()[0], b.getUser().getEmail() });
		SimpleMailMessage threadSafeMailMessage = new SimpleMailMessage(approvalMessage);
		System.out.println("Approve" + b.getBookId());
		sendMessage(threadSafeMailMessage);
	}

	/**
	 * Send disapproval email to User.
	 */
	private void sendDisapprovalEmail(String emailFrom, Book b) {
		disapprovalMessage.setFrom(emailFrom);
		disapprovalMessage.setTo(b.getUser().getEmail());
		SimpleMailMessage threadSafeMailMessage = new SimpleMailMessage(disapprovalMessage);
		System.out.println("Deny" + b.getBookId());
		sendMessage(threadSafeMailMessage);
	}

	private void sendMessage(SimpleMailMessage msg) {
		try {
			mailSender.send(msg);
		} catch (MailException e) {
			logger.warning("Send email failure: from " + msg.getFrom() + ", to " + msg.getTo());
		}
	}

	public void setBookManager(BookManager bookManager) {
		this.bookManager = bookManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void setApplicationSecurityManager(ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}

	public void setApprovalMessage(SimpleMailMessage approvalMessage) {
		this.approvalMessage = approvalMessage;
	}

	public void setDisapprovalMessage(SimpleMailMessage disapprovalMessage) {
		this.disapprovalMessage = disapprovalMessage;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
}
