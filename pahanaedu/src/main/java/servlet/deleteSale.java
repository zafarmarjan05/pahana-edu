package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.billService;
import services.bookService;


@WebServlet("/deleteSale")
public class deleteSale extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public deleteSale() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		  String billId = request.getParameter("id");
	       
	                int id = Integer.parseInt(billId);
          billService service = new billService();
          service.deleteInvoice(id);
          response.sendRedirect("viewSales");

      	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doGet(request, response);

		}
	}
