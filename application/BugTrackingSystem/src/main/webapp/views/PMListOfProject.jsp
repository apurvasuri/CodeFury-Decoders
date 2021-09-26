<%@ page language="java" import="com.hsbc.btsapp.beans.Project, com.hsbc.btsapp.beans.User, com.hsbc.btsapp.beans.Bug, java.util.List , java.util.Date"  contentType="text/html; charset=ISO-8859-1" pageEncoding = "ISO-8859-1"%>


<!DOCTYPE html>
<html>
    <head>
        <title>Project_Manager</title>
        <link rel="stylesheet" href="generic.css">
        <script src="https://kit.fontawesome.com/d04b222810.js" ></script>
    </head>
    <body>
    	<%
    	
		List<Project> plist=(List<Project>) request.getAttribute("projectList");
		User u=(User) session.getAttribute("User");
		%>
	<header>
        <img src = "logo.jpg" alt = "logo"> 
    	<div class = "nav">
        	<a href ="#">About</a>
        <div id="userinfo">
        	<br>
        	<p><%=u.getUserName() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=u.getEmailId() %>&nbsp;&nbsp;&nbsp;&nbsp;</p>
        	<p>Last accessed time: <%=new Date(session.getLastAccessedTime())%></p>
    	</div>
        </div>
	</header>
	<br><br><br><br><br>
	<%if(plist==null){ %>
		<p>No project assigned!</p>
	<%} else {%>
		<table>
				<%for(Project p:plist){ %>
					<tr>
						<td><a href="PMProject?projectId=<%=p.getProjectId()%>" ><%=p.getProjectName()%></a></td>
					</tr>
				<%} %>
		</table>
	<%} %>
        <h3>List of Projects</h3>
		<div class= "container">
        <a href="PMCreateNewProject.jsp" target="_blank"><i class="fas fa-plus-square"></i></a>
        </div>
    <footer>
    	<h2> &copy; Copyright Reserved. </h2>
	</footer>
    </body>
</html>