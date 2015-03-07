package com.openlibrary.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.openlibrary.domain.Book;
import com.openlibrary.domain.BooksOwned;
import com.openlibrary.domain.User;
import com.openlibrary.managers.BookManager;
import com.openlibrary.managers.BooksOwnedManager;
import com.openlibrary.util.ApplicationSecurityManager;




/**
 * Controller for the Book List screen.
 * 
 */
public class BooksOwnedController implements Controller {
	private BooksOwnedManager booksOwnedManager;
	private BookManager bookManager;



	private ApplicationSecurityManager applicationSecurityManager;

	public static final String MAP_KEY = "books";
	public static final String XML_KEY = "booksxml";
	public static final String USR_KEY = "user";

	private String successView;

	/**
	 * Returns a list of Book database objects in ModelAndView.
	 * 
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User myUser = (User) applicationSecurityManager.getUser(request);
		List<BooksOwned> booksOwned = booksOwnedManager.getBooksOwned(myUser);
		List<Book> books = new ArrayList<Book>();
		for(int i = 0; i < booksOwned.size(); i++){
			books.add(bookManager.getBookById(booksOwned.get(i).getBook().getBookId()));
		}
		ModelAndView model = new ModelAndView(getSuccessView(), MAP_KEY, books).addObject(USR_KEY, myUser);
		Source booksXml = null;
		booksXml = createXsltSource(model.getModel(), "books", request, response);
		return model.addObject(XML_KEY, booksXml);
	}
	
	protected Source createXsltSource(Map model, String rootName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element root = document.createElement(rootName);

		List<Book> timesheets = (List<Book>) model.get("books");
		for (Book it : timesheets) {
			Book timesheet = (Book) it;
			Element timesheetNode = document.createElement("book");

			Element idNode = document.createElement("bookid");
			Text textiNode = document.createTextNode(((Integer) timesheet.getBookId()).toString());
			idNode.appendChild(textiNode);

			Element pedNode = document.createElement("title");
			Text textpedNode = document.createTextNode(timesheet.getTitle());
			pedNode.appendChild(textpedNode);

			Element statusNode = document.createElement("statusCode");
			Text textstatusNode = document.createTextNode(timesheet.getStatusCode());
			statusNode.appendChild(textstatusNode);

			timesheetNode.appendChild(idNode);
			timesheetNode.appendChild(pedNode);
			timesheetNode.appendChild(statusNode);
			root.appendChild(timesheetNode);
		}
		System.out.println(root.toString());
		DOMSource src = new DOMSource(root);

		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			DOMSource source = new DOMSource(root);
			StreamResult result = new StreamResult(System.out);
			transformer.transform(source, result);
		} catch (Exception ioe) {
			// I/O error
			ioe.printStackTrace();
		}

		return src;
	}
	
	public BookManager getBookManager() {
		return bookManager;
	}
	
	public void setBookManager(BookManager bookManager) {
		this.bookManager = bookManager;
	}
	
	public BooksOwnedManager getBooksOwnedManager() {
		return booksOwnedManager;
	}

	public void setBooksOwnedManager(BooksOwnedManager booksOwnedManager) {
		this.booksOwnedManager = booksOwnedManager;
	}
	
	public String getSuccessView() {
		return successView;
	}

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public ApplicationSecurityManager getApplicationSecurityManager() {
		return applicationSecurityManager;
	}

	public void setApplicationSecurityManager(
			ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}
	
	
}