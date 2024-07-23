<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List - Dealer</title>
    
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
        <h1>Product List - Dealer</h1>
        <hr>
		<form action="/getByProductNameDealer" method="get" class="col-4">
            <div class="input-group">
                <input name="name" type="text" class="form-control" placeholder="Search by Product Name">
                <button class="btn btn-primary" type="submit">Search</button>
            </div>
    	</form>
    	<hr>
		<form action="/getByProductPriceDealer" method="get" class="col-4">
            <div class="input-group">
                <input name="lower" type="number" class="form-control" placeholder="Lower Price">
                <input name="upper" type="number" class="form-control" placeholder="Upper Price">
                <button class="btn btn-primary" type="submit">Search</button>
            </div>
    	</form>
    	<hr>
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Product Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td>${product.productId}</td>
                        <td>${product.productName}</td>
                        <td>${product.description}</td>
                        
                        <td>
                               <fmt:formatNumber value="${product.productPrice}" type="currency" currencyCode="INR" />
                        </td>
                        
                        <td>
                            <a href="/dealer/products/edit/${product.productId}" class="btn btn-warning">Edit</a>
                            <a href="/dealer/products/delete/${product.productId}" class="btn btn-danger" onclick="return confirm('Do you want to delete product ${product.productName}')">
                            Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="/dealer/addProduct" class="btn btn-success">Add Product</a>
    </div>
    <!-- Include Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
