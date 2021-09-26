package com.hsbc.btsapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.beans.enums.UserTypes;
import com.hsbc.btsapp.exceptions.UserAlreadyExistsException;
import com.hsbc.btsapp.factory.DAOFactory;

@WebServlet("/userController")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Inside userDoGet");

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String requestFrom = request.getParameter("requestFrom");
		System.out.println("requestfrom  " + requestFrom);
		if (requestFrom != null && !requestFrom.isEmpty() && requestFrom.equalsIgnoreCase("manager")) {

			User user = (User) request.getSession().getAttribute("User");
			List<User> userList = DAOFactory.getUserDAOImpl().getAllUsers();
			List<User> validUserList = new ArrayList<>();
			for (User u : userList) {
				boolean existsInTeam = DAOFactory.getUserTeamMapping().checkInTeam(u);
				boolean isPM = u.getUserType() == UserTypes.PM;
				if (isPM==false && existsInTeam == false) {
					validUserList.add(u);
				}
			}
			Gson gson = new Gson();
			String json = gson.toJson(validUserList);
			response.getWriter().write(json);
		}

//		else {
//		int user_id=Integer.parseInt(request.getParameter("user_id"));
//		String user_email=request.getParameter("user_email");
//		String user_name=request.getParameter("user_name");
//		String user_type=request.getParameter("user_type");
//		String user_password=request.getParameter("user_password");
//	        response.setContentType("text/html");
//	        PrintWriter printWriter = response.getWriter();
//	        printWriter.print("<html>");
//	        printWriter.print("<body>");
//	        printWriter.print("<h1>Student Resistration Form Data</h1>");
//	        printWriter.print("<p> UserId :: " + user_id + "</p>");
//	        printWriter.print("<p> UserName :: " + user_name + "</p>");
//	        printWriter.print("<p> UserEmail :: " + user_email + "</p>");
//	        printWriter.print("<p> UserPassword :: " + user_password + "</p>");
//	        printWriter.print("<p>UserType:: "+user_type+"</p");
//	        printWriter.print("</body>");
//	        printWriter.print("</html>");
//	        printWriter.close();
//
//	        System.out.println("UserId :: " + user_id);
//	        System.out.println(" UserName:: " + user_name);
//	        System.out.println("UserEmail:: " + user_email);
//	        System.out.println("UserPassword :: " + user_password);
//	        System.out.println("UserType::"+user_type);
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String user_email = request.getParameter("user_email");
		String user_name = request.getParameter("user_name");
		String user_type = request.getParameter("user_type");
		UserTypes us = UserTypes.valueOf(user_type);
		String user_password = request.getParameter("user_password");
		try {
			DAOFactory.getUserDAOImpl().addUser(new User(user_id, user_name, user_email, user_password, us));
		} catch (UserAlreadyExistsException e) {
			response.setStatus(403);
			response.setContentType("text/html");
			response.getWriter().print(e.toString());
			e.printStackTrace();
		}
		request.getRequestDispatcher("/").forward(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String user_email = request.getParameter("user_email");
		String user_name = request.getParameter("user_name");
		String user_type = request.getParameter("user_type");

		String user_password = request.getParameter("user_password");
		User u = new User();
		u.setUserId(user_id);
		u.setUserName(user_name);
		u.setEmailId(user_email);
		u.setPassword(user_password);
		UserTypes ut = UserTypes.valueOf(user_type);
		u.setUserType(ut);
		DAOFactory.getUserDAOImpl().updateUser(u);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String user_email = request.getParameter("user_email");
		String user_name = request.getParameter("user_name");
		String user_type = request.getParameter("user_type");
		String user_password = request.getParameter("user_password");
		User u = new User();
		u.setUserId(user_id);
		u.setUserName(user_name);
		u.setEmailId(user_email);
		u.setPassword(user_password);
		UserTypes ut = UserTypes.valueOf(user_type);
		u.setUserType(ut);
		DAOFactory.getUserDAOImpl().deleteUser(u);
	}

}
