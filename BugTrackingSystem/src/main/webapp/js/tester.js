function getAllProjects() {
	console.log("inside function");
	const http = new XMLHttpRequest();
	if (!http) {
		console.log("Unable to create a XMLHttpRequest Object");
		return false;
	}
	const url = "http://localhost:8080/BugTrackingSystem/UserProjectMapping?requestFrom=tester";
	http.open("GET", url, true);
	http.setRequestHeader("Content-Type", "text/html");
	http.responseType = "json";
	http.send();
	http.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(http.response);
			var projectList = http.response
			var pOption  = document.getElementById("pOptions")
			while(pOption.firstChild){
				pOption.removeChild(pOption.firstChild);
				
			}
			for(var i=0;i<projectList.length;i++){
				var op = document.createElement("option")
				op.value = projectList[i].projectId;
				op.innerHTML = projectList[i].projectName;
				pOption.appendChild(op);
			}
		}
		document.getElementById("bugForm").hidden=false;
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