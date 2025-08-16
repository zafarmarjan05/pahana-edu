package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.customer;
import services.customerService;


@WebServlet("/allCustomers")
public class allCustomers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public allCustomers() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		customerService service = new customerService();
	    List<customer> customers= service.getAllCustomers();
	   	     
	    request.setAttribute("customers", customers);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("allCustomers.jsp");
        dispatcher.forward(request, response);
        
                       
	}
		
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
