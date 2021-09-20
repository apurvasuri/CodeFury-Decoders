package com.hsbc.btsapp.daos.interfaces;

import java.util.List;

import com.hsbc.btsapp.beans.Team;

public interface TeamDAO {

	
	public void addTeam(Team team);
	public List<Team> getTeamByUserId(int userId);
	public void updateTeam(Team team);
	public Team getTeamByTeamId(int teamId);
	
	
}
