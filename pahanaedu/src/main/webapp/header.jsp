<%@ page session="true" %>
<%@ page import="model.user" %>

<%
    model.user u = (model.user) session.getAttribute("user");

    if (u == null) {
        response.sendRedirect("userLogin.jsp");
        return;
    }
    String username = u.getUsername();
    String role = u.getRole();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pahana Edu Book Shop</title>
    <link rel="stylesheet" type="text/css" href="css/styleHeader.css">
</head>
<body>

<nav class="custom-navbar">
  <div class="nav-container">
    <a class="brand" href="#">
      <img src="images/download.png" alt="Logo" class="logo">
      <span>Pahana Edu Book Shop</span>
    </a>

    <ul class="nav-links">
      <li class="dropdown">
        <span class="dropbtn">Customer Maintain</span>
        <div class="dropdown-content">
          <a href="addCustomer.jsp">Add Customer</a>
          <a href="allCustomers">View All Customers</a>
        </div>
      </li>
      <li class="dropdown">
        <span class="dropbtn">Maintain Books</span>
        <div class="dropdown-content">
          <a href="addBook.jsp">Add Books</a>
          <a href="allBooks">View All Books</a>
        </div>
      </li>
      <li class="dropdown">
        <span class="dropbtn">Billing Menu</span>
        <div class="dropdown-content">
          <a href="addBill">Add New Bill</a>
          <a href="viewSales">View Sales</a>
        </div>
      </li>
    </ul>

    <ul class="nav-right">
      <li><span class="user-info"> Logged User: <%= username %></span></li>
      <li><a class="logout-link" href="logOut.jsp">	 Logout</a></li>
    </ul>
  </div>
</nav>
