<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<!DOCTYPE html>
<html>
<head>
    <title>Order Details - Dealer</title>
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
        <h1>Order Details - Dealer</h1>
        <h4>Order ID: ${order.orderId}</h4>
        <h4>Order Date: ${order.orderDate}</h4>
        <h4>Order Status: ${order.orderStatus}</h4>
        <hr>
        <h3>Order Items</h3>
        <table class="table">
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${orderItems}" var="orderItem">
                    <tr>
                        <td>${orderItem.product.productName}</td>
                        <td>${orderItem.quantity}</td>
                        <td>${orderItem.totalPrice}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a class="btn btn-primary" href="/dealer/orders">Back to Order List</a>
    </div>
    <!-- Include Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
