<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Order List - Dealer</title>
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
        <h1>Order List - Dealer</h1>
        <hr>
		<form action="/searchByOrderStatusDealer" method="get" class="col-4">
            <div class="input-group">
                <input name="orderStatus" type="text" class="form-control" placeholder="Search by Order Status">
                <button class="btn btn-primary" type="submit">Search</button>
            </div>
    	</form>
    	<hr>
    	<form action="/searchByOrderDateDealer" method="get" class="col-4">
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
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.orderId}</td>
                 	
                   
                    <td>${order.orderDate}</td>
                    <td>${order.orderStatus}</td>
                    <td>
                        <a class="btn btn-info" href="/dealer/orders/${order.orderId}">View Details</a>
                        <f:form action="/dealer/orders/updateStatus/${order.orderId}" method="post" modelAttribute="order" style="display: inline-block;">
                            <select name="status" onchange="this.form.submit()">
                                <option value="PENDING" <c:if test="${order.orderStatus eq 'PENDING'}"> selected </c:if> >Pending</option>
    							<option value="SHIPPED" <c:if test="${order.orderStatus eq 'SHIPPED'}">selected</c:if>>Shipped</option>
   							 	<option value="DELIVERED" <c:if test="${order.orderStatus eq 'DELIVERED'}">selected</c:if>>Delivered</option>
							</select>
                        </f:form>
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