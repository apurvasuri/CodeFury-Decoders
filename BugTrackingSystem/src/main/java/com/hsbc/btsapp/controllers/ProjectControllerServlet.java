package com.hsbc.btsapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.beans.Project;
import com.hsbc.btsapp.beans.enums.Status;
import com.hsbc.btsapp.exceptions.ProjectAlreadyExistsException;
import com.hsbc.btsapp.exceptions.ProjectDoesNotExistException;
import com.hsbc.btsapp.factory.DAOFactory;


@WebServlet("/projectController")
public class ProjectControllerServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//		int projectID = Integer.parseInt(request.getParameter("projectID"));
//		String projectName = request.getParameter("projectName");
//		String projectDescription = request.getParameter("projectDescription");
//		Date projectStartDate = null;
//		try {
//			projectStartDate = new SimpleDateFormat("dd/mm/yyyy").parse(request.getParameter("projectStartDate"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		Status projectStatus = Status.valueOf(request.getParameter("projectStatus"));
		int teamID = Integer.parseInt(request.getParameter("teamID"));
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		HttpSession session = request.getSession();

		String role = (String) session.getAttribute("type");
		User user = (User) session.getAttribute("user");
		try {
			List<Project> projectList = DAOFactory.getProjectDAOImpl().getProjectById(teamID);
			request.setAttribute("projectList", projectList);

		} catch (ProjectDoesNotExistException e) {
			response.setStatus(403);
			response.setContentType("text/html");
			response.getWriter().print(e.toString());
			e.printStackTrace();
		}
		request.getRequestDispatcher("DeveloperProjects.jsp").forward(request, response);;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String projectName =  request.getParameter("projectname");
		String projectDescription = request.getParameter("projectdescription");
		int projectID = Integer.parseInt(request.getParameter("projectid"));
		Date projectStartDate = null;
		Status projectStatus = null;
		int teamID = Integer.parseInt(request.getParameter("teamid"));
		
		try {
			projectStartDate = new SimpleDateFormat("dd/mm/yyyy").parse(request.getParameter("projectstartdate"));
		} catch(ParseException e) {
			response.setStatus(403);
			response.setContentType("text/html");
			response.getWriter().print("Date could not be parsed");
			e.printStackTrace();
		}
		try {
			DAOFactory.getProjectDAOImpl().addProject(new Project(projectID, projectName,projectDescription, projectStartDate,projectStatus,teamID));
		} catch(ProjectAlreadyExistsException e) {
			response.setStatus(403);
			response.setContentType("text/html");
			response.getWriter().print(e.toString());
			e.printStackTrace();
		}
		request.getRequestDispatcher("/").forward(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int projectID = Integer.parseInt(request.getParameter("projectID"));
		String projectName = request.getParameter("projectName");
		String projectDescription = request.getParameter("projectDescription");
		Date projectStartDate = null;
		try {
			projectStartDate = new SimpleDateFormat("dd/mm/yyyy").parse(request.getParameter("projectStartDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Status projectStatus = Status.valueOf(request.getParameter("projectStatus"));
		Project project = new Project();
		project.setProjectId(projectID);
		project.setProjectName(projectName);
		project.setProjectDescription(projectDescription);
		project.setProjStartDate(projectStartDate);
		project.setProjectStatus(projectStatus);
		DAOFactory.getProjectDAOImpl().updateProject(project);
		
		
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int projectID = Integer.parseInt(request.getParameter("projectID"));
		String projectName = request.getParameter("projectName");
		String projectDescription = request.getParameter("projectDescription");
		Date projectStartDate = null;
		try {
			projectStartDate = new SimpleDateFormat("dd/mm/yyyy").parse(request.getParameter("projectStartDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Status projectStatus = Status.valueOf(request.getParameter("projectStatus"));
		Project project = new Project();
		project.setProjectId(projectID);
		project.setProjectName(projectName);
		project.setProjectDescription(projectDescription);
		project.setProjStartDate(projectStartDate);
		project.setProjectStatus(projectStatus);
		try {
			DAOFactory.getProjectDAOImpl().deleteProject(project);
		} catch (ProjectDoesNotExistException e) {
			
			e.printStackTrace();
		}
	}
	

}
