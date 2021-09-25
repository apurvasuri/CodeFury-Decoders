function getAllProject() {
		httpRequest = new XMLHttpRequest();

		if (!httpRequest) {
			console.log("Unable to create a XMLHttpRequest Object");
			return false;
		}
		
		httpRequest.open("GET", "http://localhost:8080/BugTrackingSystem/projectController",true);
		httpRequest.setRequestHeader("Content-Type", "text/html");
		httpRequest.send();
}