<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
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
                <li class="nav-item">
                    <a class="nav-link" href="/customer/dashboard">Dashboard</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/customer/products">Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/customer/orders">Orders</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/customer/changePassword">Change Password</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/customer/logout">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container mt-4">
        <h1>Product List</h1>
        <hr>
		<form action="/getByProductName" method="get" class="col-4">
            <div class="input-group">
                <input name="name" type="text" class="form-control" placeholder="Search by Product Name">
                <button class="btn btn-primary" type="submit">Search</button>
            </div>
    	</form>
    	<hr>
		<form action="/getByProductPrice" method="get" class="col-4">
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
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Description</th>
                    <th>Product Price</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>${product.productId}</td>
                        <td>${product.productName}</td>
                        <td>${product.description}</td>
                        <td>${product.productPrice}</td>
                        <td>
                            <!-- Add button or link to add the product to the order -->
                            <td>
   								 <form action="/customer/addToCart/${product.productId}" method="post">
        							<input type="number" name="quantity" min="1" value="1">
       								<input type="hidden" name="_csrf" value="${_csrf.token}">
									<input type="submit" class="btn btn-primary" value="Add to Cart">
    							</form>
							</td>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Include Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
