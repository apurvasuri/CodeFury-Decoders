package com.hsbc.btsapp.daos.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.btsapp.beans.Team;
import com.hsbc.btsapp.daos.interfaces.TeamDAO;
import com.hsbc.btsapp.exceptions.TeamNotFoundException;
import com.hsbc.btsapp.exceptions.TeamAlreadyExistsException;
import com.hsbc.btsapp.exceptions.UserAlreadyExistsException;
import com.hsbc.btsapp.exceptions.UserNotFoundException;
import com.hsbc.btsapp.utils.ConnectionUtils;



public class TeamDAOImpl implements TeamDAO {

	private PreparedStatement ps;

	
	//Adding Team Data in Database
	@Override
	public void addTeam(Team team) throws TeamAlreadyExistsException{

		String team_count = "select count(team_id) from Teams where team_id=" + team.getTeamId();
		Connection conn=ConnectionUtils.getConnection();
		try
		{
			PreparedStatement pst1 = conn.prepareStatement(team_count);
			ResultSet rs = pst1.executeQuery();
			int a = 0;
			if (rs.next()) 
			{
				a = rs.getInt(1);
			}
			if (a==1)
			{
				throw new TeamAlreadyExistsException("Team Already Exists");
			} 
			else 
			{
				ps = conn.prepareStatement("insert into Teams values(?,?)");
				ps.setInt(1, team.getTeamId());
				ps.setInt(2, team.getUserId());
				int count = ps.executeUpdate();
				if (count == 1)
				{
					System.out.println("Team added");
				}
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
		finally
		{
			ConnectionUtils.closeConnection();
		}

	}

	
	//Get team data from database using UserId
	@Override
	public List<Team> getTeamByUserId(int userId) throws TeamNotFoundException
	{
		Connection con = ConnectionUtils.getConnection();
		List<Team> teamList = new ArrayList<>();
		try {
			ps = con.prepareStatement("select * from Teams where user_id=?");
			ps.setInt(1,userId);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				throw new TeamNotFoundException("Team not Found");
			
			}
			else {
				while(rs.next()) {
					Team team = new Team(rs.getInt(1),rs.getInt(2));
					teamList.add(team);
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeConnection();
		}
		return teamList;
	}

	//get team by team id
	@Override
	public List<String> getTeamByTeamId(int teamId) throws TeamNotFoundException {
		List<String> userList = new ArrayList<>();
		String query="select userId ,userName from User where userId IN (select userId from User_Team_Mapping where teamId="+teamId;
		try {
			Connection conn=ConnectionUtils.getConnection();
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			if(!rs.next()) {
					throw new TeamNotFoundException("Team not Found");
			}
			else {
				while(rs.next()) {
				String user=rs.getInt(1)+rs.getString(2);
				userList.add(user);
				}
			}
	
		}catch(SQLException e) {
		e.printStackTrace();
	}
	finally {
		ConnectionUtils.closeConnection();
	}	
		return userList;

	}
	
	//Update team in database by TeamId
	@Override
	public void updateTeam(Team team) throws TeamNotFoundException{
		String count = "select count(teamId) from Teams where teamId=" + team.getTeamId();
		try {
			Connection conn=ConnectionUtils.getConnection();
			PreparedStatement pst = conn.prepareStatement(count);
			ResultSet rs = pst.executeQuery();
			if(!rs.next()) {
				throw new TeamNotFoundException("Team not Found");
			}
			else {
				PreparedStatement pst_1 = conn.prepareStatement("update Teams set userId=?"
						+ " where teamId=?");
				pst_1.setInt(1, team.getUserId());
				pst_1.setInt(2, team.getTeamId());
				int cnt = pst_1.executeUpdate();
				if (cnt == 1)
				{
					System.out.println("Team Updated");
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionUtils.closeConnection();
		}	
	}


	//Delete team from database
	@Override
	public void deleteTeam(Team team) throws TeamNotFoundException{
		String team_count = "select count(team_id) from Teams where team_id=" + team.getTeamId();
		Connection conn=ConnectionUtils.getConnection();
		try {
			PreparedStatement pst1 = conn.prepareStatement(team_count);
			ResultSet rs = pst1.executeQuery();
			int a = 0;
			if (rs.next())
			{
				a = rs.getInt(1);
			}
			if (a==0)
			{
				throw new TeamNotFoundException("Team not Found");
			}
			else
			{
				PreparedStatement pst2 = conn.prepareStatement("delete from Team where team_id=?");
				pst2.setInt(1, team.getTeamId());
				int count = pst2.executeUpdate();
				if (count == 1)
				{
					System.out.println("Team Deleted");
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally {
			ConnectionUtils.closeConnection();
        }
	}
		
}

