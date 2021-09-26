<%@ page language="java" 
import="com.hsbc.btsapp.beans.Project, com.hsbc.btsapp.beans.User, com.hsbc.btsapp.beans.Bug,com.hsbc.btsapp.beans.Team, java.util.List , 
java.util.Date"  
contentType="text/html; charset=ISO-8859-1" pageEncoding = "ISO-8859-1"%>

<!DOCTYPE html>
<html>

<head>
		<meta charset="ISO-8859-1">
		<title>Tester Page</title>
        <link rel="stylesheet" href="styles.css">
        <script src="https://kit.fontawesome.com/d04b222810.js" ></script>
        <script src="../js/tester.js"></script>
       <link href="generic.css" rel="stylesheet">
</head>
<body>
<h1>Welcome Tester</h1>
<jsp:include page="LogoutJsp.jsp"></jsp:include>

<%
		Team team = (Team) request.getAttribute("team");
		List<Bug> blist=(List<Bug>) request.getAttribute("buglist");
		List<Project> projectList=(List<Project>) request.getAttribute("userProject");
		User T=(User) session.getAttribute("User");
%>
	<header>
        <img src = "logo.jpg" alt = "logo"> 
    	<div class = "nav">
        	<a href ="#">About</a>
        <div id="Testerinfo">
        	<br>
        	<p><%=T.getUserName() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=T.getEmailId() %>&nbsp;&nbsp;&nbsp;&nbsp;</p>
        	<p>Last accessed time: <%=new Date(session.getLastAccessedTime())%></p>
    	</div>
        </div>
	</header>
	<br><br><br><br><br>
	<%if(projectList==null){ %>
		<p>No project assigned! <form action="UserProjectMapping" method="get"><input type="submit" value="Show All Projects"/></form></p>
	<%} else {%>
	<table name="testerProjectTable">
		<tr><th>Project ID</th><th>Project Name</th><th>Project Descriptions</th><th>Start Date</th><th>Project Status</th><th>Team Id</th></tr>
	<%for(Project p:projectList) {%>
		<tr>
			<td><%=p.getProjectId() %></td>
			<td><%=p.getProjectName()%></td>
			<td><%=p.getProjectDescription()%></td>
			<td><%=p.getProjectStartDate()%></td>
			<td><%=p.getProjectStatus()%></td>
			<td><%=p.getTeamID()%></td>
		</tr>
	<%}%>
	</table>
	<%} %>
	
	<div class= "container">
        <a href="http://localhost:8080/BugTrackingSystem/views/NewBugReport.jsp" target="_blank"><i class="fas fa-plus-square"></i></a>
        </div>
	<footer>
    	<h2> &copy; Copyright Reserved. </h2>
	</footer>
        

</body>
</html>