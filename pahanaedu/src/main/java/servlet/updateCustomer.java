package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.customer;
import services.customerService;


@WebServlet("/updateCustomer")
public class updateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public updateCustomer() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accountNumber = request.getParameter("account_number");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");

        customer c = new customer();
        c.setAccountNumber(accountNumber);
        c.setName(name);
        c.setAddress(address);
        c.setTelephone(telephone);
        c.setEmail(email);

        customerService service = new customerService();
        boolean updated = service.updateCustomer(c);

        if (updated) {
            response.sendRedirect("allCustomers");
        } else {
            request.setAttribute("error", "Failed to update customer.");
            request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
        }
    }
	


}
