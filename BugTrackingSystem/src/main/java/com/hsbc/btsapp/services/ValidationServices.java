package com.hsbc.btsapp.services;

import com.hsbc.btsapp.exceptions.UserNotFoundException;
import com.hsbc.btsapp.factory.DAOFactory;
import com.hsbc.btsapp.utils.PasswordUtils;

public class ValidationServices {

	public static boolean validateUser(String userEmail, String receivedPassword) {

		if (userEmail == "" || receivedPassword == "") {
			return false;
		}

		String userPass = "";
		try {
			userPass = DAOFactory.getUserDAOImpl().getPasswordByEmail(userEmail);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
		boolean status = PasswordUtils.validatePassword(receivedPassword, userPass);
		return status;
	}
}
