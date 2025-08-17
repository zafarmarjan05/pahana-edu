package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.user;
import services.userService;


@WebServlet("/updateUsers")
public class updateUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public updateUsers() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String password = request.getParameter("password");

        user u = new user();
        u.setId(id);
        u.setFullname(fullname);
        u.setUsername(username);
        u.setEmail(email);
        u.setRole(role);
        u.setPassword(password); // null or filled

        userService service = new userService();
        service.updateUser(u);

        response.sendRedirect("allUsers?updated=true");
        
	}

}
