package com.hsbc.btsapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.beans.enums.UserTypes;
import com.hsbc.btsapp.exceptions.UserAlreadyExistsException;
import com.hsbc.btsapp.exceptions.UserNotFoundException;
import com.hsbc.btsapp.factory.DAOFactory;
import com.hsbc.btsapp.utils.PasswordUtils;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String errMessage = "Something went wrong";

	public RegisterServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userEmail = request.getParameter("useremail");
		String userPassword = request.getParameter("password");
		String userType = request.getParameter("role");
		String encrptedPassword = PasswordUtils.encrpyt(userPassword);
		boolean status = false;
		boolean checkIfEmailExist = DAOFactory.getUserDAOImpl().checkUserExistsByEmail(userEmail);
		if (checkIfEmailExist) {
			// Get already imported user to update
			try {
				User user = DAOFactory.getUserDAOImpl().getUserByEmail(userEmail);

				if (!user.getUserType().getUserType().equalsIgnoreCase(userType)) {
					request.setAttribute("errMessage", "Please enter the correct role for you");
					request.getRequestDispatcher("RegisterJsp.jsp").forward(request, response);
				} else {
					user.setPassword(encrptedPassword);
					DAOFactory.getUserDAOImpl().updateUser(user);
					status = true;
					
				}
			} catch (UserNotFoundException e) {
				request.setAttribute("errMessage", "User not found in db. Please import first");
				request.getRequestDispatcher("RegisterJsp.jsp").forward(request, response);
			}
		} else {
			try {
				status = DAOFactory.getUserDAOImpl()
						.addUser(new User(userEmail, encrptedPassword, UserTypes.valueOf(userType)));

			} catch (UserAlreadyExistsException e) {
				request.setAttribute("errMessage", errMessage);
				request.getRequestDispatcher("RegisterJsp.jsp").forward(request, response);
			}
			if (status == true) {
				request.getRequestDispatcher("Homepage.html").forward(request, response);
			} else {
				request.setAttribute("errMessage", errMessage);
				request.getRequestDispatcher("RegisterJsp.jsp").forward(request, response);
			}
		}

	}

}
