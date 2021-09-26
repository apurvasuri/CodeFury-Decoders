<%@ page language="java"
	import="com.hsbc.btsapp.beans.Project, com.hsbc.btsapp.beans.User, com.hsbc.btsapp.beans.Bug, java.util.List , java.util.Date"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tester bug list</title>
<link href="generic.css" rel="stylesheet">
</head>
<body>
	<header>
		<img src="logo.jpg" alt="logo">
		<div id="userinfo">
			<br>
			<%
				Project p = (Project) request.getAttribute("project");
				List<Bug> blist = (List<Bug>) request.getAttribute("buglist");
				User u = (User) session.getAttribute("User");
			%>
			<p><%=p.getProjectName()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=p.getProjectStartDate()%>&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
		</div>
	</header>

	<%
		if (blist.isEmpty()) {
	%>
	<p>No bugs found</p>
	<%
		} else {
	%>
	<table>
		<thead class="thead-dark">
			<tr>
				<th>Bug ID</th>
				<th>Bug Title</th>
				<th>Description</th>
				<th>Project ID</th>
				<th>Created By</th>
				<th>Open Date</th>
				<th>Assigned To</th>
				<th>Marked For Closing</th>
				<th>Closed By</th>
				<th>Closed On</th>
				<th>Status</th>
				<th>Severity Level</th>
				<th>Task</th>
			</tr>
		</thead>
		<%
			for (Bug bug : blist) {
		%>
		<tr>
			<td><%=bug.getBugId()%></td>
			<td><%=bug.getBugTitle()%></td>
			<td><%=bug.getBugDescription()%></td>
			<td><%=bug.getProjectId()%></td>
			<td><%=bug.getCreatedBy()%></td>
			<td><%=bug.getOpenDate()%></td>
			<td><%=bug.getAssignedBy()%></td>
			<td><%=bug.isMarkedForClosing()%></td>
			<td><%=bug.getClosedBy()%></td>
			<td><%=bug.getClosedDate()%></td>
			<td><%=bug.getStatus()%></td>
			<td><%=bug.getSeverity()%></td>
			<td><button type="button">
					<a href="PMAssign?bugId=<%=bug.getBugId()%>">Assign</a>
				</button>
				<button type="button">
					<a href="closebug?bugId=<%=bug.getBugId()%>">Close</a>
				</button></td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		}
	%>
</body>
</html>