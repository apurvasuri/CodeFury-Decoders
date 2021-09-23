<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<%@ page import = com.hsbc.beans.User %> 
    
<%
	User u =request.getAttribute("User");
	String email = request.getParameter("eid");
	String pass = request.getParameter("pass");
	
	if(email == u.getemail() && pass == u.getpass()){
		if(u.getUserType() == "Project Manager"){
			response.sendRedirect("projectmanager.jsp");
		}
		else if(u.getUserType() == "Developer"){
			response.sendRedirect("developer.jsp");
		}
		else if(u.getUserType() == "Tester"){
			response.sendRedirect("tester.jsp");
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href = "login.css" rel = "stylesheet">
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
	<form action ="login" method = "POST">
		<label> Email ID </label>
		<input type="text"  id = "eid" required>
		<br>
		<label> Password </label>
		<input type = "password" id = "pass" required>
		<br>
		<input type = submit id = "submit">
	</form>
	</div>
	<footer>
    	<h2> &copy; Copyright Reserved. </h2>
	</footer>
</body>
</html>