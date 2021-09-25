package com.hsbc.btsapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.btsapp.beans.Team;
import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.beans.enums.UserTypes;
import com.hsbc.btsapp.exceptions.TeamNotFoundException;
import com.hsbc.btsapp.factory.DAOFactory;
import com.google.gson.Gson;


@WebServlet("/userTeamMapping")
public class UserTeamMappingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UserTeamMappingServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		int userID = Integer.parseInt(request.getParameter("userId"));
		UserTypes usertype = UserTypes.getUserType(request.getParameter("userType"));
		try {
			Team team = DAOFactory.getUserTeamMapping().getUserTeam(userID);
//			request.setAttribute("team", team);
			Gson gson  = new Gson();
			String json  = gson.toJson(team);
			response.getWriter().write(json);
		} catch (TeamNotFoundException e) {
			request.setAttribute("errMessage", e.getMessage());
			request.getRequestDispatcher("Homepage.html").forward(request, response);

		}
		switch (usertype) {
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
