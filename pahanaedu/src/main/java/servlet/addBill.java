package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.DBConnect;
import model.book;
import model.customer;
import services.bookService;
import services.customerService;

@WebServlet("/addBill")
public class addBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public addBill() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		customerService customerService = new customerService();
	    bookService bookService = new bookService();

	    List<customer> customers = customerService.getAllCustomers();
	    List<book> books = bookService.getAllBooks();

	    request.setAttribute("customers", customers);
	    request.setAttribute("books", books);

	    request.getRequestDispatcher("addBill.jsp").forward(request, response);
		}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String accountNumber = request.getParameter("customer");
        double totalAmount = Double.parseDouble(request.getParameter("grandTotal").replace(",", ""));
        Timestamp now = new Timestamp(System.currentTimeMillis());

        List<Map<String, Object>> bookDetails = new ArrayList<>();
        StringBuilder itemsSummary = new StringBuilder();

        for (int i = 1; i <= 4; i++) {
            String bookId = request.getParameter("book" + i);
            String qtyStr = request.getParameter("qty" + i);
            String unitPriceStr = request.getParameter("unitPrice" + i);
            String totalStr = request.getParameter("bookTotal" + i);
            

            if (bookId != null && !bookId.isEmpty() && qtyStr != null && !qtyStr.isEmpty()) {
                // Get book title from DB (optional, or include it in a hidden field in form)
                String title = getBookTitleById(bookId);

                int quantity = Integer.parseInt(qtyStr);
                double unitPrice = Double.parseDouble(unitPriceStr.replace(",", ""));
                double total = Double.parseDouble(totalStr.replace(",", ""));

                Map<String, Object> item = new HashMap<>();
                item.put("title", title);
                item.put("qty", quantity);
                item.put("unitPrice", unitPrice);
                item.put("total", total);
                bookDetails.add(item);

                itemsSummary.append(title).append(", ");
            }
        }

        // Save to billing table
        try (Connection conn = DBConnect.getConnection()) {
        	String user =request.getParameter("user");
            String sql = "INSERT INTO billing (customer_account_number, customer_name, books_purchased, total_amount, bill_date,Generated_by) VALUES (?, ?, ?, ?, ?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountNumber);
            ps.setString(2, getCustomerNameByAccount(accountNumber));
            ps.setString(3, itemsSummary.toString().replaceAll(", $", ""));
            ps.setDouble(4, totalAmount);
            ps.setTimestamp(5, now);
            ps.setString(6, user);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error adding bill", e);
        }

        // Set data to show on billPreview
        request.setAttribute("accountNumber", accountNumber);
        request.setAttribute("customerName", getCustomerNameByAccount(accountNumber));
        request.setAttribute("bookDetails", bookDetails);
        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("billDate", now);

        request.getRequestDispatcher("billPreview.jsp").forward(request, response);
    }

    private String getCustomerNameByAccount(String accNum) {
        customerService service = new customerService();
        for (customer c : service.getAllCustomers()) {
            if (c.getAccountNumber().equals(accNum)) return c.getName();
        }
        return "Unknown";
    }

    private String getBookTitleById(String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            bookService service = new bookService();
            for (book b : service.getAllBooks()) {
                if (b.getId() == id) 
                
               return b.getTitle();
            }
        } catch (Exception e) {
            return "Unknown Book";
        }
        return "Unknown Book";
    }
		 
	
}

