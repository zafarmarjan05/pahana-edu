<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.user" %>

<%
    user u = (user) request.getAttribute("user");
    if (u == null) {
        response.sendRedirect("userList.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update User</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h2>Update User</h2>
        <form action="updateUsers	" method="post">
            <input type="hidden" name="id" value="<%= u.getId() %>">
            
            <div class="form-group">
                <label for="fullname">Full Name</label>
                <input type="text" id="fullname" name="fullname" value="<%= u.getFullname() %>" required>
            </div>

            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" value="<%= u.getUsername() %>" required>
            </div>

            <div class="form-group">
                <label for="email">Email Address</label>
                <input type="text" id="email" name="email" value="<%= u.getEmail() %>" required>
            </div>

                 <div class="form-group">
			        <label for="role">User Role</label>
				        <select id="role" name="role" required>
				          <option value="" disabled selected>Select role</option>
				          <option value="Cashier">Cashier</option>
				          <option value="Manager">Manager</option>
				        </select>
		     	 </div>
		     	 
		     	   <div class="form-group">
				        <label for="password">Password</label>
				        <input type="password" id="password" name="password" minlength="6" placeholder="leave blank if password not need to change"/>
				   </div>
		     	 

            <button type="submit" class="wide-button">Update</button>
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
