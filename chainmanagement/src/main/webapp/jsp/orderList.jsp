<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order List</title>
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
                <li class="nav-item">
                    <a class="nav-link" href="/customer/products">Products</a>
                </li>
                <li class="nav-item active">
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
        <h1>Order List</h1>
        <hr>
		<form action="/searchByOrderStatus" method="get" class="col-4">
            <div class="input-group">
                <input name="orderStatus" type="text" class="form-control" placeholder="Search by Order Status">
                <button class="btn btn-primary" type="submit">Search</button>
            </div>
    	</form>
    	<hr>
    	<form action="/searchByOrderDate" method="get" class="col-4">
            <div class="input-group">
                <input name="orderDate" type="date" class="form-control" placeholder="Search by Order Date">
                <button class="btn btn-primary" type="submit">Search</button>
            </div>
    	</form>
    	<hr>
        <table class="table">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Order Date</th>
                    <th>Order Status</th>
                    <th>Order Items</th>
                    <th></th>
                    <th>Total Price</th>
                    
                    
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.orderDate}</td>
                        <td>${order.orderStatus}</td>
                        <td>
                            
                                <c:forEach var="orderItem" items="${order.orderItems}">
                                    ${orderItem.product.productName} - ${orderItem.quantity}
                                </c:forEach>
                           
                        </td>
                        <td>
    							<c:set var="totalPrice" value="0" />
    							<c:forEach var="orderItem" items="${order.orderItems}">
								<c:set var="itemTotalPrice" value="${orderItem.quantity * orderItem.product.productPrice}" />
    							<c:set var="totalPrice" value="${totalPrice + itemTotalPrice}" /> 
    							</c:forEach>
						</td>
						<td>
                               <fmt:formatNumber value="${totalPrice}" type="currency" currencyCode="INR" />
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

