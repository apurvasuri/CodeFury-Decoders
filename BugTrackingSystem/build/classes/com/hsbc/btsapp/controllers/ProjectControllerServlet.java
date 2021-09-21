package com.hsbc.btsapp.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet("/projectController")
public class ProjectControllerServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServlet request, HttpServlet response) throws ServletException, IOException {
		String projectName = request.getParameter("projectName");
		String projectDescription = request.getParameter("projectDescription");
		int projectID = Integer.parseInt(request.getParameter("projectID"));
		String 
	}
	

}
