<%@ page language="java" 
import="com.hsbc.btsapp.beans.Project, com.hsbc.btsapp.beans.User, com.hsbc.btsapp.beans.Bug, java.util.List , 
java.util.Date"  
contentType="text/html; charset=ISO-8859-1" pageEncoding = "ISO-8859-1"%>

<!DOCTYPE html>
<html>

<head>
		<meta charset="ISO-8859-1">
		<title>Tester Page</title>
        <link rel="stylesheet" href="styles.css">
        <script src="https://kit.fontawesome.com/d04b222810.js" ></script>
</head>
<body>
<h1>Welcome Tester</h1>

<%
    	
		List<Project> plist=(List<Project>) request.getAttribute("projectList");
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
	<%if(plist==null){ %>
		<p>No project assigned!</p>
	<%} else {%>
		
				<%for(Project p:plist){ %>
					<tr><td><a href="TesterProject?TesterId=<%=p.getProjectId()%>" ><%=p.getProjectName()%></a></td></tr>
				<%} %>
	</form>
	<%} %>
	
	<div class= "container">
        <a href="ReportNewBug.jsp" target="_blank"><i class="fas fa-plus-square"></i></a>
        </div>
	<footer>
    	<h2> &copy; Copyright Reserved. </h2>
	</footer>
        

</body>
</html>