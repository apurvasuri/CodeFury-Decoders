package com.hsbc.btsapp.daos.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.btsapp.beans.Team;
import com.hsbc.btsapp.daos.interfaces.TeamDAO;
import com.hsbc.btsapp.utils.ConnectionUtils;

public class TeamDAOImpl implements TeamDAO {

	private PreparedStatement ps;

	@Override
	public void addTeam(Team team) {

		Connection con = ConnectionUtils.getConnection();

		try {
			ps = con.prepareStatement("insert into team values(?,?)");
			ps.setInt(1, team.getTeamId());
			ps.setInt(2, team.getUserId());
			int count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeConnection();
		}

	}

	@Override
	public List<Team> getTeamByUserId(int userId) {
		Connection con = ConnectionUtils.getConnection();
		List<Team> teamList = new ArrayList<>();
		try {
			ps = con.prepareStatement("select * from team where user_id=?");
			ps.setInt(1,userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Team team = new Team(rs.getInt(1),rs.getInt(2));
				teamList.add(team);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeConnection();
		}
		return teamList;
	}

	@Override
	public void updateTeam(Team team) {
		
	}

	@Override
	public Team getTeamByTeamId(int teamId) {

		return null;
	}

}
