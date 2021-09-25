package com.hsbc.btsapp.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.hsbc.btsapp.beans.Project;
import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.exceptions.ProjectDoesNotExistException;
import com.hsbc.btsapp.exceptions.ProjectNotFoundException;
import com.hsbc.btsapp.factory.DAOFactory;

/**
 * Servlet implementation class UserProjectMapping
 */
@WebServlet("/UserProjectMapping")
public class UserProjectMapping extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserProjectMapping() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		int count = DAOFactory.getUserProjectMapping().getUserProjectCount(user.getUserId());
		if (count == 0) {
			request.setAttribute("errMessage", "No Project found");
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
				session.invalidate();
				request.setAttribute("errMessage", "Something went wrong");
				request.getRequestDispatcher("Homepage.html").forward(request, response);
			}
		} else {
			try {
				List<String> projectIds = DAOFactory.getUserProjectMapping().getUserProjects(user.getUserId());
				List<Project> projectList = new ArrayList<Project>();
				projectIds.forEach(id -> {
					try {
						projectList.add(DAOFactory.getProjectDAOImpl().getProjectById(id));
					} catch (ProjectDoesNotExistException e) {
						request.setAttribute("errMessage", "No Project found");
						e.printStackTrace();
					}
				});
				request.setAttribute("userProject", projectList);
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
					session.invalidate();
					request.setAttribute("errMessage", "Something went wrong");
					request.getRequestDispatcher("Homepage.html").forward(request, response);
				}
			} catch (ProjectNotFoundException e) {
				request.setAttribute("errMessage", "No Project found");
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
