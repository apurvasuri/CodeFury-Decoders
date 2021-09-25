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
	<% out.println(request.getParameter("teamID")); %>

    	<button class="button" onclick="getDeveloper()">Get Developers</button>
		<div id="dtable">
			
		</div>
</body>
</html>