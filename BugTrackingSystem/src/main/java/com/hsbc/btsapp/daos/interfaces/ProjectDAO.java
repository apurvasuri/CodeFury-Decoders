package com.hsbc.btsapp.daos.interfaces;
import java.util.List;

import com.hsbc.btsapp.beans.Project;
import com.hsbc.btsapp.exceptions.ProjectAlreadyExistsException;

public interface ProjectDao {
	public void addProject(Project project) throws ProjectAlreadyExistsException;
	public void updateProject(Project project);
	public Project getProjectById(int projectId) throws ProjectDoesNotExistException;
	public Project getProjectByName(String projectName);
	public List<Project> getAllProjects();
	public void deleteProject(Project project) throws ProjectDoesNotExistException;
	

}
