<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            text-align: center;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 4px;
            margin-top: 100px;
        }
        h1 {
            color: #333;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #666;
        }
        .form-group input[type="text"],
        .form-group input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }
        .form-group select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }
        .form-group button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border: none;
            border-radius: 4px;
            font-size: 14px;
            cursor: pointer;
        }
        .form-group button:hover {
            background-color: #45a049;
        }
        .form-group a {
            color: #666;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Login</h1>
         			<%-- Display error message if present --%>
                    <c:if test="${not empty error}">
                        <p class="error">${error}</p>
                    </c:if>
                    
                    <%-- Display success message if present --%>
                    <c:if test="${not empty successMessage}">
                        <p class="success">${successMessage}</p>
                    </c:if>
                    
                    <%-- Display registration success message if present --%>
                    <c:if test="${not empty registrationSuccess}">
                        <p class="success">${registrationSuccess}</p>
                    </c:if>
        <form action="/login" method="POST">
            <div class="form-group">
                <label for="userName">Username:</label>
                <input type="text" id="userName" name="userName" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <button type="submit">Login</button>
            </div>
        </form>
        <div class="register">
                    <p>Don't have an account? <a href="/register">Register</a></p>
        </div>
        <%-- <div class="forgot-password">
            <a href="/searchByUserName">Forgot Password?</a>
        </div>--%>
    </div>
</body>
</html>
