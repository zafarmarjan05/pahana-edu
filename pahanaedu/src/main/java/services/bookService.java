package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controler.DBConnect;
import model.book;

public class bookService {
	
	
	public List<book> getAllBooks() {
	    List<book> list = new ArrayList<>();
	    String sql = "SELECT * FROM books ORDER BY title ASC";
	    try (Connection conn = DBConnect.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        while (rs.next()) {
	            book b = extractBook(rs);
	            list.add(b);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	public List<book> searchBooks(String keyword) {
	    List<book> list = new ArrayList<>();
	    String sql = "SELECT * FROM books WHERE LOWER(title) LIKE ? OR LOWER(author) LIKE ? OR LOWER(category) LIKE ?";
	    try (Connection conn = DBConnect.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        String like = "%" + keyword.toLowerCase() + "%";
	        ps.setString(1, like);
	        ps.setString(2, like);
	        ps.setString(3, like);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            book b = extractBook(rs);
	            list.add(b);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	private book extractBook(ResultSet rs) throws SQLException {
	   book b = new book();
	    b.setId(rs.getInt("id"));
	    b.setTitle(rs.getString("title"));
	    b.setCategory(rs.getString("category"));
	    b.setAuthor(rs.getString("author"));
	    b.setLanguage(rs.getString("language"));
	    b.setPrice(rs.getDouble("price"));
	    return b;
	}

	public boolean addBook(book b) {
	    Connection conn = null;
	    PreparedStatement ps = null;

	    try {
	        conn = DBConnect.getConnection();
	        String sql = "INSERT INTO books (title, category, author, language, price) VALUES (?, ?, ?, ?, ?)";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, b.getTitle());
	        ps.setString(2, b.getCategory());
	        ps.setString(3, b.getAuthor());
	        ps.setString(4, b.getLanguage());
	        ps.setDouble(5, b.getPrice());

	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public boolean bookExists(String bookName) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = DBConnect.getConnection();
	        String sql = "SELECT id FROM books WHERE title = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, bookName);
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
	
	public List<book> searchBooks(String query,String language) {
		
		
	    List<book> books = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	    	
	        conn = DBConnect.getConnection();
	        
	        StringBuilder sql = new StringBuilder("SELECT * FROM books WHERE 1=1");
	        
	        	     
	        if (query != null && !query.trim().isEmpty()) {
	        	sql.append(" AND (LOWER(title) LIKE ? OR LOWER(author) LIKE ? OR LOWER(category) LIKE ? OR LOWER(language) LIKE ?)");
	        	}
	        	   
	       	     
	  	        
	        ps = conn.prepareStatement(sql.toString());
	      
	        int paramIndex = 1;
	        if (query != null && !query.trim().isEmpty()) {
	            String q = "%" + query.toLowerCase() + "%";
	            ps.setString(paramIndex++, q); // title
	            ps.setString(paramIndex++, q); // author
	            ps.setString(paramIndex++, q); // category
	            ps.setString(paramIndex++, q); // language
	        }

	       
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            book b = new book();
	            b.setId(rs.getInt("id"));
	            b.setTitle(rs.getString("title"));
	            b.setCategory(rs.getString("category"));
	            b.setAuthor(rs.getString("author"));
	            b.setLanguage(rs.getString("language"));
	            b.setPrice(rs.getDouble("price"));
	            books.add(b);
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
	    return books;
	}

	// Get a book by ID
	public book getBookById(int id) {
	    book b = null;
	    try (Connection conn = DBConnect.getConnection();
	         PreparedStatement ps = conn.prepareStatement("SELECT * FROM books WHERE id = ?")) {
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            b = new book();
	            b.setId(rs.getInt("id"));
	            b.setTitle(rs.getString("title"));
	            b.setCategory(rs.getString("category"));
	            b.setAuthor(rs.getString("author"));
	            b.setLanguage(rs.getString("language"));
	            b.setPrice(rs.getDouble("price"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return b;
	}

	// Update a book
	public boolean updateBook(book b) {
	    try (Connection conn = DBConnect.getConnection();
	         PreparedStatement ps = conn.prepareStatement(
	             "UPDATE books SET title=?, category=?, author=?, language=?, price=? WHERE id=?")) {
	        ps.setString(1, b.getTitle());
	        ps.setString(2, b.getCategory());
	        ps.setString(3, b.getAuthor());
	        ps.setString(4, b.getLanguage());
	        ps.setDouble(5, b.getPrice());
	        ps.setInt(6, b.getId());
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean deleteBook(int id) {
	    try (Connection conn = DBConnect.getConnection();
	         PreparedStatement ps = conn.prepareStatement("DELETE FROM books WHERE id = ?")) {
	        ps.setInt(1, id);
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
}
