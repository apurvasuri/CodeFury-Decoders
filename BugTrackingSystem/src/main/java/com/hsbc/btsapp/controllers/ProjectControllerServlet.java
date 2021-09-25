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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.beans.Project;
import com.hsbc.btsapp.beans.Team;
import com.hsbc.btsapp.beans.enums.Status;
import com.hsbc.btsapp.beans.enums.UserTypes;
import com.hsbc.btsapp.exceptions.ProjectAlreadyExistsException;
import com.hsbc.btsapp.exceptions.ProjectDoesNotExistException;
import com.hsbc.btsapp.exceptions.ProjectNotFoundException;
import com.hsbc.btsapp.exceptions.TeamNotFoundException;
import com.hsbc.btsapp.factory.DAOFactory;

@WebServlet("/projectController")
public class ProjectControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("User");
		List<String> projectIds = new ArrayList<>();
		try {
			projectIds = DAOFactory.getUserProjectMapping().getUserProjects(user.getUserId());
		} catch (ProjectNotFoundException e1) {
			e1.printStackTrace();
		}
		List<Project> projectList = new ArrayList<>();
		for (String pId : projectIds) {
			Project project;
			try {
				project = DAOFactory.getProjectDAOImpl().getProjectById(pId);
				projectList.add(project);
			} catch (ProjectDoesNotExistException e) {
				System.out.println(e.getMessage());
			}
		}
		List<Team> pmteamList = new ArrayList<>();
		Team userTeam = null;
		if (user.getUserType() == UserTypes.PM) {
			try {
				pmteamList = DAOFactory.getTeamDAOImpl().getTeamByUserId(user.getUserId());
				request.setAttribute("pmProject", projectList);
				for (Team team : pmteamList) {
					try {
						projectList.addAll(DAOFactory.getProjectDAOImpl().getProjectByTeamId(team.getTeamId()));
					} catch (ProjectDoesNotExistException e) {
						e.printStackTrace();
					}
				}
			} catch (TeamNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				userTeam = DAOFactory.getUserTeamMapping().getUserTeam(user.getUserId());
			} catch (TeamNotFoundException e) {
				e.printStackTrace();
			}
			try {
				projectList.addAll(DAOFactory.getProjectDAOImpl().getProjectByTeamId(userTeam.getTeamId()));
			} catch (ProjectDoesNotExistException e) {
				e.printStackTrace();
			}
			request.setAttribute("userProject", pmteamList);
		}
		int teamId = userTeam.getTeamId();
		if (request.getParameter("teamId") != null || !request.getParameter("teamId").isEmpty())
			teamId = Integer.parseInt(request.getParameter("teamID"));
		try {
			projectList = DAOFactory.getProjectDAOImpl().getProjectByTeamId(teamId);
			request.setAttribute("teamProject", projectList);
		} catch (ProjectDoesNotExistException e) {
			response.setStatus(403);
			response.setContentType("text/html");
			response.getWriter().print(e.toString());
			e.printStackTrace();
		}
		switch (user.getUserType()) {
		case DEV:
			request.getRequestDispatcher("/views/DeveloperJsp.jsp").forward(request, response);
			break;
		case PM:
			request.getRequestDispatcher("/views/ProjectManagerJsp.jsp").forward(request, response);
			break;
		case TESTER:
			request.getRequestDispatcher("/views/TesterJsp.jsp").forward(request, response);
			break;
		default:
			request.setAttribute("errMessage", "Something went wrong");
			request.getRequestDispatcher("Homepage.html").forward(request, response);
		}
	}

	protected boolean validateSelectedUsers(String[] userIds) {
		for (String id : userIds) {
			int userId = Integer.parseInt(id);
			int assignedProjectCount = DAOFactory.getUserProjectMapping().getUserProjectCount(userId);
			User user = DAOFactory.getUserDAOImpl().getUserById(userId);
			System.out.println(user);
			System.out.println(assignedProjectCount);
			switch (user.getUserType()) {
			case DEV:
				if (assignedProjectCount > 0)
					return false;
				break;
			case TESTER:
				if (assignedProjectCount > 1)
					return false;
				break;
			default:
				return false;
			}
		}
		return true;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String projectName = request.getParameter("projectname");
		String projectDescription = request.getParameter("projectdescription");
		Date projectStartDate = null;
		Status projectStatus = Status.INPROG;
		int teamID = Integer.parseInt(request.getParameter("teamID"));
		String projectID = teamID + "_" + projectName;
		String[] selectedUsers = request.getParameterValues("userids");

		boolean userStatus = validateSelectedUsers(selectedUsers);
		if (userStatus == false) {
			request.setAttribute("errMessage", "Developer Max 1 Project And Tester Max Project 2");
			request.getRequestDispatcher("/views/ProjectManagerJsp.jsp").forward(request, response);
		} else {
			try {
				System.out.println(request.getParameter("projectstartdate"));
				projectStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("projectstartdate"));
				System.out.println(new java.sql.Date(projectStartDate.getTime()));
				System.out.println(projectStartDate);
			} catch (ParseException e) {
				response.setStatus(403);
				response.setContentType("text/html");
				response.getWriter().print("Date could not be parsed");
				e.printStackTrace();
			}
			try {
				DAOFactory.getProjectDAOImpl().addProject(new Project(projectID, projectName, projectDescription,
						projectStartDate, projectStatus, teamID));
				request.getRequestDispatcher("/views/ProjectManagerJsp.jsp").forward(request, response);
			} catch (ProjectAlreadyExistsException e) {
				response.setStatus(403);
				response.setContentType("text/html");
				response.getWriter().print(e.toString());
				e.printStackTrace();
			}
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String projectID = request.getParameter("projectID");
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

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String projectID = request.getParameter("projectID");
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
