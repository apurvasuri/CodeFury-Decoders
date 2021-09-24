<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title> Report a New Bug</title>
 <link href = "style.css" rel = "stylesheet">
</head>
<body>

<header>
        <img src = "logo.jpg" alt = "logo"> 
    <div class = "nav">
        <a href ="bugController">About</a>
    </div>
</header>

<h1 align="center">Report a New Bug</h1><br><br>
<form method="post" action="/bugController">
<table align="center">
	<tr>
		<td>Project Name</td>
		<td><input type="text" name="pname"></td>
	</tr>
	<tr>
		<td>Title</td>
		<td><input type="text" name="bugTitle"></td>
	</tr>
	<tr>
		<td>Description</td>
		<td><textarea name="BugDescription" name="bugDescription" rows="5" cols="20"></textarea></td>
	</tr>
	<tr>
		<td>Serverity Level</td>
		<td>
			<select name="levels" id="levels" required>
			<option value=""></option>
  			<option value="Level-1">Level-1</option>
 			 <option value="Level-2">Level-2</option>
 			 <option value="Level-3">Level-3</option>
  			<option value="Level-4">Level-4</option>
			</select>
		</td>
	</tr>
	<tr>
		<td><input type="submit" value="register" id="register"></td>
	</tr>
</table>
</form>
<footer>
    <h2> &copy; Copyright Reserved. </h2>
</footer>

</body>
</html>