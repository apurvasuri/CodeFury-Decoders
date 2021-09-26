<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Assign Bugs</title>
<script src="../js/manager.js"></script>
<link href="generic.css" rel="stylesheet">
</head>
<body>
<header>
		<img src="logo.jpg" alt="logo">
		<div class ="nav">
		<jsp:include page="LogoutJsp.jsp"></jsp:include>
		<button class="button" onclick="getAllBugs()">Get All Bugs</button>
		</div>
</header>

<div id="bugTable"></div>
<form name="bugForm" action="http://localhost:8080/BugTrackingSystem/bugController" method="GET">
	<input type="text" hidden="hidden" value="true" name="closebug"/>
	Bug ID : <input type="text" name="bugID"/><br>
	<input type="submit" value="Close"/><br>
</form>
<footer>
		<h2>&copy; Copyright Reserved.</h2>
</footer>
</body>
</html>