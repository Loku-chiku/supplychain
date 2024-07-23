<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
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
        <h1>Welcome, ${user.lastName}!</h1>
        
        <%--<table class="table">
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td>${product.productName}</td>
                        <td>${product.productPrice}</td>
                        <td>
                            <input type="number" class="form-control" id="quantity_${product.id}" name="quantity_${product.id}" min="1" value="1">
                        </td>
                        <td>
                            <button class="btn btn-primary add-to-cart" data-product-id="${product.id}">Add to Cart</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>--%>
        <a class="btn btn-success" href="/customer/cart">View Cart</a>
    </div>

    <!-- Include Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/js/bootstrap.bundle.min.js"></script>
    <script>
        $(document).ready(function() {
            $('.add-to-cart').click(function() {
                var productId = $(this).data('product-id');
                var quantity = parseInt($('#quantity_' + productId).val());

                // Send an AJAX request to add the item to the cart
                $.ajax({
                    url: '/customer/cart/addItem',
                    type: 'POST',
                    data: {
                        productId: productId,
                        quantity: quantity
                    },
                    success: function(response) {
                        alert('Product added to cart!');
                    },
                    error: function(error) {
                        console.log(error);
                        alert('Error adding the product to cart. Please try again.');
                    }
                });
            });
        });
    </script>
</body>
</html>
