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
import com.hsbc.btsapp.factory.DAOFactory;
import com.hsbc.btsapp.utils.PasswordUtils;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		try {
			status = DAOFactory.getUserDAOImpl()
					.addUser(new User(userEmail, encrptedPassword, UserTypes.valueOf(userType)));
		} catch (UserAlreadyExistsException e) {
			request.setAttribute("errMessage", e.getMessage());
			request.getRequestDispatcher("/register").forward(request, response);
		}
		if (status == true) {
			request.getRequestDispatcher("/Homepage.jsp").forward(request, response);
		} else {
			request.setAttribute("errMessage", "Something went wrong!Please try later");
			request.getRequestDispatcher("/register").forward(request, response);
		}

	}

}
