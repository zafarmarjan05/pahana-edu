<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="model.customer" %>
<%@ include file="header.jsp" %>

<%
    customer c = (customer) request.getAttribute("customer");
    if (c == null) {
%>
    <div class="alert alert-danger">Customer not found.</div>
<%
    return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit Customer Details</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    input:focus, textarea:focus, select:focus {
      background-color: #fff9c4;
      outline: none;
      background-color: #fff9c4 !important; /* Light yellow */
  
    box-shadow: none; /* Removes Bootstrap's default blue shadow */
    }
  </style>
</head>

<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6 col-lg-5">
    
      <div class="bg-primary text-white p-3 rounded-top">
        <h2 class="mb-0 text-center">Edit Customer</h2>
      </div>
      
      <form action="updateCustomer" method="post" class="card p-4 shadow-sm border-top-0 rounded-bottom">

        
        <div class="mb-3">
          <label class="form-label">Account Number</label>
          <input type="text" class="form-control" value="<%= c.getAccountNumber() %>" disabled>
          <input type="hidden" name="account_number" value="<%= c.getAccountNumber() %>">
        </div>

        <div class="mb-3">
          <label class="form-label">Name</label>
          <input type="text" name="name" value="<%= c.getName() %>" class="form-control" required>
        </div>

        <div class="mb-3">
          <label class="form-label">Address</label>
          <input type="text" name="address" value="<%= c.getAddress() %>" class="form-control">
        </div>

        <div class="mb-3">
          <label class="form-label">Telephone</label>
          <input type="text" name="telephone" value="<%= c.getTelephone() %>" class="form-control" pattern="\d{10}" title="Enter 10-digit number" required>
        </div>

        <div class="mb-3">
          <label class="form-label">Email</label>
          <input type="email" name="email" value="<%= c.getEmail() %>" class="form-control" required>
        </div>

        <div class="d-grid">
          <button type="submit" class="btn btn-primary">Update Customer</button>
        </div>

      </form>
    </div>
  </div>
</div>



</html>