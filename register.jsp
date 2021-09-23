<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link href = "login.css" rel = "stylesheet">
<style>
	input{
		width : 200px;
		height : 20px;
	}
	
	select{
		width : 205px;
		height : 25px;
	}

</style>
</head>
<body>
	<header>
		<img src = "logo.jpg" alt = "logo">
		<div class = "nav">
    		<a href ="#">Home</a>
        	<a href ="#">About</a>
        	<a href ="#">FAQ</a>
        	<a href ="#">Team</a>
    	</div>
	</header>
	<div class ="mid">
	<form action = "register" method ="POST">
		<label> Email Id </label>
		<input type = "text" id = "eid">
		<br>
		<label> Role </label>
		<select>
			<option value="select">Select Role</option>
			<option value="pm">Project Manager</option>
    		<option value="dev">Developer</option>
    		<option value="test">Tester</option>
		</select>
		<br>
		<label> Password </label>
		<input type = "password" id ="pass">
		<br>
		<label> Confirm Password </label>
		<input type ="password" id ="conf_pass">
		<br>
		<input type = submit id = "submit">
	</form>
	</div>
	<footer>
    	<h2> &copy; Copyright Reserved. </h2>
	</footer>
</body>
</html>