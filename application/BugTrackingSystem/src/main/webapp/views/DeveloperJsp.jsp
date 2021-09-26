<%@ page language="java" import="com.hsbc.btsapp.beans.Project, com.hsbc.btsapp.beans.User, com.hsbc.btsapp.beans.Bug,com.hsbc.btsapp.beans.Team, java.util.List" contentType="text/html; charset=ISO-8859-1"
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
	<link href="generic.css" rel="stylesheet">
</head>
<body>
<jsp:include page="LogoutJsp.jsp"></jsp:include>
<% 
	Team team = (Team) request.getAttribute("team");
	List<Project> projectList=(List<Project>) request.getAttribute("userProject"); 
	List<Bug> blist=(List<Bug>) request.getAttribute("buglist");
	User u=(User) session.getAttribute("User");
%>	<header>
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
	<%if(projectList==null){ %>
		<p><form action="UserProjectMapping" method="get"><input type="submit" value="Show All Projects"/></form></p>
	<%} else {%>
	<table name="devProjectTable">
		<tr><th>Project ID</th><th>Project Name</th><th>Project Descriptions</th><th>Start Date</th><th>Project Status</th></tr>
	<%for(Project p:projectList) {%>
		<tr>
			<td><%=p.getProjectId() %></td>
			<td><%=p.getProjectName()%></td>
			<td><%=p.getProjectDescription()%></td>
			<td><%=p.getProjectStartDate()%></td>
			<td><%=p.getProjectStatus()%></td>
		</tr>
	<%}%>
	<%}%>
	</table>
	<br><br>
	<% if(blist==null){ %>
		<p><form action="bugController" method="get"><input type="submit" value="Show All Bugs"/></form>
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
			<%if(b.isMarkedForClosing()==true){ %>
			<td>Bug is marked for closing</td>
			<%} else {%>
			<td><form action="bugController?bugID=<%=b.getBugId() %>" method="POST"><input type="submit" value="Close bug"/></form></td>
			<%} %>
		</tr>
		<%}%>
	</table>
	<%}%>
	<footer>
    	<h2> &copy; Copyright Reserved. </h2>
	</footer>
</body>
</html>