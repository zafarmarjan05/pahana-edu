package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.user;
import services.userService;


@WebServlet("/allUsers")
public class allUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public allUsers() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		userService service = new userService();
        List<user> userList = service.getAllUsers();

        request.setAttribute("users", userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("allUsers.jsp");
        dispatcher.forward(request, response);
    }
		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
