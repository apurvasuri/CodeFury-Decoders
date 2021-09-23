package com.hsbc.btsapp.daos.interfaces;

import java.util.List;

import com.hsbc.btsapp.beans.Team;

public interface TeamDAO {

	
	public void addTeam(Team team) throws TeamAlreadyExistsException;
	public List<Team> getTeamByUserId(int userId) throws TeamNotFoundException;
	public void updateTeam(Team team) throws TeamNotFoundException;
	public void deleteTeam(Team team) throws TeamNotFoundException;
	public List<String> getTeamByTeamId(int teamId) throws TeamNotFoundException;

	
	
}
