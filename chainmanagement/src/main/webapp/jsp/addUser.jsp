<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<title>Add User</title>
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
	<div class="container" >
	<h2 class="text-info">Add User</h2>
	<f:form class="col-4" modelAttribute="user" action="/admin/addUser" method="post">
		<%-- <div class="form-group">
			<f:label path="userId">userId</f:label>
			<f:input path="userId" class="form-control"/>
			<f:errors path="userId" cssStyle="color:red"></f:errors>
		</div>--%>
		<div class="form-group">
			<f:label path="firstName">FirstName</f:label>
			<f:input path="firstName" class="form-control" required="required"/>
			<f:errors path="firstName" cssStyle="color:red"></f:errors>
		</div>
		<div class="form-group">
			<f:label path="lastName">LastName</f:label>
			<f:input path="lastName" class="form-control" required="required"/>
			<f:errors path="lastName" cssStyle="color:red"></f:errors>
		</div>
		<div class="form-group">
			<f:label path="userName">UserName</f:label>
			<f:input path="userName" class="form-control" required="required"/>
			<f:errors path="userName" cssStyle="color:red"></f:errors>
		</div>
		<div class="form-group">
			<f:label path="password">Password</f:label>
			<f:input path="password" class="form-control" required="required"/>
			<f:errors path="password" cssStyle="color:red"></f:errors>
		</div>
		<div class="form-group">
			<f:label path="email">Email</f:label>
			<f:input path="email" class="form-control" required="required"/>
			<f:errors path="email" cssStyle="color:red"></f:errors>
		</div>
		<div class="form-group">
			<f:label path="phone">Phone</f:label>
			<f:input path="phone" class="form-control" required="required"/>
			<f:errors path="phone" cssStyle="color:red"></f:errors>
		</div>
		<div class="form-group">
			<f:label path="role">Role:</f:label>
			<select class="form-control" id="role" name="role" required>
					<option value="">Select Role</option>
					<option value="customer">Customer</option>
					<option value="dealer">Dealer</option>
			</select>
		</div>

	<br>
	<button class="btn btn-info">Add User</button>
	 
	</f:form>
	<hr>
	<a class="btn btn-success" href="/admin/users">Back to User List</a>
	</div>
	
    <!-- Include Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
	