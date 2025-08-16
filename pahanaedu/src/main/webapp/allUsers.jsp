<%@ page import="java.util.List" %>
<%@ page import="model.user" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    String deleted = request.getParameter("deleted");
    if ("true".equals(deleted)) {
%>
    <script>
        alert("User deleted Sucessfully");
    </script>
<%
    } 
%>
<%
    String updated = request.getParameter("updated");
    if ("true".equals(updated)) {
%>
    <script>
        alert("User details updated Sucessfully");
    </script>
<%
    } 
%>

<!DOCTYPE html>
<html>
<head>
    <title>All Users</title>
    <link rel="stylesheet" href="style.css" />
</head>
<body>

<div class="container">
    <h2>Users List of Book Shop</h2>
    <table>
        <thead style="background-color">
            <tr>
                <th>#</th>
                <th>Full Name</th>
                <th>User Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<user> users = (List<user>) request.getAttribute("users");
            if (users != null && !users.isEmpty()) {
                int count = 1;
                for (user u : users) {
        %>
        <tr style="text-align: center;">
            <td><%= count %></td>
            <td style="text-align : left"><%= u.getFullname() %></td>
            <td><%= u.getUsername() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getRole() %></td>
            <td>
                <a href="editUser?id=<%= u.getId() %>" class="btn-edit">Edit</a>
				<a href="deleteUser?id=<%= u.getId() %>" class="btn-delete"
				   onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>

            </td>
        </tr>
        <%
                count++;
                }
            } else {
        %>
        <tr>
            <td colspan="6" style="text-align: center;">No users found.</td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <div style="text-align: center; margin-top: 20px;">
        <a href="adminPanel.jsp"><button class="blue-button">Back to Admin Panel</button></a>
    </div>
</div>

<div>
<%@ include file ="footer.jsp"%>
</div>
</body>
</html>
