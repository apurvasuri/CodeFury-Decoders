<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="form.css" rel="stylesheet">
</head>
<body>
	<header>
		<img src="logo.jpg" alt="logo">
	</header>
	<c:if test="${not empty request.errMessage}">
		<div id="errMessage">{% request.getAttribute("errMessage") %}</div>
	</c:if>
	<div class="mid">
		<form name="login-form"
			action="${pageContext.request.contextPath}/login" method="POST">
			<input type="text" name="useremail" placeholder="Email Id" /> <input
				type="password" name="password" placeholder="password" /> <input
				type="submit" value="login" />
		</form>
	</div>
	<footer>
		<h2>&copy; Copyright Reserved.</h2>
	</footer>
</body>
</html>