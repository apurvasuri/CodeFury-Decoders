package com.hsbc.btsapp.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		if (userPass == null || userPass.isEmpty()) {
			return false;
		}
		boolean status = PasswordUtils.validatePassword(receivedPassword, userPass);
		return status;
	}

	public static boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile("^\\S+@\\S+\\.\\S+");
		return pattern.matcher(email).matches();
	}
}
