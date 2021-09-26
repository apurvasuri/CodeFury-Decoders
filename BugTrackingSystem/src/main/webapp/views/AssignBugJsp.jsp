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
<form name="bugForm" action="bugController" method="put">
	Project ID :<input type="text" name="projectID"/><br>
	Developer ID : <input type="text" name="developerID"/><br>
	Bug ID : <input type="text" name="bugID"/><br>
	<input type="submit" value="Assign"/><br>
</form>

</body>
</html>