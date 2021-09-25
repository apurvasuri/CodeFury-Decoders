<%@ page language="java" import="com.hsbc.btsapp.beans.Project, com.hsbc.btsapp.beans.User, com.hsbc.btsapp.beans.Bug, java.util.List" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Decoders</title>
	<link href = "style.css" rel = "stylesheet">
	<script src="js/developerJs.js"></script>
	<style>
		th{
			text-align: left;
		}
		#userinfo{
			float:right;
		}
	</style>
</head>
<body>
	<% Project p=(Project) request.getAttribute("project"); 
	List<Bug> blist=(List<Bug>) request.getAttribute("bug");
	User u=(User) session.getAttribute("User");
	%>
	<header>
        <img src = "logo.jpg" alt = "logo"> 
    	<div class = "nav">
        	<a href ="#">About</a>
        <div id="userinfo">
        	<br>
        	<p><%=u.getUserName() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=u.getEmailId() %>&nbsp;&nbsp;&nbsp;&nbsp;</p>
    	</div>
        </div>
	</header>
	<br><br><br><br><br>
	<%if(p==null){ %>
		<p>No project assigned!</p>
	<%} else {%>
		<form action="">
		<table border="1">
		<tr>
			<th>Project Id : </th>
			<td><%=p.getProjectId() %></td>
		</tr>
		<tr>
			<th>Project Name : </th>
			<td><%=p.getProjectName()%></td>
		</tr>
		<tr>
			<th>Description : </th>
			<td><%=p.getProjectDescription()%></td>
		</tr>
		<tr>
			<th>Start Date : </th>
			<td><%=p.getProjectStartDate()%></td>
		</tr>
		<tr>
			<th>Project Status : </th>
			<td><%=p.getProjectStatus()%></td>
		</tr>
		<tr>
			<th>Team Id : </th>
			<td><%=p.getTeamID()%></td>
		</tr>
	</table>
	<br><br>
	<% if(blist==null){ %>
		<p>No bugs found</p>
	<%} else { %>
		<table border="1">
		<tr>
			<th>Bug Id </th>
			<th>Bug Title </th>
			<th>Bug Description </th>
			<th>Severity </th>
			<th>Action</th>
		</tr>
		<% for(Bug b:blist){ %>
		<tr>
			<td><%=b.getBugId() %></td>
			<td><%=b.getBugTitle() %></td>
			<td><%=b.getBugDescription() %></td>
			<td><%=b.getSeverity() %></td>
			<td><a href="markForClosing?bugId=<%=b.getBugId() %>">Mark for closing</a></td>
		</tr>
		<%} %>
	</table>
	<%} %>
	</form>
	<%} %>
	<footer>
    	<h2> &copy; Copyright Reserved. </h2>
	</footer>
</body>
</html>