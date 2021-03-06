package com.hsbc.btsapp.beans;

import com.hsbc.btsapp.beans.enums.UserTypes;

public class User {

	private int userId;
	private String userName;
	private String emailId;
	private String password;
	private UserTypes userType;

	public User() {
	}

	public User(int userId, String userName, String emailId, String password, UserTypes userType) {
		this.userId = userId;
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.userType = userType;
	}

	public User(int userId, String userName, String emailId, UserTypes userType) {
		this.userId = userId;
		this.userName = userName;
		this.emailId = emailId;
		this.userType = userType;
	}

	public User(String userName, String emailId, UserTypes userType) {
		this.userName = userName;
		this.emailId = emailId;
		this.userType = userType;
	}

	public User(String userName, String userEmail, String encrptedPassword, UserTypes userType) {
		this.userName = userName;
		this.emailId = userEmail;
		this.password = encrptedPassword;
		this.userType = userType;
	
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userId=").append(userId).append(", userName=").append(userName).append(", emailId=")
				.append(emailId).append(", password=").append(password).append(", userType=").append(userType)
				.append("]");
		return builder.toString();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserTypes getUserType() {
		return userType;
	}

	public void setUserType(UserTypes userType) {
		this.userType = userType;
	}
}
