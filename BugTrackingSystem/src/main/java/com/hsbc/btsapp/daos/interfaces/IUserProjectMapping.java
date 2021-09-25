package com.hsbc.btsapp.daos.interfaces;

import java.util.List;

import com.hsbc.btsapp.beans.Project;
import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.exceptions.ProjectNotFoundException;

public interface IUserProjectMapping {
	public boolean addUserToProject(User user, String projectId);

	List<String> getUserProjects(int userId) throws ProjectNotFoundException;
}
