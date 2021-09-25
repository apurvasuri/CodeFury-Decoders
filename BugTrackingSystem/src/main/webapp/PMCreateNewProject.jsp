<%@ page language="java" import="com.hsbc.btsapp.beans.Project, com.hsbc.btsapp.beans.User, com.hsbc.btsapp.beans.Bug, java.util.List , java.util.Date , java.util.ArrayList" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML>
<html>
    <head>
    	<meta charset="ISO-8859-1">
        <title>Create_New_Project</title>
        <link rel="stylesheet" href="style.css">
    </head>
     <script>
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
        <form name="myform" action="addProject"  onsubmit="return myfun()"  method="post">
            Project Name: <input type="text" name="p_name" value="" required>
            <br>
            Start Date: <input type="date" id="entered_date" value="" required>
            <span id="Message" > </span>
            <br>
            Description: <input type="text" id="des" value="" required><br>
            
            Add users: 
            
            <%List<User> ulist=(ArrayList<User>)request.getAttribute("ulist");%>
		<table border="2" class="table"><br>
			<thead class="thead-dark"> <tr><th>UserId</th><th>User Name</th><th>Email Id</th><th>Role</th><th>Select Employee</th></tr> </thead>
			<%for(User u:ulist){ %>   
				<tr><td><%=u.getUserId()%></td>
				<td><%=u.getUserName()%></td>
				<td><%=u.getEmailId()%></td>
				<td><%=u.getUserType()%></td>
				<td>
					<input type="checkbox" id="userId" name="userId" value="<%= u.getUserId()%>">	    	
				</td>  
				</tr>
				<%} %>
		</table>
		<input type="submit" value="Submit">
        </form>
    </body>
</html>