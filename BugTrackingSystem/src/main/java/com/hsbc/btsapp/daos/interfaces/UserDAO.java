package com.hsbc.btsapp.daos.interfaces;

import java.util.List;

import com.hsbc.btsapp.beans.User;

public interface UserDAO {
	
	void addUser(User user);
	void updateUser(User user);
	User getUserById(int userId);
	User getUserByName(String userName);
	List<User> getAllUsers();
	void deleteUser(User user);

	
}
