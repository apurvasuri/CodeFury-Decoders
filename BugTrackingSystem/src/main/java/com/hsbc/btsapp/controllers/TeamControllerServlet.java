package com.hsbc.btsapp.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.btsapp.beans.Team;
import com.hsbc.btsapp.factory.DAOFactory;

/**
 * Servlet implementation class TeamContollerServlet
 */

@WebServlet("/teamController")
public class TeamControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TeamControllerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userid"));
		List<Team> teamList=DAOFactory.getTeamDAOImpl().getTeamByUserId(userId);
		request.getRequestDispatcher("/").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int teamId = Integer.parseInt(request.getParameter("teamid"));
		int userId = Integer.parseInt(request.getParameter("userid"));
		DAOFactory.getTeamDAOImpl().addTeam(new Team(teamId, userId));
		request.getRequestDispatcher("/").forward(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int teamId = Integer.parseInt(request.getParameter("teamid"));
		int userId = Integer.parseInt(request.getParameter("userid"));
		DAOFactory.getTeamDAOImpl().updateTeam(new Team(teamId, userId));
		request.getRequestDispatcher("/").forward(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int teamId = Integer.parseInt(request.getParameter("teamid"));
		int userId = Integer.parseInt(request.getParameter("userid"));
		DAOFactory.getTeamDAOImpl().deleteTeam(new Team(teamId, userId));
		request.getRequestDispatcher("/").forward(request, response);
	}

}
