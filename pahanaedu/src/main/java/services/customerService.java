package services;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import controler.DBConnect;
import model.customer;
import model.user;

public class customerService {
	
	public String generateNextAccountNumber() {
		
		
	       
	    String prefix = "5501";
	    String nextAccount = prefix + "0001"; // default if no customer exists

	    try (Connection conn = DBConnect.getConnection();
	         PreparedStatement ps = conn.prepareStatement(
	        		 
	             "SELECT account_number FROM customers WHERE account_number LIKE '5501%' ORDER BY account_number DESC LIMIT 1");
	    		
	         ResultSet rs = ps.executeQuery()) {

	        if (rs.next()) {
	            String lastAccount = rs.getString("account_number"); // e.g. 55010023
	            int lastNum = Integer.parseInt(lastAccount.substring(4));
	            int newNum = lastNum + 1;
	            nextAccount = prefix + String.format("%04d", newNum); // e.g. 55010024
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return nextAccount;
	}

	public boolean addCustomer(customer c) {
	    try (Connection conn = DBConnect.getConnection();
	         PreparedStatement ps = conn.prepareStatement(
	             "INSERT INTO customers (account_number, name, address, telephone, email) VALUES (?, ?, ?, ?, ?)")) {

	        ps.setString(1, c.getAccountNumber());
	        ps.setString(2, c.getName());
	        ps.setString(3, c.getAddress());
	        ps.setString(4, c.getTelephone());
	        ps.setString(5, c.getEmail());

	        int rows = ps.executeUpdate();
	        return rows > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean emailExists(String email) {
	    boolean exists = false;
	    try (Connection conn = DBConnect.getConnection();
	         PreparedStatement ps = conn.prepareStatement("SELECT 1 FROM customers WHERE email = ?")) {

	        ps.setString(1, email);
	        ResultSet rs = ps.executeQuery();
	        exists = rs.next();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return exists;
	}

	public List<customer> getAllCustomers() {
		
	
		   List<customer> list = new ArrayList<>();
		    Connection conn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;

		    try {
		        conn = DBConnect.getConnection();
		        String sql = "SELECT * FROM customers";
		        ps = conn.prepareStatement(sql);
		        rs = ps.executeQuery();

		        while (rs.next()) {
		        	
		             		        	
		        	
		        customer c = new customer();
		        c.setId(rs.getInt("id"));
		        c.setAccountNumber(rs.getString("account_number"));
		        c.setName(rs.getString("name"));
		        c.setAddress(rs.getString("address"));
		        c.setTelephone(rs.getString("telephone"));
		        c.setEmail(rs.getString("email"));
		        list.add(c);
		    }
		        
		       
		} catch (Exception e) {
			
		    e.printStackTrace();
		}
		return list;
	}
	
	public customer getCustomerByAccountNumber(String accNum) {
	    customer c = null;
	    try (Connection conn = DBConnect.getConnection()) {
	        String sql = "SELECT * FROM customers WHERE account_number = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, accNum);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            c = new customer();
	            c.setAccountNumber(rs.getString("account_number"));
	            c.setName(rs.getString("name"));
	            c.setAddress(rs.getString("address"));
	            c.setTelephone(rs.getString("telephone"));
	            c.setEmail(rs.getString("email"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return c;
	}

	public boolean updateCustomer(customer c) {
	    try (Connection conn = DBConnect.getConnection()) {
	        String sql = "UPDATE customers SET name = ?, address = ?, telephone = ?, email = ? WHERE account_number = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, c.getName());
	        ps.setString(2, c.getAddress());
	        ps.setString(3, c.getTelephone());
	        ps.setString(4, c.getEmail());
	        ps.setString(5, c.getAccountNumber());
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	public void deleteCustomer(int id) {
		   
		   Connection conn = null;
		   PreparedStatement ps = null;

		  
		   try {
		       conn = DBConnect.getConnection();
		       String sql = "DELETE FROM customers WHERE id = ?";
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

	public List<customer> searchCustomers(String accountNumber, String name, String telephone) {
	  
		List<customer> list = new ArrayList<>();
		
	    StringBuilder sql = new StringBuilder("SELECT * FROM customers WHERE 1=1");

	    if (accountNumber != null && !accountNumber.trim().isEmpty()) {
	        sql.append(" AND account_number LIKE ?");
	    }
	    if (name != null && !name.trim().isEmpty()) {
	        sql.append(" AND name LIKE ?");
	    }
	    if (telephone != null && !telephone.trim().isEmpty()) {
	        sql.append(" AND telephone LIKE ?");
	    }

	    try (Connection conn = DBConnect.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

	        int paramIndex = 1;
	        
	        if (accountNumber != null && !accountNumber.trim().isEmpty()) {
	            stmt.setString(paramIndex++, "%" + accountNumber + "%");
	        }
	        if (name != null && !name.trim().isEmpty()) {
	            stmt.setString(paramIndex++, "%" + name + "%");
	        }
	        if (telephone != null && !telephone.trim().isEmpty()) {
	            stmt.setString(paramIndex++, "%" + telephone + "%");
	        }

	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            customer c = new customer();
	            c.setAccountNumber(rs.getString("account_number"));
	            c.setName(rs.getString("name"));
	            c.setAddress(rs.getString("address"));
	            c.setTelephone(rs.getString("telephone"));
	            c.setEmail(rs.getString("email"));
	            list.add(c);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}


}
