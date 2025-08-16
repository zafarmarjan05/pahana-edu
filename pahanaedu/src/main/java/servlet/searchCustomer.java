package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.customer;
import services.customerService;


@WebServlet("/searchCustomer")
public class searchCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public searchCustomer() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accountNumber = request.getParameter("accountNumber");
	    String name = request.getParameter("name");
	    String telephone = request.getParameter("telephone");

	    customerService service = new customerService();
	    List<customer> filteredCustomers = service.searchCustomers(accountNumber, name, telephone);

	    request.setAttribute("customers", filteredCustomers);
	    request.getRequestDispatcher("allCustomers.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
