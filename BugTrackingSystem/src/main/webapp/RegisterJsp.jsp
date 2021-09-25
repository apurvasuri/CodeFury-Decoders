<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>

<form name="register-form" action="register" method="POST">
<label for="username">Username: </lable><input type="text" name="username"/>
<label for="useremail">Email: </lable><input type="text" name="useremail"/>
<label for="role">Role: </lable>
<select name="role">
	<option  value="Developer">Developer</option>
	<option  value="Project Manager">Project Manager</option>
	<option value="Tester">Tester</option>
</select>
<label for="password">Password: </lable><input type="password" name="password"/>
<label for="confirm_password">Confirm password: </lable><input type="password" name="confirm_password"/>
<input type="submit" value="Register">
</form>
</body>
</html>