package com.hsbc.btsapp.daos.interfaces;

import java.util.List;

import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.beans.enums.UserTypes;
import com.hsbc.btsapp.exceptions.UserAlreadyExistsException;
import com.hsbc.btsapp.exceptions.UserNotFoundException;

public interface UserDAO {
	boolean addUser(User user) throws UserAlreadyExistsException;
	void updateUser(User user);
	User getUserById(int userId);
	User getUserByName(String userName);
	int getUserCount();
	boolean checkUserExistsByEmail(String email);
	boolean checkUserExistsById(int uId);
	String getPasswordByEmail(String emailId) throws UserNotFoundException;
	User getUserByEmail(String emailId) throws UserNotFoundException;
	List<User> getAllUsers();
	void deleteUser(User user);
	boolean importUser(List<User> userlist) throws UserAlreadyExistsException;
}
