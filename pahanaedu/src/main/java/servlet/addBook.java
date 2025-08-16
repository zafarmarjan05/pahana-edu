package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.book;
import services.bookService;
import services.userService;


@WebServlet("/addBook")
public class addBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public addBook() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
        String category = request.getParameter("category");
        String author = request.getParameter("author");
        String language = request.getParameter("language");
        String priceStr = request.getParameter("price");

        double price = 0;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid price.");
            request.getRequestDispatcher("addBook.jsp").forward(request, response);
            return;
        }
        
        // Check if book already exists
		
		bookService servicebook = new bookService();
		if (servicebook.bookExists(title)) {
		    request.setAttribute("error", "This book already added.");
		    request.getRequestDispatcher("addBook.jsp").forward(request, response);
		    return;
		}
      
        book b = new book();
        b.setTitle(title);
        b.setCategory(category);
        b.setAuthor(author);
        b.setLanguage(language);
        b.setPrice(price);

        
        bookService service = new bookService();
        boolean success = service.addBook(b);

        if (success) {
            request.setAttribute("message", "Book added successfully.");
        } else {
            request.setAttribute("error", "Failed to add book.");
        }

        request.getRequestDispatcher("addBook.jsp").forward(request, response);
    }
	

}
