<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Member</title>
<script src="../js/manager.js"></script>
</head>
<body>
<header>
		<img src="logo.jpg" alt="logo">
</header>
	<% out.println(request.getParameter("teamID")); %>

    	<button class="button" onclick="getDeveloper()">Get Developers and testers</button>
    	
		<div id="dtable">
			
		</div>
	<footer>
		<h2>&copy; Copyright Reserved.</h2>
	</footer>
</body>
</html>