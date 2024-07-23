<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css">
    <!-- Include custom CSS -->
    <link rel="stylesheet" href="css/custom.css">
	<title>Update Product</title>
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
	
	<div class="container" >
	<h2 class="text-success">Update Product</h2>
	<hr>
	<f:form class="col-4" modelAttribute="product" action="/product/addProduct" method="post">
		<div class="form-group">
			<f:label path="productId">productId</f:label>
			<f:input path="productId" class="form-control"/>
			<f:errors path="productId" cssStyle="color:red"></f:errors>
		</div>
		<div class="form-group">
			<f:label path="productName">productName</f:label>
			<f:input path="productName" class="form-control"/>
			<f:errors path="productName" cssStyle="color:red"></f:errors>
		</div>
		<div class="form-group">
			<f:label path="description">Description</f:label>
			<f:input path="description" class="form-control"/>
			<f:errors path="description" cssStyle="color:red"></f:errors>
		</div>
		<div class="form-group">
			<f:label path="productPrice">productPrice</f:label>
			<f:input path="productPrice" class="form-control"/>
			<f:errors path="productPrice" cssStyle="color:red"></f:errors>
		</div>

	<br>
	<button class="btn btn-success">Update Product</button>
	 
	</f:form>
	<a href="/products">Back to Product List</a>
	</div>
</body>
</html>