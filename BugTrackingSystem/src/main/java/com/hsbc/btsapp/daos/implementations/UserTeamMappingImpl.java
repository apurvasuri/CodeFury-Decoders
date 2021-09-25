package com.hsbc.btsapp.daos.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.btsapp.beans.Team;
import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.daos.interfaces.IUserTeamMapping;
import com.hsbc.btsapp.exceptions.TeamNotFoundException;
import com.hsbc.btsapp.utils.ConnectionUtils;

public class UserTeamMappingImpl implements IUserTeamMapping {

	private ResultSet rs;
	private PreparedStatement ps;

	@Override
	public boolean addUserToTeam(User user, int teamId) {

		boolean status = false;
		String sql = "insert into User_Team_Mapping(user_id,team_id,user_type) values(?,?,?)";
		Connection con = ConnectionUtils.getConnection();

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getUserId());
			ps.setInt(2, teamId);
			ps.setString(3, user.getUserType().getUserType());
			int count = ps.executeUpdate();
			if (count == 1) {
				status = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeConnection();
		}
		return status;

	}

	@Override
	public Team getUserTeam(int userId) throws TeamNotFoundException {
		String sql = "select * from User_Team_Mapping where user_id=?";
		Connection con = ConnectionUtils.getConnection();
		Team team = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				team = new Team(rs.getInt("team_id"), rs.getInt("user_id"));
			} else {
				throw new TeamNotFoundException("No team found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeConnection();
		}
		return team;
	}

	@Override
	public boolean checkInTeam(User user) {
		String sql = "select count(*) from User_Team_Mapping where user_id=?";
		Connection con = ConnectionUtils.getConnection();
		boolean status = false;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getUserId());
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0) {
					status = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeConnection();
		}
		return status;
	}

	@Override
	public List<Integer> getTeamMemeber(int teamID) {
		String sql = "select (user_id) from User_Team_Mapping where team_id=?";
		Connection con = ConnectionUtils.getConnection();
		List<Integer> memberIds = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, teamID);
			rs = ps.executeQuery();
			while (rs.next()) {
				memberIds.add(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeConnection();
		}
		return memberIds;

	}

}
