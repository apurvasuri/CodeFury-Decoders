<%@ page page language="java"
	import="com.hsbc.btsapp.beans.Project, com.hsbc.btsapp.beans.User, com.hsbc.btsapp.beans.Bug, java.util.List , java.util.Date"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Report a New Bug</title>
<link href="style.css" rel="stylesheet">
</head>
<body>

	<header>
		<img src="logo.jpg" alt="logo">
		<div class="nav">
			<a href="#">About</a>
		</div>
	</header>

	<%
		List<Bug> plist = (List<Bug>) request.getAttribute("projectlist");
		User u = (User) session.getAttribute("User");
	%>

	<h1 align="center">Report a New Bug</h1>
	<br>
	<br>
	<form method="post"
		action="https://localhost:8080/BugTrackingSystem/bugController/">
		<table align="center">
			<tr>
				<td>Project Id</td>
				<td>
					<%-- <select name="pname" id="projects">
							<c:forEach items="${plist}" var="p">
								<option value="${p.getProjectId()"}>${p.getProjectName()}</option>
							</c:forEach>
					</select> --%>
					<input type="text" name="projectId">
				</td>
			</tr>
			<tr>
				<td>Title</td>
				<td><input type="text" name="bugTitle"></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><textarea name="BugDescription" name="bugDescription"
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
	</form>
	<footer>
		<h2>&copy; Copyright Reserved.</h2>
	</footer>

</body>
</html>