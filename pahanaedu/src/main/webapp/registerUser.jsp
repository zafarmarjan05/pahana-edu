<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String admin = (String) session.getAttribute("admin");
    if (admin == null || !"admin".equalsIgnoreCase(admin)) {
        response.sendRedirect("userLogin.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>User Registration - Pahana Edu</title>
  <link rel="stylesheet" href="style.css" />
 
</head>
<body>
  <div class="container">
    <h2>Staff User Registration</h2>
    	
    			<%
				    String message = (String) session.getAttribute("message");
				    if (message != null) {
				%>
				    <div class="success-message"><%= message %></div>
				<%
				        session.removeAttribute("message"); // Clear it
				    } 
				    
				    String error = (String) request.getAttribute("error");
				    if (error != null) {
				%>
				   <div class="error-message"><%= error %></div>
				<%
				    }
				%>

    <form action="registerUser" method="post">
      <div class="form-group">
        <label for="fullname">Full Name</label>
        <input type="text" id="fullname" name="fullname" required />
      </div>

      <div class="form-group">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" required />
      </div>

      <div class="form-group">
        <label for="email">Email Address</label>
        <input type="email" id="email" name="email" required />
      </div>

      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required minlength="6" />
      </div>

      <div class="form-group">
        <label for="confirm">Confirm Password</label>
        <input type="password" id="confirm" name="confirm" required minlength="6" />
      </div>

      <div class="form-group">
        <label for="role">User Role</label>
        <select id="role" name="role" required>
          <option value="" disabled selected>Select role</option>
          <option value="Cashier">Cashier</option>
          <option value="Manager">Manager</option>
        </select>
      </div>

      <button type="submit">Register</button>
    </form>

  
   	<div style="margin-top: 20px; text-align: center;">
 		 <a href="adminPanel.jsp">
    		<button type="button" class="blue-button">Back to Admin Panel</button>
  		</a>
	</div>

  </div>
  <div>
    <%@ include file="footer.jsp" %>
    </div>
  
</body>
</html>
