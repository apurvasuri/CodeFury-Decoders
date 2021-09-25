package com.hsbc.btsapp.daos.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.beans.enums.UserTypes;
import com.hsbc.btsapp.daos.interfaces.UserDAO;
import com.hsbc.btsapp.exceptions.UserAlreadyExistsException;
import com.hsbc.btsapp.exceptions.UserNotFoundException;
import com.hsbc.btsapp.utils.ConnectionUtils;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean importUser(List<User> userList) throws UserAlreadyExistsException {
		boolean status = false;
		PreparedStatement ps = null;
		try {
			ConnectionUtils.getConnection().setAutoCommit(false);
			for (User user : userList) {

				if (checkUserExistsByEmail(user.getEmailId()) == true) {
					System.out.println("Thorwing UserAlreadyExistsException due to duplicate email");
					throw new UserAlreadyExistsException("User email already exists in database");
				}
				ps = ConnectionUtils.getConnection()
						.prepareStatement("INSERT INTO user(user_name, user_email, user_type) VALUES(?,?,?)");
				ps.setString(1, user.getUserName());
				ps.setString(2, user.getEmailId());
				ps.setString(3, user.getUserType().getUserType().toString());
				int count = ps.executeUpdate();
				if (count == 1) {
					System.out.println("User added");
					status = true;
				}
				ps.clearParameters();
			}
			ConnectionUtils.getConnection().commit();
		} catch (SQLException e) {
			System.out.println("Sql emxception on import" + e.getMessage());
			try {
				ConnectionUtils.getConnection().rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
		} catch (UserAlreadyExistsException e) {
			System.out.println("Caught user UserAlreadyExistsException" + e.getMessage());
			try {
				ConnectionUtils.getConnection().rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
			throw new UserAlreadyExistsException(e.getMessage());
		} finally {
			try {
				ConnectionUtils.getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println(e.getMessage());

			}
			ConnectionUtils.closeConnection();
		}
		return status;
	}

	public boolean addUser(User user) throws UserAlreadyExistsException {
		boolean status = false;
		if (checkUserExistsByEmail(user.getEmailId()))
			throw new UserAlreadyExistsException("User email exists in the database");
		try {
			Connection conn = ConnectionUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"insert into User(user_name, user_email, user_password, user_type) value(?,?,?,?)");
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getEmailId());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getUserType().getUserType().toString());
			int count = ps.executeUpdate();
			if (count == 1) {
				System.out.println("User added");
				status = true;
			}

		} catch (

		SQLException e) {
			e.printStackTrace();
			return status;
		} finally {

			ConnectionUtils.closeConnection();
		}
		return status;
	}

	@Override
	public void updateUser(User user) {
		String user_count = "select count(user_id) from user where user_id=" + user.getUserId();
		try {
			Connection conn = ConnectionUtils.getConnection();
			PreparedStatement pst1 = conn.prepareStatement(user_count);
			ResultSet rs = pst1.executeQuery();
			if (!rs.next()) {
				System.out.println("User Not Present to update");
			} else {
				PreparedStatement pst2 = conn.prepareStatement(
						"update user set user_name=?,user_email=?,user_password=?,user_type=? where user_id=?");
				pst2.setString(1, user.getUserName());
				pst2.setString(2, user.getEmailId());
				pst2.setString(3, user.getPassword());
				pst2.setString(4, user.getUserType().getUserType().toString());
				pst2.setInt(5, user.getUserId());
				int count = pst2.executeUpdate();
				if (count == 1) {
					System.out.println("User Updated");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeConnection();
		}
	}

	@Override
	public User getUserById(int userId) {
		User user = null;
		try {
			Connection conn = ConnectionUtils.getConnection();
			PreparedStatement pst = conn.prepareStatement("select * from user where user_id=?");
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_email"),
						rs.getString("user_password"), UserTypes.getUserType(rs.getString("user_type")));
			} else {
				System.out.println("User not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeConnection();
		}
		return user;
	}

	@Override
	public User getUserByName(String userName) {
		User user = null;
		try {
			Connection conn = ConnectionUtils.getConnection();
			PreparedStatement pst = conn.prepareStatement("select * from user where user_name=?");
			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_email"),
						rs.getString("user_password"), UserTypes.getUserType(rs.getString("user_type")));
			} else {
				System.out.println("User not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeConnection();
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		try {
			Connection conn = ConnectionUtils.getConnection();
			PreparedStatement pst = conn.prepareStatement("select * from user");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				userList.add(new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_email"),
						rs.getString("user_password"), UserTypes.getUserType(rs.getString("user_type"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeConnection();
		}
		return userList;
	}

	@Override
	public void deleteUser(User user) {
		String user_count = "select count(user_id) from user where user_id=" + user.getUserId();
		try {
			Connection conn = ConnectionUtils.getConnection();
			PreparedStatement pst1 = conn.prepareStatement(user_count);
			ResultSet rs = pst1.executeQuery();
			int a = 0;
			if (rs.next()) {
				a = rs.getInt(1);
			}
			if (a == 0) {
				System.out.println("User not found to delete");
			} else {
				PreparedStatement pst2 = conn.prepareStatement("delete from user where user_id=?");
				pst2.setInt(1, user.getUserId());
				int count = pst2.executeUpdate();
				if (count == 1) {
					System.out.println("User Deleted");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeConnection();
		}
	}

	@Override
	public String getPasswordByEmail(String emailId) throws UserNotFoundException {
		String userPass = "";
		try {
			Connection conn = ConnectionUtils.getConnection();
			PreparedStatement pst = conn.prepareStatement("select * from user where user_email=?");
			pst.setString(1, emailId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				userPass = rs.getString("user_password");
			} else {
				throw new UserNotFoundException("Invalid user email");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeConnection();
		}
		return userPass;

	}

	@Override
	public User getUserByEmail(String emailId) throws UserNotFoundException {

		User user = null;
		try {
			Connection conn = ConnectionUtils.getConnection();
			PreparedStatement pst = conn.prepareStatement("select * from user where user_email=?");
			pst.setString(1, emailId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_email"),
						UserTypes.getUserType(rs.getString("user_type")));
			} else {
				throw new UserNotFoundException("Invalid user email");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeConnection();
		}
		return user;
	}

	@Override
	public int getUserCount() {
		Connection conn = ConnectionUtils.getConnection();
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("select count(*) from user");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			ConnectionUtils.closeConnection();
		}
		return count;
	}

	@Override
	public boolean checkUserExistsByEmail(String email) {
		boolean exists = false;
		try {
			PreparedStatement ps = ConnectionUtils.getConnection()
					.prepareStatement("SELECT count(*) FROM user where user_email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			int a = 0;
			if (rs.next()) {
				a = rs.getInt(1);
			}
			if (a == 1) {
				exists = true;
				return exists;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			ConnectionUtils.closeConnection();
		}
		return exists;
	}

	@Override
	public boolean checkUserExistsById(int id) {

		boolean exists = false;
		try {
			PreparedStatement ps1 = ConnectionUtils.getConnection()
					.prepareStatement("select count(*) from user where user_id=" + id);
			ResultSet rs1 = ps1.executeQuery();
			int a = 0;
			if (rs1.next()) {
				a = rs1.getInt(1);
			}
			if (a == 1) {
				exists = true;
				return exists;

			}
		} catch (SQLException e) {
			System.out.println("Sql exception when checking by email" + e.getMessage());
		} finally {
			ConnectionUtils.closeConnection();
		}
		return exists;
	}

}
