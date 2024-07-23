<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>User List</title>
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
                    <a class="nav-link" href="/admin/dashboard">Dashboard</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/users">Users</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/changePassword">Change Password</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/logout">Logout</a>
                </li>
            </ul>
        </div>
    </nav>
	
	<div class="container mt-4">
	<h2 class="text-primary" >User List </h2>
	<hr>
	<form action="/searchByUserName" method="get" class="col-4">
            <div class="input-group">
                <input name="name" class="form-control" placeholder="Search by User Name">
                <button class="btn btn-primary" type="submit">Search</button>
            </div>
    </form>
    <hr>
	<form action="/searchByUserEmail" method="get" class="col-4">
            <div class="input-group">
                <input name="email" class="form-control" placeholder="Search by User Email">
                <button class="btn btn-primary" type="submit">Search</button>
            </div>
    </form>
    <hr>
	<form action="/searchByUserRoleName" method="get" class="col-4">
            <div class="input-group">
                <input name="roleName" class="form-control" placeholder="Search by User Role">
                <button class="btn btn-primary" type="submit">Search</button>
            </div>
    </form>
    <hr>
    
	<form action="/addUser">
		<button class="btn btn-primary">Add New </button>
	</form>
	<br>
	<table class="table table-striped table-bordered">
		<thead>
		<tr>
			<th>ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>UserName</th>
			<th>Password</th>
			<th>Email</th>
			<th>Phone</th>
			<th>Role</th>
			<th>Change</th>
			<th>Actions</th>
		</tr>
		</thead>
		<tbody> 
		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.userId}</td>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.userName}</td>
				<td>${user.password}</td>
				<td>${user.email}</td>
				<td>${user.phone}</td>
				<td>${user.role}</td>
				<td>
					<a class="btn btn-warning" href="/user/update/${user.userId}">Update</a>
				</td>
				<td>
					<form action="/user/delete" method="post" onclick="return confirm('Do you want to delete user ${user.userName}')">
						<input type="hidden" name="id" value="${user.userId}">
						<button class="btn btn-danger" type="submit">Delete</button>
					</form>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	
    <!-- Include Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
		