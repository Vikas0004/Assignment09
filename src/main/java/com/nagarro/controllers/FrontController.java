package com.nagarro.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.dao_classes.UserDAO;
import com.nagarro.pojo.Authors;
import com.nagarro.pojo.Books;
import com.nagarro.pojo.UserDetails;

@Controller
@SuppressWarnings("all")
public class FrontController {
	
	private static final UserDAO userDAO = new UserDAO();
	
	
	private final RestTemplate restTemplate;

    public FrontController() {

    	this.restTemplate = new RestTemplate();
    
    }
	
	
	@RequestMapping("/login")
	public ModelAndView displayController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mv = null;
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		
		UserDetails userDetails = new UserDetails(name, pass);
			
		if(userDAO.validateUser(userDetails)) {
			
			HttpSession session = request.getSession();  
	        session.setAttribute("name",name);  

			mv = new ModelAndView();
			mv.setViewName("displayBooks.jsp");

			
			String url = "http://localhost:8081/books";
			ResponseEntity<List<Books>> response1 = this.restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Books>>() {});
			List<Books> listBooks = response1.getBody();
			
			mv.addObject("listBooks", listBooks);
			
		}
		else {
			
			mv = new ModelAndView();
			mv.setViewName("error");
		}
		
		
		return mv;
	}
	
	
	@RequestMapping("/addBook")
	public ModelAndView addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false); 
		ModelAndView mv ;
		String url = "http://localhost:8081/authors";
		ResponseEntity<List<Authors>> response1 = this.restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Authors>>() {});
		List<Authors> listAuthors = response1.getBody();
		
		
		if((String)session.getAttribute("name")!=null) {
			String name = (String)session.getAttribute("name"); 
			mv = new ModelAndView();
			mv.setViewName("addBook.jsp");
			mv.addObject("listAuthors", listAuthors);
			mv.addObject("name",name);
		}
		else {
			
			mv = new ModelAndView();
			mv.setViewName("login_first");
			
		}
		
		return mv;

	}
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		   
		   HttpSession session = request.getSession();  
		   session.invalidate();  
	      
		   request.getRequestDispatcher("index.jsp").include(request, response);  

		
	}
	
	
	@RequestMapping("/edit")
	public void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		String url = "http://localhost:8081/book/{id}";
		Map<String, Integer> urlParams = new HashMap<String, Integer>();
		urlParams.put("id", id);
		 
        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Books> response1 = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, Books.class, urlParams);
        
        Books book = response1.getBody();
        
        
        
        String url2 = "http://localhost:8081/authors";
		ResponseEntity<List<Authors>> response2 = this.restTemplate.exchange(url2, HttpMethod.GET, null, new ParameterizedTypeReference<List<Authors>>() {});
		List<Authors> listAuthors = response2.getBody();
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("editBook.jsp");
		request.setAttribute("book", book);
		request.setAttribute("listAuthors", listAuthors);
		dispatcher.forward(request, response);

	}
	
	@RequestMapping("/insert")
	public void insertBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int bookCode = Integer.parseInt(request.getParameter("bookCode"));
		String bookName = request.getParameter("bookName");
		String authorName = request.getParameter("author");
		
		String url = "http://localhost:8081/book";
	    
		HttpHeaders headers = new HttpHeaders();

	    headers.setContentType(MediaType.APPLICATION_JSON);

	    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

	    Books book = new Books();
	    
	    Authors author = new Authors(authorName);
	    
		book.setBookCode(bookCode);
		book.setBookName(bookName);
		book.setCreatedAt(new Date());
		book.setAuthor(author);
		
	    HttpEntity<Books> entity = new HttpEntity<>(book, headers);

	    Books b = this.restTemplate.postForObject(url, entity, Books.class);
	    
		System.out.println(b); 
		
		response.sendRedirect("displayBooks");
	}
	
	@RequestMapping("/delete")
	public void deleteBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		int bookCode = Integer.parseInt(request.getParameter("id"));
		
		String url = "http://localhost:8081/book/{bookCode}";
		
		this.restTemplate.delete(url, bookCode);
		
		response.sendRedirect("displayBooks");
	}
	
	@RequestMapping("/update")
	public void updateBook(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
				
		String url = "http://localhost:8081/book/{bookCode}";
		
		// create headers
	    HttpHeaders headers = new HttpHeaders();
	    
	    
	    // set `content-type` header
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    
	    // set `accept` header
	    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	    
	    int bookCode = Integer.parseInt(request.getParameter("bookCode"));
		String bookName = request.getParameter("bookName");
		String authorName = request.getParameter("author");
		String create_date = request.getParameter("date");
		
	
		Date date = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy").parse(create_date); 
	
	    
	    Authors author = new Authors(authorName);
	    Books book = new Books();

	    book.setBookCode(bookCode);
		book.setBookName(bookName);
		book.setCreatedAt(date);
		book.setAuthor(author);

	    
		// build the request
	    HttpEntity<Books> entity = new HttpEntity<>(book, headers);
	    
	    this.restTemplate.put(url, entity, bookCode);
	    
	    response.sendRedirect("displayBooks");

	}
	
	@RequestMapping("/displayBooks")
	public ModelAndView displayBooks(HttpServletRequest request, HttpServletResponse response) {
	
		HttpSession session = request.getSession(false);
		String name=(String)session.getAttribute("name");
		ModelAndView mv = new ModelAndView();
		if(name != null) {
			
			String url = "http://localhost:8081/books";
			ResponseEntity<List<Books>> response1 = this.restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Books>>() {});
			List<Books> listBooks = response1.getBody();
			mv.setViewName("displayBooks.jsp");
			mv.addObject("listBooks", listBooks);
			
		}
		return mv;
	}


}
