<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css">
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
                <li class="nav-item">
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
        <h1>Cart</h1>
        <hr>
		<form action="/getByOrderItemsTotalPrice" method="get" class="col-4">
            <div class="input-group">
				<input name="lower" type="number" class="form-control" placeholder="Lower Price">
                <input name="upper" type="number" class="form-control" placeholder="Upper Price">                
                <button class="btn btn-primary" type="submit">Search</button>
            </div>
    	</form>
    	<hr>
    	<form action="/getByOrderItemsQuantity" method="get" class="col-4">
            <div class="input-group">
				<input name="minQuantity" type="number" class="form-control" placeholder="Minimum Quantity">
                <input name="maxQuantity" type="number" class="form-control" placeholder="Maximum Quantity">                 <button class="btn btn-primary" type="submit">Search</button>
            </div>
    	</form>
    	<hr>
    	
        <table class="table">
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="orderItem" items="${orderItems}">
                    <tr>
                        <td>${orderItem.product.productId}</td>
                        <td>${orderItem.product.productName}</td>
                        <td>${orderItem.quantity}</td>
                        <td>${orderItem.totalPrice}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form action="/customer/placeOrder" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" class="btn btn-success" value="Place Order">
        </form>
    </div>

    <!-- Include Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
