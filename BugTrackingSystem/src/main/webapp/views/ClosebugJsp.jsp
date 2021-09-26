<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Assign Bugs</title>
<script src="../js/manager.js"></script>
</head>
<body>
<jsp:include page="LogoutJsp.jsp"></jsp:include>



<button class="button" onclick="getAllBugs()">Get All Bugs</button>
<br>

<div id="bugTable"></div>
<form name="bugForm" action="http://localhost:8080/BugTrackingSystem/bugController" method="GET">
	<input type="text" hidden="hidden" value="true" name="closebug"/>
	Bug ID : <input type="text" name="bugID"/><br>
	<input type="submit" value="Close"/><br>
</form>

</body>
</html>