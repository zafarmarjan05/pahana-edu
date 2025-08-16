<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.book" %>
<%@ include file="header.jsp" %>
<%
    book b = (book) request.getAttribute("book");
%>
    	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Books Details</title>
</head>
<body>


<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0 text-center">Edit Book</h4>
        </div>
        <div class="card-body">
            <form action="editBook" method="post">
                <input type="hidden" name="id" value="<%= b.getId() %>">

                <div class="mb-3">
                    <label>Title</label>
                    <input type="text" name="title" class="form-control" value="<%= b.getTitle() %>" required>
                </div>

                <div class="mb-3">
                    <label>Category</label>
                    <input type="text" name="category" class="form-control" value="<%= b.getCategory() %>" required>
                </div>

                <div class="mb-3">
                    <label>Author</label>
                    <input type="text" name="author" class="form-control" value="<%= b.getAuthor() %>" required>
                </div>

                <div class="mb-3">
                    <label>Language</label>
                    <input type="text" name="language" class="form-control" value="<%= b.getLanguage() %>" required>
                </div>

                <div class="mb-3">
                    <label>Price (LKR)</label>
                    <input type="number" name="price" class="form-control" step="0.01" value="<%= b.getPrice() %>" required>
                </div>

                <button type="submit" class="btn btn-success w-100">Update Book</button>
            </form>
        </div>
    </div>
    
</div>
</body>
</html>