<%@ page import="java.util.*, model.book" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>


<!DOCTYPE html> 
<html> <head> 
<title>View Books</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"> 
</head> 
<body> <div class="container mt-5">

 <h2 class="bg-primary text-white text-center p-3 rounded">Books List</h2>
 
 <form method="get" action="allBooks" class="row g-3 mb-4">
 
    <div class="col-md-4">
        <input type="text" class="form-control" name="query" placeholder="Search by title, author, catergory, language">
    </div>
      
    <div class="col-md-2">
        <button type="submit" class="btn btn-warning w-100">Search</button>
    </div>
</form>

<table class="table table-bordered table-hover">
    <thead class="table-dark">
        <tr>
            <th>#</th>
            <th>Title</th>
            <th>Category</th>
            <th>Author</th>
            <th class="text-center">Language</th>
            <th class="text-center">Price (LKR)</th>
             <% if (!"Cashier".equalsIgnoreCase(role)) { %>
             <th class="text-center">Actions</th>
            <% } %>
        </tr>	
    </thead>
    <tbody>
    <%
        List<book> books = (List<book>) request.getAttribute("books");
        if (books != null && !books.isEmpty()) {
           
        	int count = 1;
            
            java.text.NumberFormat nf = java.text.NumberFormat.getNumberInstance(new java.util.Locale("en", "US"));
            nf.setMinimumFractionDigits(2);
            
            for (book b : books) {
    %>
    <tr>
        <td><%= count++ %></td>
        <td><%= b.getTitle() %></td>
        <td><%= b.getCategory() %></td>
        <td><%= b.getAuthor() %></td>
        <td class="text-center"><%= b.getLanguage() %></td>
         <td class="text-end"><%= nf.format(b.getPrice()) %></td>
        
			     <% if (!"Cashier".equalsIgnoreCase(role)) { %>
			      <td class="text-center">
			        <a href="editBook?id=<%= b.getId() %>" class="btn btn-warning btn-sm">Edit</a>
			        <a href="deleteBook?id=<%= b.getId() %>" class="btn btn-danger btn-sm"
			           onclick="return confirm('Are you sure you want to delete this customer?');">Delete</a>
			   	 </td>
			     <% } %>
    </tr>
    <%  }
        } else { %>
    <tr>
        <td colspan="6" class="text-center">No books found.</td>
    </tr>
    <% } %>
    </tbody>
</table>

			<% if (books != null) { %>
			
			    <div class="mt-3 text-start">
			        <strong>Total Books: <%= books.size() %></strong>
			    </div>
			    
			<% } %>
			
			<div class="text-center mt-3">
				  <form action="allBooks" method="get" class="d-inline">
				    	<button type="submit" class="btn btn-warning btn-sm">View All Books</button>
				  </form>
				</div>
			
 </div>
 </body>
 </html>
 