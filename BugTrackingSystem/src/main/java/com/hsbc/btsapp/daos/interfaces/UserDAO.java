package com.hsbc.btsapp.daos.interfaces;

import java.util.List;

import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.exceptions.UserAlreadyExistsException;

public interface UserDAO {
	void addUser(User user) throws UserAlreadyExistsException;
	void updateUser(User user);
	User getUserById(int userId);
	User getUserByName(String userName);
	List<User> getAllUsers();
	void deleteUser(User user);
}
