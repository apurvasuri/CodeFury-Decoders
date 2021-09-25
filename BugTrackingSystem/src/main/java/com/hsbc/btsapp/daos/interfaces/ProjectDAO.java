package com.hsbc.btsapp.daos.interfaces;

import java.util.List;

import com.hsbc.btsapp.beans.Project;
import com.hsbc.btsapp.exceptions.ProjectAlreadyExistsException;
import com.hsbc.btsapp.exceptions.ProjectDoesNotExistException;

public interface ProjectDAO {
	public void addProject(Project project) throws ProjectAlreadyExistsException;

	public void updateProject(Project project);

	public Project getProjectById(String pId) throws ProjectDoesNotExistException;

	public Project getProjectByName(String projectName);

	public List<Project> getAllProjects();

	public void deleteProject(Project project) throws ProjectDoesNotExistException;

	List<Project> getProjectByTeamId(int teamID) throws ProjectDoesNotExistException;

}
