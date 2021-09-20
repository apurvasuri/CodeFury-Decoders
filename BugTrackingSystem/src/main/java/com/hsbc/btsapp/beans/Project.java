package com.hsbc.btsapp.beans;

import java.util.Date;

import com.hsbc.btsapp.beans.enums.Status;

public class Project {
	
	private int projectId;
	private String projectName;
	private String projectDescription;
	private Date projectStartDate;
	private Status projectStatus;
	private int teamID;
	
	public Project() {
		
	}
	
	public Project(int projectId, String projectName, String projectDescription, Date projectStartDate,
			Status projectStatus, int teamID) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.projectStartDate = projectStartDate;
		this.projectStatus = projectStatus;
		this.teamID = teamID;
	}
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public Date getProjectStartDate() {
		return projectStartDate;
	}
	public void setProjStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	public Status getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(Status projectStatus) {
		this.projectStatus = projectStatus;
	}
	public int getTeamID() {
		return teamID;
	}
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", projectDescription="
				+ projectDescription + ", projStartDate=" + projectStartDate + ", projectStatus=" + projectStatus
				+ ", teamID=" + teamID + "]";
	}


}
