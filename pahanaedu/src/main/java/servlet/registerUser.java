package servlet;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.user;
import services.userService;



@WebServlet("/registerUser")
public class registerUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public registerUser() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String fullname = request.getParameter("fullname");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirm");
		String role = request.getParameter("role");

		// Check either passwords match
		
		if (!password.equals(confirm)) {
		    request.setAttribute("error", "Passwords do not match.");
		    request.getRequestDispatcher("registerUser.jsp").forward(request, response);
		    return;
		}

		// Check if email already exists
		
		userService service = new userService();
		if (service.emailExists(email)) {
		    request.setAttribute("error", "Email already registered.");
		    request.getRequestDispatcher("registerUser.jsp").forward(request, response);
		    return;
		}
		
		// Check if username already exists
		
		userService serviceUser = new userService();
		if (serviceUser.userNameExists(username)) {
		    request.setAttribute("error", "User Name already registered.");
		    request.getRequestDispatcher("registerUser.jsp").forward(request, response);
		    return;
		}
		// Fill the User object
       user user = new user();
                
	   user.setFullname(request.getParameter("fullname"));
	   user.setUsername(request.getParameter("username"));
	   user.setEmail(request.getParameter("email"));
	   user.setPassword(request.getParameter("password"));
	   user.setRole(request.getParameter("role"));
	   	   

	   	userService service1 = new userService();
		service1.regUser(user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("registerUser.jsp");  
		request.getSession().setAttribute("message", "User added successfully.");
		dispatcher.forward(request, response);
		
		     
    	
	}


	
}
