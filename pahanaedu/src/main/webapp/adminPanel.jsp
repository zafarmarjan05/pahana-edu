<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%
    String admin = (String) session.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect("userLogin.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Admin Panel</title>
  
  <link rel="stylesheet" href="css/styleAdminPanel.css" />
  
</head>
<body>

<div class="navbar-container">
  <div class="navbar">
    <a class="navbar-brand" href="#">
      <img src="images/download.png" alt="Logo" style="height: 30px; margin-right: 10px;">
      "Pahana Edu" - Book Shop
    </a>
    <div class="navbar-right">
      <span class="username">Logged as Admin</span>
      <a href="logOut.jsp" class="logout-btn">Logout</a>
    </div>
  </div>
</div>


<!-- Admin Panel -->
<div class="panel-container">
  <div class="panel-title">Admin Control Panel</div>

  <form action="allUsers" method="get">
    <button type="submit" class="btn-blue">
      View/Edit Staff
    </button>
  </form>

 <form action="registerUser.jsp" method="get">
    <button type="submit" class="btn-yellow">
      Register Staff
    </button>
</form>

<form action="viewSales" method="get">
    <button type="submit" class="btn-green">
      Sales Reports
    </button>
</form>


</div>

</body>
</html>
