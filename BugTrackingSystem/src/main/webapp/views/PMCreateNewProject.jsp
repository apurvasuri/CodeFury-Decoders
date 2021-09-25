<%@ page language="java" import="com.hsbc.btsapp.beans.Project, com.hsbc.btsapp.beans.User, com.hsbc.btsapp.beans.Bug, java.util.List , java.util.Date , java.util.ArrayList" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML>
<html>
    <head>
    	<meta charset="ISO-8859-1">
        <title>Create_New_Project</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <script src="../js/manager.js"></script>
     <script>
     
     	 function getDevelopers(){
     		 
     	 }
         function myfun(){
            
            var future = new Date();
            future.setDate(future.getDate() + 2);
            
            var DateOne=document.getElementById("entered_date").value;
            var date=new Date(DateOne);

            if(date.getTime()<future.getTime())
            {
                document.getElementById("Message").innerHTML="**Date must be 2 days ahead of current date.**";
                return false;
            }
        }
    </script> 
    <body>
    	<button class="button" onclick="getDeveloperFromTeam()">Open Form</button>
       	<div id="projectFormContainer" hidden="true">
       	<form name="projectForm" action="http://localhost:8080/BugTrackingSystem/projectController" method="post">
            Project Name: <input type="text" name="projectname" value="" required>
            <br>
            Start Date: <input type="date" name="projectstartdate" value="" required>
            <span id="Message" > </span>
            <br>
            Description: <input type="text" id="des" name="projectdescription" value="" required><br>
            <br>
            <label for="teamID">Team</label><input type="text" readonly="readonly" name="teamID" value="<%=request.getParameter("teamID") %>"/>
            <table id="membersTable">
            	<tr><th>Username</th><th>Role</th><th>Select</th></tr>
            </table>
		<input type="submit" value="Submit">
        </form>
       	</div>
    </body>
</html>