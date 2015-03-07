package com.openlibrary.test;

import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.openlibrary.controllers.ApproveController;
import com.openlibrary.controllers.YesNoPropertyEditor;
import com.openlibrary.domain.Book;
import com.openlibrary.domain.BookList;
import com.openlibrary.domain.BookStatus;
import com.openlibrary.managers.BookManager;
import com.openlibrary.managers.UserManager;
import com.openlibrary.util.ApplicationSecurityManager;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ApproveControllerTest extends TestCase {
	private MockHttpServletRequest mockHttpServletRequest;
	
	private ApproveController approveController;
	private BookManager bookManager;
	private UserManager userManager;
	private ApplicationSecurityManager applicationSecurityManager;
	
	private SimpleMailMessage approvalMessage;
	private SimpleMailMessage disapprovalMessage;
	private MailSender mailSender;
	private ResourceBundleMessageSource messages;
	
	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}
	
	public static Test suite() {
		return new TestSuite(ApproveControllerTest.class);
	}
	
	public void testFormBackingObject() {
		BookList bookList = (BookList)approveController.formBackingObject(mockHttpServletRequest);		
		assertNotNull(bookList);
	}

	

	public void testOnSubmit() {
		assertTrue(true);
	}
	protected void setUp() throws Exception {
		
		mockHttpServletRequest = new MockHttpServletRequest("GET", "/administration.htm");
		
		approveController = new ApproveController();
		bookManager = new BookManager();
		userManager = new UserManager();
		approvalMessage = new SimpleMailMessage();
		disapprovalMessage = new SimpleMailMessage();
		messages = new ResourceBundleMessageSource();
		messages.setBasename("messages");
		mockHttpServletRequest.setAttribute("messages", messages);
		JavaMailSenderImpl jms = new JavaMailSenderImpl();
		jms.setHost("gmail.com");
		mailSender = jms;
		approvalMessage.setTo("administrator@me.com" );
		approvalMessage.setSubject("Book Approved");
		approvalMessage.setText("Book is approved.  Regards.");
		disapprovalMessage.setSubject("Book Disapproved");
		disapprovalMessage.setText("Book is disapproved, please correct information.  Regards.");
		applicationSecurityManager = new ApplicationSecurityManager();
		approveController.setBookManager(bookManager);
		approveController.setUserManager(userManager);
		approveController.setApprovalMessage(approvalMessage);
		approveController.setDisapprovalMessage(disapprovalMessage);
		approveController.setMailSender(mailSender);
		approveController.setApplicationSecurityManager(applicationSecurityManager);
		
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
