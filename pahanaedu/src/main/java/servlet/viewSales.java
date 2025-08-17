package servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.Bill;
import services.billService;


@WebServlet("/viewSales")
public class viewSales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public viewSales() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		billService service = new billService();
        List<Bill> salesList = service.getAllBills(); 

        request.setAttribute("salesList", salesList);
        request.getRequestDispatcher("allSales.jsp").forward(request, response);
    
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
