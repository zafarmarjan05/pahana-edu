package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.book;

import java.util.List;
import services.bookService;

@WebServlet("/allBooks")
public class allBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public allBooks() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String query = request.getParameter("query");
			String language = request.getParameter("language");
	      
	       
	     

	        bookService service = new bookService();
	        List<book> books;

	        if ((query != null && !query.trim().isEmpty())) {
	            books = service.searchBooks(query,language);
	          
	        } else {
	            books = service.getAllBooks();
	        }
	       
	        request.setAttribute("books", books);
	        request.getRequestDispatcher("allBooks.jsp").forward(request, response);
	}

		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
