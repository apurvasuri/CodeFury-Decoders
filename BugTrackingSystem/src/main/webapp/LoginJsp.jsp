<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

<c:if test="${not empty request.errMessage}">
<div id="errMessage">{% request.getAttribute("errMessage") %}</div>
</c:if>
<form name="login-form" action="${pageContext.request.contextPath}/login" method="POST">
<label for="useremail">Email:  </label>
<input type="text" name="useremail"/>
<label for ="password">Password: </label>
<input type="password" name="password"/>
<input type="submit" value="login"/>
</form>

</body>
</html>