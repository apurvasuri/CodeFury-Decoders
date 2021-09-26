function getDeveloper() {
	console.log("inside function");
	const http = new XMLHttpRequest();
	if (!http) {
		console.log("Unable to create a XMLHttpRequest Object");
		return false;
	}
	const url = "http://localhost:8080/BugTrackingSystem/userController?requestFrom=manager";
	var params = "requestFrom=manager";
	http.open("GET", url, true);
	http.setRequestHeader("Content-Type", "text/html");
	http.responseType = "json";
	http.send();
	http.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(http.response);

			var userList = http.response
			var table = document.createElement("table");
			var form = document.createElement("form");
			form.action = "http://localhost:8080/BugTrackingSystem/userTeamMapping"
			form.method = "post"

			var teamID = document.createElement("input");
			teamID.value = (new URLSearchParams(window.location.search)).get("teamID");
			teamID.name = "teamID";
			teamID.hidden = true;
			form.appendChild(teamID);



			var tr1 = document.createElement("tr");
			var th1 = document.createElement("th");
			var th2 = document.createElement("th");
			var th3 = document.createElement("th");
			var th4 = document.createElement("th");

			var index = document.createTextNode("index");
			var name = document.createTextNode("Name");
			var role = document.createTextNode("Role");
			var selectBox = document.createTextNode("Add");

			th1.appendChild(index)
			th2.appendChild(name)
			th3.appendChild(role)
			th4.appendChild(selectBox)

			tr1.appendChild(th1)
			tr1.appendChild(th2)
			tr1.appendChild(th3)
			tr1.appendChild(th4)

			table.appendChild(tr1)
			for (var i = 0; i < userList.length; i++) {
				console.log(userList[i])
				var username = userList[i].userName;
				var role = userList[i].userType;
				var row = document.createElement("tr");
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");
				var td4 = document.createElement("td");


				var index = document.createTextNode(i);
				var name = document.createTextNode(username);
				var role = document.createTextNode(role);
				var check = document.createElement("input")
				check.type = 'checkbox';
				check.id = 'memberCheckBox';
				check.name = 'userids';
				check.value = userList[i].userId;

				td1.appendChild(index);
				td2.appendChild(name);
				td3.appendChild(role)
				td4.appendChild(check);

				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				table.appendChild(row);
			}
			form.appendChild(table);
			var submit = document.createElement("input");
			submit.type = "submit"
			submit.value = "submit"
			submit.name = "Add Members";
			form.appendChild(submit);
			document.getElementById("dtable").appendChild(form);
		}
	}
}

function getDeveloperFromTeam() {

	console.log("inside function");
	const http = new XMLHttpRequest();
	if (!http) {
		console.log("Unable to create a XMLHttpRequest Object");
		return false;
	}
	const teamID = (new URLSearchParams(window.location.search)).get("teamID");
	const url = "http://localhost:8080/BugTrackingSystem/userTeamMapping?teamID=" + teamID;
	http.open("GET", url, true);
	http.responseType = "json";
	http.send();
	http.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {


			var userList = http.response

			var table = document.getElementById("membersTable")

			for (var i = 0; i < userList.length; i++) {
				console.log(userList[i])
				var username = userList[i].userName;
				var role = userList[i].userType;
				var row = document.createElement("tr");
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");
				var td4 = document.createElement("td");


				var index = document.createTextNode(i + 1);
				var name = document.createTextNode(username);
				var role = document.createTextNode(role);
				var check = document.createElement("input")
				check.type = 'checkbox';
				check.id = 'memberCheckBox';
				check.name = 'userids';
				check.value = userList[i].userId;

				td1.appendChild(index);
				td2.appendChild(name);
				td3.appendChild(role)
				td4.appendChild(check);

				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				table.appendChild(row);
			}
			document.getElementById("projectFormContainer").hidden = false;
		} else {
			console.log("Something went wrong");
		}
	}
}

function getAllTeamMembers() {

	console.log("inside function");
	const http = new XMLHttpRequest();
	if (!http) {
		console.log("Unable to create a XMLHttpRequest Object");
		return false;
	}
	const teamID = (new URLSearchParams(window.location.search)).get("teamID");
	const url = "http://localhost:8080/BugTrackingSystem/userTeamMapping?teamID=" + teamID;
	http.open("GET", url, true);
	http.responseType = "json";
	http.send();
	http.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {


			var userList = http.response

			var table = document.getElementById("membersTable")

			for (var i = 0; i < userList.length; i++) {
				console.log(userList[i])
				var username = userList[i].userName;
				var role = userList[i].userType;
				var row = document.createElement("tr");
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");
				var td4 = document.createElement("td");


				var index = document.createTextNode(i + 1);
				var name = document.createTextNode(username);
				var role = document.createTextNode(role);
				var check = document.createElement("input")
				check.type = 'checkbox';
				check.id = 'memberCheckBox';
				check.name = 'userids';
				check.value = userList[i].userId;

				td1.appendChild(index);
				td2.appendChild(name);
				td3.appendChild(role)
				td4.appendChild(check);

				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				table.appendChild(row);
			}
			document.getElementById("projectFormContainer").hidden = false;
		} else {
			console.log("Something went wrong");
		}
	}
}


function getAllBugs(){
	
	getAllUser()
	
	console.log("inside function");
	const http = new XMLHttpRequest();
	if (!http) {
		console.log("Unable to create a XMLHttpRequest Object");
		return false;
	}
	const url = "http://localhost:8080/BugTrackingSystem/projectController?requestFrom=manager";
	http.open("GET", url, true);
	http.setRequestHeader("Content-Type", "text/html");
	http.responseType = "json";
	http.send();
	http.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(http.response);
			
			var bugs = http.response;
			
			var table = document.createElement("table")
			var tr1 = document.createElement("tr")
			tr1.appendChild(document.createElement("th").appendChild(document.createTextNode("Bug_Id")))
			tr1.appendChild(document.createElement("th").appendChild(document.createTextNode("Bug Title")))
			tr1.appendChild(document.createElement("th").appendChild(document.createTextNode("Project Id")))
			tr1.appendChild(document.createElement("th").appendChild(document.createTextNode("Status")))
			tr1.appendChild(document.createElement("th").appendChild(document.createTextNode("Severity")))
			table.appendChild(tr1)
			for(var i=0;i<bugs.length;i++){
				var tr = document.createElement("tr");
				tr.appendChild(document.createElement("td").appendChild(document.createTextNode(bugs[i].bugId)))
				tr.appendChild(document.createElement("td").appendChild(document.createTextNode(bugs[i].bugTitle)))
				tr.appendChild(document.createElement("td").appendChild(document.createTextNode(bugs[i].projectId)))
				tr.appendChild(document.createElement("td").appendChild(document.createTextNode(bugs[i].status)))
				tr.appendChild(document.createElement("td").appendChild(document.createTextNode(bugs[i].severity)))
				table.appendChild(tr)
			}
			document.getElementById("bugTable").appendChild(table)
		}
	}
}

function getProjectDetails(){
	console.log("inside function");
	const http = new XMLHttpRequest();
	if (!http) {
		console.log("Unable to create a XMLHttpRequest Object");
		return false;
	}
	const url = "http://localhost:8080/BugTrackingSystem/projectController?requestFrom=manager";
	var params = "requestFrom=manager";
	http.open("GET", url, true);
	http.setRequestHeader("Content-Type", "text/html");
	http.responseType = "json";
	http.send();
	http.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(http.response);

			var projectList = http.response
			var table = document.createElement("table");
			var form = document.createElement("form");
			form.action = "http://localhost:8080/BugTrackingSystem/userTeamMapping"
			form.method = "post"

			var teamID = document.createElement("input");
			teamID.value = (new URLSearchParams(window.location.search)).get("teamID");
			teamID.name = "teamID";
			teamID.hidden = true;
			form.appendChild(teamID);



			var tr1 = document.createElement("tr");
			var th1 = document.createElement("th");
			var th2 = document.createElement("th");
			var th3 = document.createElement("th");
			var th4 = document.createElement("th");

			var index = document.createTextNode("index");
			var name = document.createTextNode("Name");
			var role = document.createTextNode("Role");
			var selectBox = document.createTextNode("Add");

			th1.appendChild(index)
			th2.appendChild(name)
			th3.appendChild(role)
			th4.appendChild(selectBox)

			tr1.appendChild(th1)
			tr1.appendChild(th2)
			tr1.appendChild(th3)
			tr1.appendChild(th4)

			table.appendChild(tr1)
			for (var i = 0; i < userList.length; i++) {
				console.log(userList[i])
				var username = userList[i].userName;
				var role = userList[i].userType;
				var row = document.createElement("tr");
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");
				var td4 = document.createElement("td");


				var index = document.createTextNode(i);
				var name = document.createTextNode(username);
				var role = document.createTextNode(role);
				var check = document.createElement("input")
				check.type = 'checkbox';
				check.id = 'memberCheckBox';
				check.name = 'userids';
				check.value = userList[i].userId;

				td1.appendChild(index);
				td2.appendChild(name);
				td3.appendChild(role)
				td4.appendChild(check);

				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				table.appendChild(row);
			}
			form.appendChild(table);
			var submit = document.createElement("input");
			submit.type = "submit"
			submit.value = "submit"
			submit.name = "Add Members";
			form.appendChild(submit);
			document.getElementById("dtable").appendChild(form);
		}
	}
	
	
	
}