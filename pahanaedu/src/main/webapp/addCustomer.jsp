<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Add Customer</title>
  <link rel="stylesheet" type="text/css" href="css/styleCus.css">
</head>
<body>


<div class="container">
  <div class="card">
    <div class="card-header">
      <h4 class="mb-0">Add New Customer</h4>
    </div>
    <div class="card-body">

      <% String message = (String) session.getAttribute("message");
         if (message != null) { %>
           <div class="alert-success"><%= message %></div>
      <%   session.removeAttribute("message");
         } 
         String error = (String) request.getAttribute("error");
         if (error != null) { %>
           <div class="alert-danger"><%= error %></div>
      <% } %>

      <form action="addCustomer" method="post">
        <div class="mb-3">
          <label for="name">Customer Name</label>
          <input type="text" name="name" required>
        </div>
        <div class="mb-3">
          <label for="address">Address</label>
          <input type="text" name="address" required>
        </div>
        <div class="mb-3">
          <label for="telephone">Telephone</label>
          <input type="text" name="telephone" required pattern="\d{10}" title="Please enter exactly 10 digits">
        </div>
        <div class="mb-3">
          <label for="email">Email</label>
          <input type="email" name="email" required>
        </div>
        <button type="submit">Add Customer</button>
      </form>
    </div>
  </div>
</div>
</body>
</html>
