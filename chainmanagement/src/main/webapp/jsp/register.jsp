<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>User Registration</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<style>
		.container {
			max-width: 500px;
			margin: 0 auto;
			padding-top: 50px;
		}
	</style>
</head>
<body>
	<div class="container">
		<h2 class="text-success">User Registration</h2>
		<f:form  modelAttribute="user" action="/register" method="post">
			<div class="form-group">
				<f:label path="firstName">First Name:</f:label>
				<f:input path="firstName" class="form-control" required="required"/>
				<f:errors path="firstName" cssStyle="color:red"></f:errors>
				
		    </div>
			<div class="form-group">
				<f:label path="lastName">Last Name:</f:label>
				<f:input path="lastName" class="form-control" required="required"/>
				<f:errors path="lastName" cssStyle="color:red"></f:errors>
				
		    </div>
			<div class="form-group">
				<f:label path="userName">User Name:</f:label>
				<f:input path="userName" class="form-control" required="required"/>
				<f:errors path="userName" cssStyle="color:red"></f:errors>
				<%-- Display username exists error message --%>
            	<c:if test="${not empty error}">
                	<div class="text-danger">${error}</div>
            	</c:if>
		    </div>
			<div class="form-group">
				<f:label path="password">Password:</f:label>
				<f:input path="password" class="form-control" required="required"/>
				<f:errors path="password" cssStyle="color:red"></f:errors>
		    </div>
			<div class="form-group">
				<f:label path="email">Email:</f:label>
				<f:input path="email" class="form-control" required="required"/>
				<f:errors path="email" cssStyle="color:red"></f:errors>
				
		    </div>
			<div class="form-group">
				<f:label path="phone">Phone:</f:label>
				<f:input path="phone" class="form-control" required="required"/>
				<f:errors path="phone" cssStyle="color:red"></f:errors>
				
		    </div>
			<div class="form-group">
				<f:label path="role">Role:</f:label>
				<select class="form-control" id="role" name="role" required>
					<option value="">Select Role</option>
					<%-- <option value="admin">Admin</option>--%>
					<option value="customer">Customer</option>
					<option value="dealer">Dealer</option>
				</select>
			</div>
			<button type="submit" class="btn btn-primary">Register</button>
		</f:form>
		<hr>
		<a class="btn btn-secondary" href="/login">Back to Login</a> 
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>
