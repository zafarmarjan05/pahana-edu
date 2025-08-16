package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import controler.DBConnect;
import model.Bill;

public class billService {
	
	public List<Bill> getAllBills() {
	    List<Bill> list = new ArrayList<>();
	    String sql = "SELECT * FROM billing ORDER BY bill_date DESC";

	    try (Connection conn = DBConnect.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Bill bill = new Bill();
	            bill.setId(rs.getInt("id"));
	            bill.setAccountNumber(rs.getString("customer_account_number"));
	            bill.setCustomerName(rs.getString("customer_name"));
	            bill.setBooksPurchased(rs.getString("books_purchased"));
	            bill.setTotalAmount(rs.getDouble("total_amount"));
	            bill.setBillingTime(rs.getTimestamp("bill_date"));
	            bill.setStaffUsername(rs.getString("Generated_by"));
	            list.add(bill);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;

}
	 public void deleteInvoice(int id) {
		   
		   Connection conn = null;
		   PreparedStatement ps = null;

		  
		   try {
		       conn = DBConnect.getConnection();
		       String sql = "DELETE FROM billing WHERE id = ?";
		       ps = conn.prepareStatement(sql);
		       ps.setInt(1, id);
		       ps.executeUpdate();
		   } catch (Exception e) {
		       e.printStackTrace();
		   } finally {
		       try {
		           if (ps != null) ps.close();
		           if (conn != null) conn.close();
		       } catch (Exception e) {
		           e.printStackTrace();
		       }
		   }
		   }
	   
}
