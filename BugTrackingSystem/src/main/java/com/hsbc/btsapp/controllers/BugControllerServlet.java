package com.hsbc.btsapp.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.btsapp.beans.Bug;
import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.beans.enums.UserTypes;
import com.hsbc.btsapp.daos.implementations.BugDAOImpl;
import com.hsbc.btsapp.daos.interfaces.BugDAO;
import com.hsbc.btsapp.exceptions.BugAlreadyExitsException;
import com.hsbc.btsapp.factory.DAOFactory;

@WebServlet("/bugController")
public class BugControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BugDAO dao;

	public void init() throws ServletException {
		dao = new BugDAOImpl();
	}

	private List<Bug> getUserBugs(int userId) throws ParseException {
		return DAOFactory.getBugDAOImpl().getAllBugsWithUserId(userId);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get all bugs for users

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("User");
		if (user != null) {
			if (user.getUserType() == UserTypes.DEV) {
				try {
					List<Bug> userBugs = getUserBugs(user.getUserId());
					request.setAttribute("buglist", userBugs);
					request.getRequestDispatcher("/views/DeveloperJsp.jsp").forward(request, response);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}

		else {

			if (request.getParameter("update") != null) {
				if (request.getParameter("update").isEmpty() == false) {
					if (request.getParameter("update").equalsIgnoreCase("true")) {
						System.out.println("alksjda;lksd");
						doPut(request, response);
					}
				}
			} else {
				String projectId = request.getParameter("project_id");
				List<Bug> bugList;
				try {
					bugList = dao.getAllBugsWithProjectId(projectId);
					request.setAttribute("buglist", bugList);
					request.getRequestDispatcher("/showbugs.jsp").forward(request, response);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session == null) {
			request.getRequestDispatcher("Homepage.html").forward(request, response);
		}
		if (session.getAttribute("User") == null) {
			request.getRequestDispatcher("Homepage.html").forward(request, response);
		}
		User user = (User) session.getAttribute("User");

		if (user != null) {

			// only DEV can close
			switch (user.getUserType()) {

			case DEV: {
				String bugID = request.getParameter("bugID");
				System.out.println(bugID);
				if (bugID != null) {
					if (!bugID.isEmpty()) {
						System.out.println(bugID);

						Bug bug = DAOFactory.getBugDAOImpl().getBugWithBugId(bugID);
						System.out.println(bug);
						bug.setMarkedForClosing(true);
						DAOFactory.getBugDAOImpl().updateBugByBugId(bug);
						request.getRequestDispatcher("/views/DeveloperJsp.jsp").forward(request, response);
					}
				}
			}
				break;

			case TESTER: {
				int createdBy = Integer.parseInt(request.getParameter("createdBy"));
				String projectId = request.getParameter("projectID");
				String bugTitle = request.getParameter("bugTitle");
				String bugDescription = request.getParameter("bugDescription");
				String bugId = projectId + bugTitle;
				String severity = request.getParameter("levels");
				try {
					DAOFactory.getBugDAOImpl()
							.addBug(new Bug(bugId, bugTitle, bugDescription, projectId, createdBy, severity));
					request.getRequestDispatcher("/views/TesterJsp.jsp").forward(request, response);

				} catch (BugAlreadyExitsException e) {
					response.setStatus(403);
					response.setContentType("text/html");
					response.getWriter().print(e.toString());
					e.printStackTrace();
				}

			}
				break;
			case PM: {
				String assign = request.getParameter("assign");
				System.out.println(assign);
				if (assign != null) {
					if (!assign.isEmpty()) {
						System.out.println(assign);
						doPut(request, response);
					}
				}
			}
				break;
			default:
				request.getRequestDispatcher("Homepage.html").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("Homepage.html").forward(request, response);

		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		if (user.getUserType() == UserTypes.PM) {
			String bugId = request.getParameter("bugID");
			Bug bug = DAOFactory.getBugDAOImpl().getBugWithBugId(bugId);
			int assignedTo = Integer.parseInt(request.getParameter("developerID"));  
			bug.setAssignedBy(user.getUserId());
			bug.setAssignedTo(assignedTo);
			DAOFactory.getBugDAOImpl().updateBugByBugId(bug);
			request.getRequestDispatcher("/views/ProjectManagerJsp.jsp").forward(request, response);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// delete based on bug id
		String bugId = request.getParameter("bugId");
		dao.deleteBugByBugId(bugId);
	}

}
