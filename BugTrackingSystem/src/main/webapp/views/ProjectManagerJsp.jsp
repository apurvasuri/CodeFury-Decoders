<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.hsbc.btsapp.beans.Project, com.hsbc.btsapp.beans.User, com.hsbc.btsapp.beans.Bug,com.hsbc.btsapp.beans.Team, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Manager</title>
<link href="generic.css" rel="stylesheet">
</head>
<body>
<jsp:include page="LogoutJsp.jsp"></jsp:include>

<% 
List<Team> teamList = (List<Team>) request.getAttribute("pmTeamList");
Project p=(Project) request.getAttribute("userProject"); 
List<Bug> blist=(List<Bug>) request.getAttribute("bug");
User u=(User) session.getAttribute("User");
%>

<% if(request.getAttribute("errMessage")!=null && !((String)request.getAttribute("errMessage")).isEmpty()) {
	out.println(request.getAttribute("errMessage"));
}%>
<%if (teamList==null) { %>
<form action="teamController" method="get"><input type="submit" value="Show Teams"/></form>
<% } else { %>

		<table border="1">
		<tr>
			<th>Team Id : </th>
			<th colspan="3">Team Name : </th>
		</tr>
		<%for(Team team:teamList) { %>
		<tr>
			<td><%= team.getTeamId()%></td>
			<td>Team - <%=team.getTeamId() %></td>
			<td><a href="http://localhost:8080/BugTrackingSystem/views/PMCreateNewProject.jsp?teamID=<%=team.getTeamId()%>">Add Project</a>
			<td><a href="http://localhost:8080/BugTrackingSystem/views/PMAddMember.jsp?teamID=<%=team.getTeamId()%>">Add Member</a>
		</tr>
		<%} %>
		</table>
		
<%} %>
<a href="http://localhost:8080/BugTrackingSystem/views/AssignBugJsp.jsp">Assign Bugs To Developers</a>
<a href="http://localhost:8080/BugTrackingSystem/views/ClosebugJsp.jsp">Close bug</a>
<form action="teamController" method="post">
		<input type="submit" value="Create Team"/>
</form>
</body>
</html>