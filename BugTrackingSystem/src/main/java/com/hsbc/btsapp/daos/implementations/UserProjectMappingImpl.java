package com.hsbc.btsapp.daos.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.btsapp.beans.Project;
import com.hsbc.btsapp.beans.Team;
import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.daos.interfaces.IUserProjectMapping;
import com.hsbc.btsapp.exceptions.ProjectNotFoundException;
import com.hsbc.btsapp.exceptions.TeamNotFoundException;
import com.hsbc.btsapp.utils.ConnectionUtils;

public class UserProjectMappingImpl implements IUserProjectMapping {

	PreparedStatement ps;
	ResultSet rs;

	@Override
	public boolean addUserToProject(User user, String projectId) {
		boolean status = false;
		String sql = "insert into user_project_mapping(user_id,user_type,project_id) values(?,?,?)";
		Connection con = ConnectionUtils.getConnection();

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getUserId());
			ps.setString(2, user.getUserType().getUserType());
			ps.setString(3, projectId);
			int count = ps.executeUpdate();
			if (count == 1) {
				status = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<String> getUserProjects(int userId) throws ProjectNotFoundException {
		String sql = "select project_id from user_project_mapping where user_id=?";
		Connection con = ConnectionUtils.getConnection();
		List<String> projectIdList = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				String projectId = rs.getString("project_id");
				projectIdList.add(projectId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectIdList;
	}

}
