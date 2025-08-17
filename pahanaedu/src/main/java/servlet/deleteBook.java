package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.bookService;

@WebServlet("/deleteBook")
public class deleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public deleteBook() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	       String idParam = request.getParameter("id");
	        if (idParam != null) {
	            try {
	                int id = Integer.parseInt(idParam);
	                bookService service = new bookService();
	                boolean deleted = service.deleteBook(id);

	                if (deleted) {
	                    response.sendRedirect("allBooks?message=Book+deleted+successfully");
	                } else {
	                    response.sendRedirect("allBooks?error=Failed+to+delete+book");
	                }
	            } catch (NumberFormatException e) {
	                response.sendRedirect("allBooks?error=Invalid+book+ID");
	            }
	        } else {
	            response.sendRedirect("allBooks?error=Book+ID+missing");
	        }
	    }
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
