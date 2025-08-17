package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.book;
import services.bookService;


@WebServlet("/editBook")
public class editBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public editBook() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        int id = Integer.parseInt(request.getParameter("id"));
        bookService service = new bookService();
        book b = service.getBookById(id);

        request.setAttribute("book", b);
        request.getRequestDispatcher("editBook.jsp").forward(request, response);
    }

    // Process the form submission
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String author = request.getParameter("author");
        String language = request.getParameter("language");
        double price = Double.parseDouble(request.getParameter("price"));

        book b = new book();
        b.setId(id);
        b.setTitle(title);
        b.setCategory(category);
        b.setAuthor(author);
        b.setLanguage(language);
        b.setPrice(price);

        bookService service = new bookService();
        boolean updated = service.updateBook(b);

        if (updated) {
            response.sendRedirect("allBooks");
        } else {
            request.setAttribute("error", "Failed to update book.");
            request.setAttribute("book", b);
            request.getRequestDispatcher("editBook.jsp").forward(request, response);
        }
    }
    
	}


