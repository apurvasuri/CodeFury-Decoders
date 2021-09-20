package com.hsbc.btsapp.beans;

import java.util.Date;

public class Bug {

	private int bugId;
	private String bugTitle;
	private String bugDescription;
	private int projectId;
	private String createdBy;
	private Date openDate;
	private String assignedBy;
	private boolean markedForClosing;
	private String closedBy;
	private Date closedDate;
	private String status;
	private String severity;

	public Bug(int bugId, String bugTitle, String bugDescription, int projectId, String createdBy, Date openDate,
			String assignedBy, boolean markedForClosing, String closedBy, Date closedDate, String status,
			String severity) {
		super();
		this.bugId = bugId;
		this.bugTitle = bugTitle;
		this.bugDescription = bugDescription;
		this.projectId = projectId;
		this.createdBy = createdBy;
		this.openDate = openDate;
		this.assignedBy = assignedBy;
		this.markedForClosing = markedForClosing;
		this.closedBy = closedBy;
		this.closedDate = closedDate;
		this.status = status;
		this.severity = severity;
	}

	
	
	public Bug(String bugTitle, String bugDescription, int projectId, String createdBy, Date openDate,
			String assignedBy, boolean markedForClosing, String closedBy, Date closedDate, String status,
			String severity) {
		super();
		this.bugTitle = bugTitle;
		this.bugDescription = bugDescription;
		this.projectId = projectId;
		this.createdBy = createdBy;
		this.openDate = openDate;
		this.assignedBy = assignedBy;
		this.markedForClosing = markedForClosing;
		this.closedBy = closedBy;
		this.closedDate = closedDate;
		this.status = status;
		this.severity = severity;
	}



	public Bug() {
		// TODO Auto-generated constructor stub
	}

	public int getBugId() {
		return bugId;
	}

	public void setBugId(int bugId) {
		this.bugId = bugId;
	}

	public String getBugTitle() {
		return bugTitle;
	}

	public void setBugTitle(String bugTitle) {
		this.bugTitle = bugTitle;
	}

	public String getBugDescription() {
		return bugDescription;
	}

	public void setBugDescription(String bugDescription) {
		this.bugDescription = bugDescription;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}

	public boolean isMarkedForClosing() {
		return markedForClosing;
	}

	public void setMarkedForClosing(boolean markedForClosing) {
		this.markedForClosing = markedForClosing;
	}

	public String getClosedBy() {
		return closedBy;
	}

	public void setClosedBy(String closedBy) {
		this.closedBy = closedBy;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

}