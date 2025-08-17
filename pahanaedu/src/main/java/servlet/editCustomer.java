package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.customer;
import services.customerService;


@WebServlet("/editCustomer")
public class editCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public editCustomer() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accountNumber = request.getParameter("id");
        customerService service = new customerService();
        customer c = service.getCustomerByAccountNumber(accountNumber);

        request.setAttribute("customer", c);
        request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
