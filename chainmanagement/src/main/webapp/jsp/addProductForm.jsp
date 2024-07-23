<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Product - Dealer</title>
    
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Include custom CSS -->
    <link rel="stylesheet" href="css/custom.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Supply Chain System</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/dealer/dashboard">Dashboard</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/dealer/products">Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/dealer/orders">Orders</a>
                </li>
         
                <li class="nav-item">
                    <a class="nav-link" href="/dealer/logout">Logout</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container mt-4">
        <h1 class="text-info">Add Product - Dealer</h1>
        <hr>
        <f:form class="col-4" modelAttribute="product" action="/dealer/products/add" method="post" >
            <div class="form-group">
				<f:label path="productName">productName</f:label>
				<f:input path="productName" class="form-control" required="required"/>
				<f:errors path="productName" cssStyle="color:red"></f:errors>
		    </div>
			<div class="form-group">
				<f:label path="description">Description</f:label>
				<f:input path="description" class="form-control" required="required"/>
				<f:errors path="description" cssStyle="color:red"></f:errors>
			</div>
			<div class="form-group">
				<f:label path="productPrice">productPrice</f:label>
				<f:input path="productPrice" class="form-control" required="required"/>
				<f:errors path="productPrice" cssStyle="color:red"></f:errors>
			</div>
			<br>
	       <button class="btn btn-primary">Add Product</button>
	 
	</f:form>
	 <hr>
     <a class="btn btn-secondary" href="/dealer/products">Back to Product List</a>      
    	</div>
    <!-- Include Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>