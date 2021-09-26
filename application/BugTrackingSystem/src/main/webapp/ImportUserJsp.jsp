<%@ page language="java" import="com.hsbc.btsapp.beans.User"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Decoders</title>
<link href="form.css" rel="stylesheet">
<style>
#userinfo {
	float: right;
}

button {
	border: none;
	color: white;
	padding: 10px 26px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 2px 1px;
	transition-duration: 0.4s;
	cursor: pointer;
	background-color: white;
	color: black;
	border: 2px solid #008CBA;
}

button:hover {
	background-color: #008CBA;
	color: white;
}
</style>
<script>
	function readData() {
		var xhr = new XMLHttpRequest();
		var fname = document.getElementById("selectedfile").files[0].name;
		xhr.open("GET", "getDataFromFile/" + fname, true);
		xhr.onreadystatechange = function() {
			if ((xhr.readyState == 4) && (xhr.status == 200)) {
				alert(xhr.responseText);
			}
		}
		xhr.send();

	}
</script>
</head>
<body>
	<header>
		<img src="logo.jpg" alt="logo">
	</header>
	<br>
	<br>
	<br>
	<br>
	<br>
	<%
		//out.println(request.getAttribute("errMessage"));
	%>
	<div class="mid">
		<form action="importUsers" method="post" enctype="multipart/form-data">
			<input type="file" name="file" accept=".json,.xml"
				placeholder="import json file"><br> <br> <input
				type="submit" value="Import" />
		</form>
	</div>
	<footer>
		<h2>&copy; Copyright Reserved.</h2>
	</footer>
</body>
</html>