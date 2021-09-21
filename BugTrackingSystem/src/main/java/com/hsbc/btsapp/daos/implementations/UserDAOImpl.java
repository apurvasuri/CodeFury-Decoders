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
	public boolean addUser(User user) throws UserAlreadyExistsException {
		String user_count = "select count(user_id) from User where user_id=" + user.getUserId();
		boolean status = false;
		try {
			Connection conn = ConnectionUtils.getConnection();
			PreparedStatement pst1 = conn.prepareStatement(user_count);
			ResultSet rs = pst1.executeQuery();
			int a = 0;
			if (rs.next()) {
				a = rs.getInt(1);
			}
			if (a == 1) {
				throw new UserAlreadyExistsException("User already exists in the database");
			} else {
				PreparedStatement pst2 = conn.prepareStatement(
						"insert into User(user_name, user_email, user_password, user_type) value(?,?,?,?)");
				pst2.setInt(1, user.getUserId());
				pst2.setString(2, user.getUserName());
				pst2.setString(3, user.getEmailId());
				pst2.setString(4, user.getPassword());
				pst2.setString(5, user.getUserType().toString());
				int count = pst2.executeUpdate();
				if (count == 1) {
					System.out.println("User added");
					status=true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return status;
		} finally {
			ConnectionUtils.closeConnection();
		}
		return status;
	}

	@Override
	public void updateUser(User user) {
		String user_count = "select count(user_id) from User where user_id=" + user.getUserId();
		try {
			Connection conn = ConnectionUtils.getConnection();
			PreparedStatement pst1 = conn.prepareStatement(user_count);
			ResultSet rs = pst1.executeQuery();
			if (!rs.next()) {
				System.out.println("User Not Present to update");
			} else {
				PreparedStatement pst2 = conn.prepareStatement(
						"update User set user_name=?,user_email=?,user_password=?,user_type=? where user_id=?");
				pst2.setString(1, user.getUserName());
				pst2.setString(2, user.getEmailId());
				pst2.setString(3, user.getPassword());
				pst2.setString(4, user.getUserType().toString());
				pst2.setDouble(4, user.getUserId());
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
			PreparedStatement pst = conn.prepareStatement("select * from User where user_id=?");
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_email"),
						rs.getString("user_password"), UserTypes.valueOf(rs.getString("user_type")));
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
			PreparedStatement pst = conn.prepareStatement("select * from User where user_name=?");
			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_email"),
						rs.getString("user_password"), UserTypes.valueOf(rs.getString("user_type")));
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
			PreparedStatement pst = conn.prepareStatement("select * from User");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				userList.add(new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_email"),
						rs.getString("user_password"), UserTypes.valueOf(rs.getString("user_type"))));
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
		String user_count = "select count(user_id) from User where user_id=" + user.getUserId();
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
				PreparedStatement pst2 = conn.prepareStatement("delete from User where user_id=?");
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
			PreparedStatement pst = conn.prepareStatement("select * from User where user_email=?");
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
			PreparedStatement pst = conn.prepareStatement("select * from User where user_email=?");
			pst.setString(1, emailId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_email"),
						UserTypes.valueOf(rs.getString("user_type")));
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
}
