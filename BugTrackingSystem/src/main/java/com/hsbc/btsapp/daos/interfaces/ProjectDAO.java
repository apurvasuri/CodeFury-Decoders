package com.hsbc.btsapp.daos.interfaces;
import java.util.List;

import com.hsbc.btsapp.beans.Project;
import com.hsbc.btsapp.exceptions.ProjectAlreadyExistsException;
import com.hsbc.btsapp.exceptions.ProjectDoesNotExistException;

public interface ProjectDAO {
	public void addProject(Project project) throws ProjectAlreadyExistsException;
	public void updateProject(Project project);
	public List<Project> getProjectById(int teamID) throws ProjectDoesNotExistException;
	public Project getProjectByName(String projectName);
	public List<Project> getAllProjects();
	public void deleteProject(Project project) throws ProjectDoesNotExistException;
	

}
