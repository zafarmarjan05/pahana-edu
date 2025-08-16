<%@ page import="java.util.List" %>
<%@ page import="model.customer" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="header.jsp" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View All Customers</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
   
</head>
<body>

<div class="container mt-5">
  
	<div class="bg-primary text-white p-3 rounded-top mb-3">
        <h2 class="mb-0 text-center">Customers List</h2>
    </div>	
   
   
    <div>
   
			<form class="row g-2 mb-3 mt-2" method="get" action="searchCustomer">
			  <div class="col-md-3">
			    <input type="text" name="accountNumber" class="form-control" placeholder="Search by Account Number" value="<%= request.getParameter("accountNumber") != null ? request.getParameter("accountNumber") : "" %>">
			  </div>
			  <div class="col-md-3">
			    <input type="text" name="name" class="form-control" placeholder="Search by Name" value="<%= request.getParameter("name") != null ? request.getParameter("name") : "" %>">
			  </div>
			  <div class="col-md-3">
			    <input type="text" name="telephone" class="form-control" placeholder="Search by Telephone" value="<%= request.getParameter("telephone") != null ? request.getParameter("telephone") : "" %>">
			  </div>
			  <div class="col-md-3">
			    <button type="submit" class="btn btn-warning w-100">Search</button>
			  </div>
			</form>

	</div>
	
    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th>#</th>
            <th>Account Number</th>
            <th>Name</th>
            <th>Address</th>
            <th>Telephone</th>
            <th>Email</th>
            <% if (!"Cashier".equalsIgnoreCase(role)) { %>
             <th>Actions</th>
            <% } %>
            
        </tr>
        </thead>
		<tbody>
			<%
			    List<customer> customers1 = (List<customer>) request.getAttribute("customers");
			    int count = 1;
			    if (customers1 != null && !customers1.isEmpty()) {
			        java.util.Iterator<customer> iterator = customers1.iterator();
			        while (iterator.hasNext()) {
			        customer c = iterator.next();
			        
					        
			%>
			<tr>
			    <td><%= count++ %></td>
			    <td><%= c.getAccountNumber() %></td>
			    <td><%= c.getName() %></td>
			    <td><%= c.getAddress() %></td>
				    <%
					    String rawTel = c.getTelephone();
					    String formattedTel = rawTel;
					    if (rawTel != null && rawTel.length() == 10) {
					        formattedTel = "(" + rawTel.substring(0, 3) + ") " + rawTel.substring(3, 6) + "-" + rawTel.substring(6);
					    }
					%>
				<td><%= formattedTel %></td>
			    <td><%= c.getEmail() %></td>
			   
			     <% if (!"Cashier".equalsIgnoreCase(role)) { %>
			      <td>
			        <a href="editCustomer?id=<%= c.getAccountNumber() %>" class="btn btn-warning btn-sm">Edit</a>
			        <a href="deleteCustomer?id=<%= c.getId() %>" class="btn btn-danger btn-sm"
			           onclick="return confirm('Are you sure you want to delete this customer?');">Delete</a>
			   	 </td>
			     <% } %>
			     
			</tr>
			<%
			        }
			    } else {
			%>
			<tr>
			    <td colspan="7" class="text-center">
			        No customers found.
			    </td>
			</tr>
			<%
			    }
			%>
			</tbody>
			</table>
			
			<% if (customers1 != null) { %>
			
			    <div class="mt-3 text-start">
			        <strong>Total Customers: <%= customers1.size() %></strong>
			    </div>
			    
			<% } %>
			
				<div class="text-center mt-3">
				  <form action="allCustomers" method="get" class="d-inline">
				    	<button type="submit" class="btn btn-warning btn-sm">View All Customers</button>
				  </form>
				</div>
							
			</div>
		</body>
			
	</html>