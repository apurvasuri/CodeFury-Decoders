package com.hsbc.btsapp.daos.interfaces;

import java.util.List;

import com.hsbc.btsapp.beans.Project;
import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.exceptions.ProjectNotFoundException;
import com.hsbc.btsapp.exceptions.UserCouldNotBeAdded;

public interface IUserProjectMapping {
	public boolean addUserToProject(User user, String projectId) throws UserCouldNotBeAdded;

	List<String> getUserProjects(int userId) throws ProjectNotFoundException;
	public int getUserProjectCount(int userId);
}
