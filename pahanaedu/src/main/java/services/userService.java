package services;


import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import controler.DBConnect;

import model.user;

public class userService {
	
public void regUser(user User){
	

	   Connection conn = null;
       PreparedStatement ps = null;

       try {
           // Hash the password
           String hashedPassword = hashPassword(User.getPassword());

           // Connect to the database
           conn = DBConnect.getConnection();

           // Use PreparedStatement (NO CASTING)
           String sql = "INSERT INTO users (fullname, username, email, password, role) VALUES (?, ?, ?, ?, ?)";
           ps = conn.prepareStatement(sql);
           ps.setString(1, User.getFullname());
           ps.setString(2, User.getUsername());
           ps.setString(3, User.getEmail());
           ps.setString(4, hashedPassword);
           ps.setString(5, User.getRole());

           int rowsInserted = ps.executeUpdate();
           System.out.println("Rows inserted: " + rowsInserted);

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


public boolean emailExists(String email) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conn = DBConnect.getConnection();
        String sql = "SELECT id FROM users WHERE email = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        rs = ps.executeQuery();
        return rs.next();  // true if a row is returned
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public boolean userNameExists(String userName) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conn = DBConnect.getConnection();
        String sql = "SELECT id FROM users WHERE username = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, userName);
        rs = ps.executeQuery();
        return rs.next();  // true if a row is returned
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
   // SHA-256 password hashing
   private String hashPassword(String password) throws Exception {
       MessageDigest md = MessageDigest.getInstance("SHA-256");
       byte[] hash = md.digest(password.getBytes("UTF-8"));

       StringBuilder hex = new StringBuilder();
       for (byte b : hash) {
           String hexChar = Integer.toHexString(0xff & b);
           if (hexChar.length() == 1) hex.append('0');
           hex.append(hexChar);
       }
       return hex.toString();
   }
   
   public user loginUser(String username, String password) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	     //   System.out.println("Login attempt with email: " + username);

	        // Hash the entered password
	        String hashedPassword = hashPassword(password);
	       // System.out.println("Hashed entered password: " + hashedPassword);

	        conn = DBConnect.getConnection();
	        String sql = "SELECT * FROM users WHERE username = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, username);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            String storedHash = rs.getString("password");
	         //   System.out.println("Stored password in DB: " + storedHash);

	            if (hashedPassword.equals(storedHash)) {
	              //  System.out.println("Password match success!");

	                user u = new user();
	                u.setId(rs.getInt("id"));
	                u.setFullname(rs.getString("fullname"));
	                u.setUsername(rs.getString("username"));
	                u.setEmail(rs.getString("email"));
	                u.setRole(rs.getString("role"));
	                return u;
	            } else {
	                System.out.println("Password does not match.");
	            }
	            
	            
	        } else {
	            System.out.println("No user found with that email.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return null;
	}


 

   public List<user> getAllUsers() {
	   
	    List<user> list = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
//
	    try {
	        conn = DBConnect.getConnection();
	        String sql = "SELECT * FROM users";
	        ps = conn.prepareStatement(sql);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            user u = new user();
	            u.setId(rs.getInt("id"));
	            u.setFullname(rs.getString("fullname"));
	            u.setUsername(rs.getString("username"));
	            u.setEmail(rs.getString("email"));
	            u.setRole(rs.getString("role"));
	            list.add(u);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return list;
	}

   public void updateUser(user u) {
	    Connection conn = null;
	    PreparedStatement ps = null;

	    try {
	        conn = DBConnect.getConnection();

	        String sql;
	        if (u.getPassword() != null) {
	            sql = "UPDATE users SET fullname=?, username=?, email=?, role=?, password=? WHERE id=?";
	            ps = conn.prepareStatement(sql);
	            ps.setString(1, u.getFullname());
	            ps.setString(2, u.getUsername());
	            ps.setString(3, u.getEmail());
	            ps.setString(4, u.getRole());
	            ps.setString(5, hashPassword(u.getPassword())); // hash new password
	            ps.setInt(6, u.getId());
	        } else {
	            sql = "UPDATE users SET fullname=?, username=?, email=?, role=? WHERE id=?";
	            ps = conn.prepareStatement(sql);
	            ps.setString(1, u.getFullname());
	            ps.setString(2, u.getUsername());
	            ps.setString(3, u.getEmail());
	            ps.setString(4, u.getRole());
	            ps.setInt(5, u.getId());
	        }

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
   
   public user getUserById(int id) {
	    user u = null;
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = DBConnect.getConnection();
	        String sql = "SELECT * FROM users WHERE id = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, id);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            u = new user(); // or 'new User()' if your class is named 'User'
	            u.setId(rs.getInt("id"));
	            u.setFullname(rs.getString("fullname"));
	            u.setUsername(rs.getString("username"));
	            u.setEmail(rs.getString("email"));
	            u.setRole(rs.getString("role"));
	            // Do not set password unless needed
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return u;
	}

   public void deleteUser(int id) {
	   
	   Connection conn = null;
	   PreparedStatement ps = null;

	  
	   try {
	       conn = DBConnect.getConnection();
	       String sql = "DELETE FROM users WHERE id = ?";
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



