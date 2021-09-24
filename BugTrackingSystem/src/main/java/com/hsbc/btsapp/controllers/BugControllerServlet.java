package com.hsbc.btsapp.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.btsapp.beans.Bug;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String projectId = request.getParameter("project_id");
		List<Bug> bugList = dao.getAllBugsWithProjectId(projectId);
		request.setAttribute("buglist", bugList);
		request.getRequestDispatcher("/showbugs.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//project name = pname
		String bugTitle = request.getParameter("bugTitle");
		String bugDescription = request.getParameter("bugDescription");
		String projectId = request.getParameter("projectid");
		String createdBy = request.getParameter("createby");
		Date openDate = null, closedDate = null;
		try {
			openDate = new SimpleDateFormat("dd/mm/yyyy").parse(request.getParameter("opendate"));
			closedDate = new SimpleDateFormat("dd/mm/yyyy").parse(request.getParameter("closedate"));
		} catch (ParseException e) {

			response.setStatus(403);
			response.setContentType("text/html");
			response.getWriter().print("Date could not be parsed");
			e.printStackTrace();
		}
		String assignedBy = request.getParameter("assignedBy");
		boolean markedForClosing = Boolean.getBoolean(request.getParameter("markedforclosing"));
		String closedBy = request.getParameter("closedBy");
		String status = request.getParameter("status");
		String severity = request.getParameter("levels");
		try {
			DAOFactory.getBugDAOImpl().addBug(new Bug(bugTitle, bugDescription, projectId, createdBy, openDate,
					assignedBy, markedForClosing, closedBy, closedDate, status, severity));
		} catch (BugAlreadyExitsException e) {
			response.setStatus(403);
			response.setContentType("text/html");
			response.getWriter().print(e.toString());
			e.printStackTrace();
		}

		request.getRequestDispatcher("/").forward(request, response);

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//delete based on bug id
		String bugId = request.getParameter("bugId");
		dao.deleteBugByBugId(bugId);
	}

}
