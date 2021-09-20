package Group1.CodeFury;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BugDAOImpl implements BugDAO  {


public int addBug(Bugs b) throws BugAlreadyExitsException  {
	int i=0;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtrackingsystem","root","");
		PreparedStatement ps=con.prepareStatement("insert into Bug values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		ps.setInt(1,b.getUniqueId());
		ps.setString(2, b.getTitle());
		ps.setString(3,b.getDescription());
		ps.setInt(4,b.getProjectId());
		ps.setString(5, b.getCreatedBy());
		ps.setInt(6,b.getCreatedById());
		ps.setString(7,LocalDate.now().toString());
		ps.setString(8,b.getAssignedBy());
		ps.setInt(9,b.getAssignedById());
		ps.setBoolean(10, b.isMarkedForClosing());
		ps.setString(11, b.getClosedBy());
		ps.setInt(12,b.getClosedById());
		ps.setString(13,b.getClosedOnDate().toString());
		ps.setString(14,b.getStatus());
		ps.setString(15,b.getSeverity());
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
		PreparedStatement ps=con.prepareStatement("update  Bug set bug_title =?, bug_description =?, "
				+ "project_id =?, created_by =?,created_by_id=?,"
				+ " open_date =?, assigned_by =?,assigned_by_id=?, marked_for_closing =?, closed_by =?,closed_by_id=?, closed_on_date =?, status =?, severity =? where bug_id=? ");
		ps.setString(1, b.getTitle());
		ps.setString(2,b.getDescription());
		ps.setInt(3,b.getProjectId());
		ps.setString(4, b.getCreatedBy());
		ps.setInt(5,b.getCreatedById());
		ps.setString(6,b.getOpenDate().toString());
		ps.setString(7,b.getAssignedBy());
		ps.setInt(8,b.getAssignedById());
		ps.setBoolean(9, b.isMarkedForClosing());
		ps.setString(10, b.getClosedBy());
		ps.setInt(11,b.getClosedById());
		ps.setString(12, b.getClosedOnDate().toString());
		ps.setString(13,b.getStatus());
		ps.setString(14,b.getSeverity());
		ps.setInt(15,b.getUniqueId());
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
		PreparedStatement ps=con.prepareStatement("update  Bug set bug_id=?,bug_title =?, bug_description =?,  "
				+ "created_by =?,created_by_id=?, open_date =?, assigned_by =?,assigned_by_id=?, marked_for_closing =?, closed_by =?,closed_by_id=?, closed_on_date =?, status =?, severity =? where project_id=? ");
		ps.setInt(1,b.getUniqueId());
		ps.setString(2, b.getTitle());
		ps.setString(3,b.getDescription());
		ps.setString(4, b.getCreatedBy());
		ps.setInt(5,b.getCreatedById());
		ps.setString(6,b.getOpenDate().toString());
		ps.setString(7,b.getAssignedBy());
		ps.setInt(8,b.getAssignedById());
		ps.setBoolean(9, b.isMarkedForClosing());
		ps.setString(10, b.getClosedBy());
		ps.setInt(11,b.getClosedById());
		ps.setString(12, b.getClosedOnDate().toString());
		ps.setString(13,b.getStatus());
		ps.setString(14,b.getSeverity());
		ps.setInt(15,b.getProjectId());
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
public int DeleteBugs(int UniqueId) throws BugNotPresentException {
	int i=0;
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtrackingsystem","root","");
		PreparedStatement ps = con.prepareStatement("delete from Bug where bug_id=?");
		ps.setInt(1,UniqueId);
		i = ps.executeUpdate();
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
		int created_by_id=rs.getInt(6);
		String open_date=rs.getDate(7).toString();
		String assigned_by=rs.getString(8);
		int assigned_by_id=rs.getInt(9);
		boolean marked_for_closing=rs.getBoolean(10);
		String Closed_by=rs.getString(11);
        int Closed_By_Id=rs.getInt(12);
		String close_date=rs.getDate(13).toString();
		String status=rs.getString(14);
		String severity=rs.getString(15);
		b.setUniqueId(bug_id);
		b.setTitle(bug_title);
		b.setDescription(bug_description);
        b.setClosedOnDate(Date.valueOf(close_date));
        b.setAssignedBy(assigned_by);
		b.setClosedBy(Closed_by);
		b.setCreatedBy(created_by);
		b.setMarkedForClosing(marked_for_closing);
        b.setSeverity(severity);
        b.setProjectId(project_Id);
        b.setOpenDate(Date.valueOf(open_date));
        b.setStatus(status);
        b.setClosedById(Closed_By_Id);
        b.setAssignedById(assigned_by_id);
        b.setCreatedById(created_by_id);
        projectBugs.add(b);
		} 
	} 
	catch (SQLException e) 
	{
		System.out.println(e);
	}
	catch(ClassNotFoundException c) {
		System.out.println(c);
	}
	return projectBugs;
}
}
