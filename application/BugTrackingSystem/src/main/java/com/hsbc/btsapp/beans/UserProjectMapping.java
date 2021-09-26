package com.hsbc.btsapp.beans;

import com.hsbc.btsapp.beans.enums.UserTypes;

public class UserProjectMapping {
	
	private int userId;
	private UserTypes userType;
	private String projectId;
	public UserProjectMapping(int userId, UserTypes userType, String projectId) {
		super();
		this.userId = userId;
		this.userType = userType;
		this.projectId = projectId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public UserTypes getUserType() {
		return userType;
	}
	public void setUserType(UserTypes userType) {
		this.userType = userType;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	
	

}
