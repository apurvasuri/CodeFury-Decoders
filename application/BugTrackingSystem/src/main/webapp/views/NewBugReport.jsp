<%@ page language="java"
	import="com.hsbc.btsapp.beans.Project, com.hsbc.btsapp.beans.User, com.hsbc.btsapp.beans.Bug,com.hsbc.btsapp.beans.Project, java.util.List , java.util.Date"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Report a New Bug</title>
<link href="generic.css" rel="stylesheet">
        <script src="../js/tester.js"></script>

</head>
<body>

	<header>
		<img src="logo.jpg" alt="logo">
		<div class="nav">
			<a href="#">About</a>
		</div>
	</header>
	<jsp:include page="LogoutJsp.jsp"></jsp:include>
	<%
		List<Project> plist = (List<Project>) request.getAttribute("projectlist");
		User u = (User) session.getAttribute("User");
	%>

	<h1 align="center"><button class="button" onclick="getAllProjects()">Report a New Bug</button></h1>
	<br>
	<br>
	<form id="bugForm" method="post"
		action="http://localhost:8080/BugTrackingSystem/bugController" hidden=true>
		<table align="center">
			<tr>
				<td>Project Id</td>
				<td>
					<select name="projectID" id="pOptions">
					</select>
				</td>
			</tr>
			<tr>
				<td>Title</td>
				<td><input type="text" name="bugTitle"></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><textarea name="bugDescription"
						rows="5" cols="20"></textarea></td>
			</tr>
			<tr>
				<td>Severity Level</td>
				<td><select name="levels" id="levels" required>
						<option value=""></option>
						<option value="Level-1">Level-1</option>
						<option value="Level-2">Level-2</option>
						<option value="Level-3">Level-3</option>
						<option value="Level-4">Level-4</option>
				</select></td>
			</tr>
			<tr>
				<td><input type="submit" value="register" id="register"></td>
			</tr>
		</table>
		<label for="createdBy">Created By: </label><input name="createdBy" type="text" readonly="readonly" value="<%=u.getUserId() %>"/>
	</form>
	<footer>
		<h2>&copy; Copyright Reserved.</h2>
	</footer>

</body>
</html>