package com.hsbc.btsapp.daos.interfaces;

import java.util.List;

import com.hsbc.btsapp.beans.Team;
import com.hsbc.btsapp.exceptions.CapacityException;
import com.hsbc.btsapp.exceptions.TeamAlreadyExistsException;
import com.hsbc.btsapp.exceptions.TeamNotFoundException;

public interface TeamDAO {

	public void addTeam(int pmId) throws CapacityException;
	public int getTeamCountOfmanager(int pmId);
	public List<Team> getTeamByUserId(int userId) throws TeamNotFoundException;
	public void updateTeam(Team team) throws TeamNotFoundException;
	public void deleteTeam(Team team) throws TeamNotFoundException;
	public List<String> getTeamByTeamId(int teamId) throws TeamNotFoundException;

}
	
