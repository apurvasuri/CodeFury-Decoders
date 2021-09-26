package com.hsbc.btsapp.daos.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hsbc.btsapp.beans.Bug;
import com.hsbc.btsapp.daos.interfaces.BugDAO;
import com.hsbc.btsapp.exceptions.BugAlreadyExitsException;
import com.hsbc.btsapp.utils.ConnectionUtils;

public class BugDAOImpl implements BugDAO {

	private PreparedStatement ps;

	private java.util.Date parseSQLDate(String date) throws ParseException {
		if (date == null)
			return null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date project_start_date = null;
		try {
			project_start_date = format.parse(date);
		} catch (ParseException e) {
			throw new ParseException(e.getMessage(), 0);
		}

		return project_start_date;

	}

	@Override
	public int addBug(Bug b) throws BugAlreadyExitsException {
		int i = 0;
		try {
			Connection con = ConnectionUtils.getConnection();
//			bugId, bugTitle, bugDescription,projectId,crteatedBy, severity
			ps = con.prepareStatement(
					"insert into Bug(bug_id,bug_title,bug_description,project_id,created_by,severity) values(?,?,?,?,?,?)");
			ps.setString(1, b.getBugId());
			ps.setString(2, b.getBugTitle());
			ps.setString(3, b.getBugDescription());
			ps.setString(4, b.getProjectId());
			ps.setInt(5, b.getCreatedBy());
			ps.setString(6, b.getSeverity());
			i = ps.executeUpdate();
		} catch (SQLException s) {
			System.out.println(s);
		} finally {
			ConnectionUtils.closeConnection();
		}
		return i;
	}

	@Override
	public List<Bug> getAllBugsWithProjectId(String projectId) throws ParseException {
		List<Bug> projectBugs = new ArrayList<Bug>();
		try {
			Connection con = ConnectionUtils.getConnection();
			ps = con.prepareStatement("select * from Bug where project_id=?");
			ps.setString(1, projectId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Bug b = new Bug();
				String bug_id = rs.getString(1);
				String bug_title = rs.getString(2);
				String bug_description = rs.getString(3);
				String project_Id = rs.getString(4);
				int created_by = rs.getInt(5);
				Date open_date = parseSQLDate(rs.getString(6));
				int assigned_by = rs.getInt(7);
				boolean marked_for_closing = rs.getBoolean(8);
				int Closed_by = rs.getInt(9);
				Date close_date = parseSQLDate(rs.getString(10));
				String status = rs.getString(11);
				String severity = rs.getString(12);
				b.setBugId(bug_id);
				b.setBugTitle(bug_title);
				b.setBugDescription(bug_description);
				b.setClosedDate(close_date);
				b.setAssignedBy(assigned_by);
				b.setClosedBy(Closed_by);
				b.setCreatedBy(created_by);
				b.setMarkedForClosing(marked_for_closing);
				b.setSeverity(severity);
				b.setProjectId(project_Id);
				b.setOpenDate(open_date);
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
	public int updateBugByBugId(Bug b) {
		int i = 0;
		try {
			System.out.println(b);
			Connection con = ConnectionUtils.getConnection();
			ps = con.prepareStatement(
					"update  Bug set bug_title =?, bug_description =?, project_id =?, created_by =?, assigned_by =?,assigned_to =?, marked_for_closing =?, closed_by =?, closed_on_date =?, status =?, severity =? where bug_id=? ");
			ps.setString(1, b.getBugTitle());
			ps.setString(2, b.getBugDescription());
			ps.setString(3, b.getProjectId());
			ps.setInt(4, b.getCreatedBy());
			ps.setInt(5, b.getAssignedBy());
			ps.setInt(6, b.getAssignedTo());
			ps.setBoolean(7, b.isMarkedForClosing());
			ps.setInt(8, b.getClosedBy());
			
			if (b.getClosedDate() != null)
				ps.setDate(9, new java.sql.Date(b.getClosedDate().getTime()));
			else
				ps.setDate(9, null);
			ps.setString(10, b.getStatus());
			ps.setString(11, b.getSeverity());
			ps.setString(12, b.getBugId());
			i = ps.executeUpdate();
		} catch (SQLException s) {
			System.out.println(s);
		} finally {
			ConnectionUtils.closeConnection();
		}
		return i;
	}

	@Override
	public int deleteBugByBugId(String bugId) {
		int i = 0;
		try {
			Connection con = ConnectionUtils.getConnection();
			ps = con.prepareStatement("delete from  Bug where bug_id=? ");
			ps.setString(1, bugId);
			i = ps.executeUpdate();
		} catch (SQLException s) {
			System.out.println(s);
		} finally {
			ConnectionUtils.closeConnection();
		}
		return i;
	}

	@Override
	public int deleteBugByProjectId(String projectId) {
		int i = 0;
		try {
			Connection con = ConnectionUtils.getConnection();
			ps = con.prepareStatement("delete from  Bug where bug_id=? ");
			ps.setString(1, projectId);
			i = ps.executeUpdate();
		} catch (SQLException s) {
			System.out.println(s);
		} finally {
			ConnectionUtils.closeConnection();
		}
		return i;
	}

	@Override
	public Bug getBugWithBugId(String bugId) {
		Bug b = null;
		try {
			Connection con = ConnectionUtils.getConnection();
			ps = con.prepareStatement("select * from Bug where bug_id=?");
			ps.setString(1, bugId);
			ResultSet rs = ps.executeQuery();
			b = new Bug();
			if (rs.next()) {
				String bug_id = rs.getString(1);
				String bug_title = rs.getString(2);
				String bug_description = rs.getString(3);
				String project_Id = rs.getString(4);
				int created_by = rs.getInt(5);
				Date open_date = parseSQLDate(rs.getString(6));
				int assigned_by = rs.getInt(7);
				int assigned_to = rs.getInt(8);
				boolean marked_for_closing = rs.getBoolean(9);
				int Closed_by = rs.getInt(10);

				Date close_date = parseSQLDate(rs.getString(11));
				String status = rs.getString(12);
				String severity = rs.getString(13);
				b.setBugId(bug_id);
				b.setBugTitle(bug_title);
				b.setBugDescription(bug_description);
				b.setClosedDate(close_date);
				b.setAssignedBy(assigned_by);
				b.setAssignedTo(assigned_to);
				b.setClosedBy(Closed_by);
				b.setCreatedBy(created_by);
				b.setMarkedForClosing(marked_for_closing);
				b.setSeverity(severity);
				b.setProjectId(project_Id);
				b.setOpenDate(open_date);
				b.setStatus(status);
			}
		} catch (SQLException s) {
			System.out.println(s);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeConnection();
		}
		return b;
	}

	@Override
	public List<Bug> getAllBugsWithUserId(int assignedTo) throws ParseException {
		List<Bug> userBugs = new ArrayList<Bug>();
		try {
			Connection con = ConnectionUtils.getConnection();
			ps = con.prepareStatement("select * from Bug where assigned_to=?");
			ps.setInt(1, assignedTo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Bug b = new Bug();
				String bug_id = rs.getString(1);
				String bug_title = rs.getString(2);
				String bug_description = rs.getString(3);
				String project_Id = rs.getString(4);
				int created_by = rs.getInt(5);
				Date open_date = parseSQLDate(rs.getString(6));
				int assigned_by = rs.getInt(7);
				int assigned_to  =rs.getInt(8);
				boolean marked_for_closing = rs.getBoolean(9);
				int Closed_by = rs.getInt(10);
				Date close_date = parseSQLDate(rs.getString(11));
				String status = rs.getString(12);
				String severity = rs.getString(13);
				b.setBugId(bug_id);
				b.setBugTitle(bug_title);
				b.setBugDescription(bug_description);
				b.setClosedDate(close_date);
				b.setAssignedBy(assigned_by);
				b.setAssignedTo(assigned_to);
				b.setClosedBy(Closed_by);
				b.setCreatedBy(created_by);
				b.setMarkedForClosing(marked_for_closing);
				b.setSeverity(severity);
				b.setProjectId(project_Id);
				b.setOpenDate(open_date);
				b.setStatus(status);
				userBugs.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeConnection();
		}
		return userBugs;	}

}
