<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>

<form name="register-form" action="/register" method="POST">
<label for="username">Username: </lable><input type="text" name="username"/>
<label for="useremail">Email: </lable><input type="text" name="useremail"/>
<label for="password">Password: </lable><input type="text" name="password"/>
<label for="confirm_password">Confirm password: </lable><input type="text" name=""confirm_password""/>
</form>
</body>
</html>