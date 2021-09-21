package com.hsbc.btsapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.daos.interfaces.UserDAO;
import com.hsbc.btsapp.exceptions.UserNotFoundException;
import com.hsbc.btsapp.factory.DAOFactory;
import com.hsbc.btsapp.services.ValidationServices;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SESSION_USEROBJ = "User";

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userEmail = request.getParameter("useremail");
		String password = request.getParameter("password");

		boolean validationStatus = ValidationServices.validateUser(userEmail, password);
		if (validationStatus == false) {

			request.setAttribute("errMessage", "Invalid email or password");
			request.getRequestDispatcher("Login.jsp").forward(request, response);

		} else {
			HttpSession session = request.getSession();
			User user = null;
			try {
				user = DAOFactory.getUserDAOImpl().getUserByEmail(userEmail);
			} catch (UserNotFoundException e) {
				System.out.println(e.getMessage());
				request.setAttribute("errMessage", "Invalid email");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
			session.setAttribute(SESSION_USEROBJ, user);
		}
	}

}
