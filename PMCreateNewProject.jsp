<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
        <form name="myform" action="PMListOfProject.jsp"  onsubmit="return myfun()"  method="post">
            Project Name: <input type="text" name="p_name" value="" required>
            <br>
            Start Date: <input type="date" id="entered_date" value="" required>
            <span id="Message" > </span>
            <br>
            Description: <input type="text" id="des" value="" required><br>
            
            Unique Id: <input type="number" id="uid" value=0 required><br>
            <footer><input type="submit" value="Create"></footer>
           
        </form>
    </body>
</html>