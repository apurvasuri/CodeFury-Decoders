package com.hsbc.btsapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.btsapp.beans.Team;
import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.exceptions.TeamNotFoundException;
import com.hsbc.btsapp.factory.DAOFactory;


@WebServlet("/userTeamMapping")
public class UserTeamMappingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UserTeamMappingServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int userID = Integer.parseInt(request.getParameter("teamId"));
		try {
			Team team = DAOFactory.getUserTeamMapping().getUserTeam(userID);
			request.setAttribute("team", team);
		} catch (TeamNotFoundException e) {
			request.setAttribute("errMessage", e.getMessage());
		}
		request.getRequestDispatcher(request.getContextPath()+"/user.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int userId=  Integer.parseInt(request.getParameter("userid"));
		int teamId=  Integer.parseInt(request.getParameter("teamId"));
		boolean status =false;
		User user = DAOFactory.getUserDAOImpl().getUserById(userId);
		status = DAOFactory.getUserTeamMapping().addUserToTeam(user, teamId);
		if(status==false) {
			request.setAttribute("errMessage", "User could not be added to team");
		}
		else {
			request.setAttribute("successMessage","User added to team successfully");
		}
		request.getRequestDispatcher(request.getContextPath()+"/user.jsp").forward(request, response);
	}

}
