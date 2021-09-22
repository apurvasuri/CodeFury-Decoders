package com.hsbc.btsapp.daos.interfaces;

import com.hsbc.btsapp.beans.Team;
import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.exceptions.TeamNotFoundException;

public interface IUserTeamMapping {
	
	public boolean addUserToTeam(User user,int teamId);
	public Team getUserTeam(int userId) throws TeamNotFoundException;

}
