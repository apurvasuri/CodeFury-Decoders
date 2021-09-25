package com.hsbc.btsapp.daos.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hsbc.btsapp.beans.Team;
import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.daos.interfaces.IUserTeamMapping;
import com.hsbc.btsapp.exceptions.TeamNotFoundException;
import com.hsbc.btsapp.utils.ConnectionUtils;

public class UserTeamMappingImpl implements IUserTeamMapping {

	private ResultSet rs;
	private PreparedStatement ps;
	@Override
	public boolean addUserToTeam(User user,int teamId) {

		boolean status = false;
		String sql = "insert into User_Team_Mapping values(?,?,?)";
		Connection con  =  ConnectionUtils.getConnection();
			
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1,user.getUserId());
			ps.setString(2, user.getUserType().getUserType());
			ps.setInt(3,teamId);
			int count = ps.executeUpdate();
			if(count==1) {
				status=true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
		
	}

	@Override
	public Team getUserTeam(int userId) throws TeamNotFoundException {
		String sql = "select * from User_Team_Mapping where user_id=?";
		Connection con  =  ConnectionUtils.getConnection();
		Team team = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1,userId);
			rs  = ps.executeQuery();
			if(rs.next()) {
				team = new Team(rs.getInt("team_id"), rs.getInt("user_id"));
			}
			else {
				throw new TeamNotFoundException("No team found");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return team;		
	}

}
