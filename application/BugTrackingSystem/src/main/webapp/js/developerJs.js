function getAllProjects() {
	console.log("inside function");
	const http = new XMLHttpRequest();
	if (!http) {
		console.log("Unable to create a XMLHttpRequest Object");
		return false;
	}
	const url = "http://localhost:8080/BugTrackingSystem/UserProjectMapping";
	http.open("GET", url, true);
	http.setRequestHeader("Content-Type", "text/html");
	http.responseType = "json";
	http.send();
	http.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(http.response);

			var projectList = http.response
			var table = document.createElement("table");


			var tr1 = document.createElement("tr");
			var th1 = document.createElement("th");
			var th2 = document.createElement("th");
			var th3 = document.createElement("th");

			var index = document.createTextNode("Project Name");
			var name = document.createTextNode("Description");
			var role = document.createTextNode("Status");

			th1.appendChild(index)
			th2.appendChild(name)
			th3.appendChild(role)

			tr1.appendChild(th1)
			tr1.appendChild(th2)
			tr1.appendChild(th3)

			table.appendChild(tr1)
			for (var i = 0; i < projectList.length; i++) {
				console.log(projectList[i])
				var projectname = projectList[i].projectName;
				var description = projectList[i].projectDescription;
				var status = projectList[i].projectStatus;
				var row = document.createElement("tr");
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");


				var n = document.createTextNode(projectname);
				var d = document.createTextNode(description);
				var s = document.createTextNode(status);


				td1.appendChild(n);
				td2.appendChild(d);
				td3.appendChild(s)

				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				table.appendChild(row);
			}
			document.getElementById("ptable").appendChild(table);
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