package com.hsbc.btsapp.daos.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hsbc.btsapp.beans.Bug;
import com.hsbc.btsapp.daos.interfaces.BugDAO;
import com.hsbc.btsapp.exceptions.BugAlreadyExitsException;
import com.hsbc.btsapp.utils.ConnectionUtils;

public class BugDAOImpl implements BugDAO {

	private PreparedStatement ps;

	@Override
	public int addBug(Bug b) throws BugAlreadyExitsException {
		int i = 0;
		try {
			Connection con = ConnectionUtils.getConnection();
			ps = con.prepareStatement("insert into Bug values(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, b.getBugId());
			ps.setString(2, b.getBugTitle());
			ps.setString(3, b.getBugDescription());
			ps.setInt(4, b.getProjectId());
			ps.setString(5, b.getCreatedBy());
			ps.setDate(6, new java.sql.Date(b.getOpenDate().getTime()));
			ps.setString(7, b.getAssignedBy());
			ps.setBoolean(8, b.isMarkedForClosing());
			ps.setString(9, b.getClosedBy());
			ps.setDate(10, new java.sql.Date(b.getClosedDate().getTime()));
			ps.setString(11, b.getStatus());
			ps.setString(12, b.getSeverity());
			i = ps.executeUpdate();
		} catch (SQLException s) {
			System.out.println(s);
		} finally {
			ConnectionUtils.closeConnection();
		}
		return i;
	}

	@Override
	public int updateBugByBugId(Bug b) {
		int i = 0;
		try {
			Connection con = ConnectionUtils.getConnection();
			ps = con.prepareStatement(
					"update  Bug set bug_title =?, bug_description =?, project_id =?, created_by =?, open_date =?, assigned_by =?, marked_for_closing =?, closed_by =?, closed_on_date =?, status =?, severity =? where bug_id=? ");
			ps.setString(1, b.getBugTitle());
			ps.setString(2, b.getBugDescription());
			ps.setInt(3, b.getProjectId());
			ps.setString(4, b.getCreatedBy());
			ps.setDate(5, new java.sql.Date(b.getOpenDate().getTime()));
			ps.setString(6, b.getAssignedBy());
			ps.setBoolean(7, b.isMarkedForClosing());
			ps.setString(8, b.getClosedBy());
			ps.setDate(9, new java.sql.Date(b.getClosedDate().getTime()));
			ps.setString(10, b.getStatus());
			ps.setString(11, b.getSeverity());
			ps.setInt(12, b.getBugId());
			i = ps.executeUpdate();
		} catch (SQLException s) {
			System.out.println(s);
		} finally {
			ConnectionUtils.closeConnection();
		}
		return i;

	}

	@Override
	public List<Bug> getAllBugswithAprojectId(int projectId) {
		List<Bug> projectBugs = new ArrayList<Bug>();
		try {
			Connection con = ConnectionUtils.getConnection();
			ps = con.prepareStatement("select * from Bug where project_id=?");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Bug b = new Bug();
				int bug_id = rs.getInt(1);
				String bug_title = rs.getString(2);
				String bug_description = rs.getString(3);
				int project_Id = rs.getInt(4);
				String created_by = rs.getString(5);
				long open_date = rs.getLong(6);
				String assigned_by = rs.getString(7);
				boolean marked_for_closing = rs.getBoolean(8);
				String Closed_by = rs.getString(9);
				long close_date = rs.getLong(10);
				String status = rs.getString(11);
				String severity = rs.getString(12);
				b.setBugId(bug_id);
				b.setBugTitle(bug_title);
				b.setBugDescription(bug_description);
				b.setClosedDate(new Date(close_date));
				b.setAssignedBy(assigned_by);
				b.setClosedBy(Closed_by);
				b.setCreatedBy(created_by);
				b.setMarkedForClosing(marked_for_closing);
				b.setSeverity(severity);
				b.setProjectId(project_Id);
				b.setOpenDate(new Date(open_date));
				b.setStatus(status);
				projectBugs.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeConnection();
		}
		return projectBugs;
	}

	@Override
	public int updateBugByProjectId(Bug b) {
		// TODO Auto-generated method stub
		return 0;
	}
}
