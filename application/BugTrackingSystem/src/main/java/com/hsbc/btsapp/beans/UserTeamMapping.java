package com.hsbc.btsapp.beans;

import java.util.Objects;

import com.hsbc.btsapp.beans.enums.UserTypes;

public class UserTeamMapping {

	private int userId;
	private UserTypes userType;
	private int teamID;

	public UserTeamMapping(int userId, UserTypes userType, int teamID) {
		super();
		this.userId = userId;
		this.userType = userType;
		this.teamID = teamID;
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

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	@Override
	public String toString() {
		return "UserTeamMapping [userId=" + userId + ", userType=" + userType + ", teamID=" + teamID + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(teamID, userId, userType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTeamMapping other = (UserTeamMapping) obj;
		return teamID == other.teamID && userId == other.userId && userType == other.userType;
	}

}
