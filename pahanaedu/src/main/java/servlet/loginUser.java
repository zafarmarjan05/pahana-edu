package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.user;

import services.userService;


@WebServlet("/loginUser")
public class loginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public loginUser() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username.equals("admin") && password.equals("admin1")) {
        	
        	HttpSession session = request.getSession();
            session.setAttribute("admin", "admin");
            response.sendRedirect("adminPanel.jsp"); // or forward if preferred
            return;
      	  
        }else {	
	      	  
	        userService service = new userService();
	        user loggedUser = service.loginUser(username, password);
	
		        if (loggedUser != null) {
		        	
		            // Login success: create session
		            HttpSession session = request.getSession();
		            session.setAttribute("user", loggedUser);
		            response.sendRedirect("mainInterface.jsp");
		            
		        } else {
		        	
		            // Login failed: show error message
		            request.setAttribute("error", "Invalid Username or Password.");
		            RequestDispatcher dispatcher = request.getRequestDispatcher("userLogin.jsp");
		            dispatcher.forward(request, response);
		            		           
		        }
        }        
		
	}

}
