	<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Book</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6"> 
      <div class="card shadow">
        <div class="card-header bg-primary text-white">
          <h4 class="mb-0 text-center">Add New Book</h4>
        </div>
        <div class="card-body">
          <form action="addBook" method="post">
            <div class="mb-3">
              <label for="title" class="form-label">Book Title</label>
              <input type="text" class="form-control" name="title" required>
            </div>
            <div class="mb-3">
              <label for="category" class="form-label">Category</label>
              <input type="text" class="form-control" name="category" required>
            </div>
            <div class="mb-3">
              <label for="author" class="form-label">Author</label>
              <input type="text" class="form-control" name="author" required>
            </div>
            <div class="mb-3">
              <label for="language" class="form-label">Language</label>
              <select name="language" class="form-select" required>
                <option value="English">English</option>
                <option value="Sinhala">Sinhala</option>
                <option value="Tamil">Tamil</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="price" class="form-label">Price (LKR)</label>
              <input type="number" class="form-control" name="price" step="0.01" required>
            </div>
            <button type="submit" class="btn btn-success w-100">Add Book</button>
          </form>
        </div>
       		
       		<% if (request.getAttribute("error") != null) { %>
			  <div class="d-flex justify-content-center">
			    <div class="alert alert-danger w-50 text-center">
			      <%= request.getAttribute("error") %>
			    </div>
			  </div>
			<% } %>
      </div>
    </div>
  </div>
</div>

</body>
</html>
