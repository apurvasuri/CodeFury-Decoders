package Group1.CodeFury;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BugDAOImpl implements BugDAO  {


public int addBug(Bugs b) throws BugAlreadyExitsException  {
	int i=0;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtrackingsystem","root","");
		PreparedStatement ps=con.prepareStatement("insert into Bug values(?,?,?,?,?,?,?,?,?,?,?,?)");
		ps.setInt(1,b.getUniqueId());
		ps.setString(2, b.getTitle());
		ps.setString(3,b.getDescription());
		ps.setInt(4,b.getProjectId());
		ps.setString(5, b.getCreatedBy());
		ps.setDate(6,new java.sql.Date(b.getOpenDate()));
		ps.setString(7,b.getAssignedBy());
		ps.setBoolean(8, b.isMarkedForClosing());
		ps.setString(9, b.getClosedBy());
		ps.setDate(10, new java.sql.Date(b.getClosedOnDate()));
		ps.setString(11,b.getStatus());
		ps.setString(12,b.getSeverity());
		i=ps.executeUpdate();
	}
	catch(ClassNotFoundException c) {
		System.out.println(c);
	}
	catch(SQLException s) {
		System.out.println(s);
	}

	return i;
}
public int updateBugBybugId(Bugs b) {
	int i=0;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtrackingsystem","root","");
		PreparedStatement ps=con.prepareStatement("update  Bug set bug_title =?, bug_description =?, project_id =?, created_by =?, open_date =?, assigned_by =?, marked_for_closing =?, closed_by =?, closed_on_date =?, status =?, severity =? where bug_id=? ");
		ps.setString(1, b.getTitle());
		ps.setString(2,b.getDescription());
		ps.setInt(3,b.getProjectId());
		ps.setString(4, b.getCreatedBy());
		ps.setDate(5,new java.sql.Date(b.getOpenDate()));
		ps.setString(6,b.getAssignedBy());
		ps.setBoolean(7, b.isMarkedForClosing());
		ps.setString(8, b.getClosedBy());
		ps.setDate(9, new java.sql.Date(b.getClosedOnDate()));
		ps.setString(10,b.getStatus());
		ps.setString(11,b.getSeverity());
		ps.setInt(12,b.getUniqueId());
		i=ps.executeUpdate();
	}
	catch(ClassNotFoundException c) {
		System.out.println(c);
	}
	catch(SQLException s) {
		System.out.println(s);
	}

	return i;

}
public int updateBugByProjectId(Bugs b) {
	int i=0;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtrackingsystem","root","");
		PreparedStatement ps=con.prepareStatement("update  Bug set bug_id=?,bug_title =?, bug_description =?,  created_by =?, open_date =?, assigned_by =?, marked_for_closing =?, closed_by =?, closed_on_date =?, status =?, severity =? where project_id=? ");
		ps.setInt(1,b.getUniqueId());
		ps.setString(2, b.getTitle());
		ps.setString(3,b.getDescription());
		ps.setString(4, b.getCreatedBy());
		ps.setDate(5,new java.sql.Date(b.getOpenDate()));
		ps.setString(6,b.getAssignedBy());
		ps.setBoolean(7, b.isMarkedForClosing());
		ps.setString(8, b.getClosedBy());
		ps.setDate(9, new java.sql.Date(b.getClosedOnDate()));
		ps.setString(10,b.getStatus());
		ps.setString(11,b.getSeverity());
		ps.setInt(12,b.getProjectId());
		i=ps.executeUpdate();
	}
	catch(ClassNotFoundException c) {
		System.out.println(c);
	}
	catch(SQLException s) {
		System.out.println(s);
	}

	return i;

}
public List<Bugs> getAllBugswithAprojectId(int projectId) {
	List<Bugs> projectBugs= new ArrayList<Bugs>();
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtrackingsystem","root","");
		PreparedStatement ps = con.prepareStatement("select * from Bug where project_id=?");
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
		Bugs b=new Bugs();
		int bug_id=rs.getInt(1);
		String bug_title=rs.getString(2);
		String bug_description=rs.getString(3);
		int project_Id=rs.getInt(4);
		String created_by=rs.getString(5);
		long open_date=rs.getLong(6);
		String assigned_by=rs.getString(7);
		boolean marked_for_closing=rs.getBoolean(8);
		String Closed_by=rs.getString(9);
		long close_date=rs.getLong(10);
		String status=rs.getString(11);
		String severity=rs.getString(12);
		b.setUniqueId(bug_id);
		b.setTitle(bug_title);
		b.setDescription(bug_description);
        b.setClosedOnDate(close_date);
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
	} 
	catch (SQLException e) 
	{
		e.printStackTrace();
	}
	catch(ClassNotFoundException c) {
		System.out.println(c);
	}
	return projectBugs;
}
}
