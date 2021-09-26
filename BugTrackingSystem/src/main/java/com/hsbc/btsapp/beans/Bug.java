package com.hsbc.btsapp.beans;

import java.util.Date;

public class Bug {

	private String bugId;
	private String bugTitle;
	private String bugDescription;
	private String projectId;
	private int createdBy;
	private Date openDate;
	private int assignedBy;
	private boolean markedForClosing;
	private int closedBy;
	private Date closedDate;
	private String status;
	private String severity;

	public Bug(String bugId, String bugTitle, String bugDescription, String projectId, int createdBy, Date openDate,
			int assignedBy, boolean markedForClosing, int closedBy, Date closedDate, String status, String severity) {
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

	public Bug(String bugId, String bugTitle, String bugDescription, String projectId, int createdBy, String severity) {
		this.bugId = bugId;
		this.bugTitle = bugTitle;
		this.bugDescription = bugDescription;
		this.projectId = projectId;
		this.createdBy = createdBy;
		this.severity = severity;
	}

	public Bug(String bugTitle, String bugDescription, String projectId, int createdBy, Date openDate, int assignedBy,
			boolean markedForClosing, int closedBy, Date closedDate, String status, String severity) {
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

	public String getBugId() {
		return bugId;
	}

	public void setBugId(String bugId) {
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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public int getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(int assignedBy) {
		this.assignedBy = assignedBy;
	}

	public boolean isMarkedForClosing() {
		return markedForClosing;
	}

	public void setMarkedForClosing(boolean markedForClosing) {
		this.markedForClosing = markedForClosing;
	}

	public int getClosedBy() {
		return closedBy;
	}

	public void setClosedBy(int closedBy) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bug [bugId=").append(bugId).append(", bugTitle=").append(bugTitle).append(", bugDescription=")
				.append(bugDescription).append(", projectId=").append(projectId).append(", createdBy=")
				.append(createdBy).append(", openDate=").append(openDate).append(", assignedBy=").append(assignedBy)
				.append(", markedForClosing=").append(markedForClosing).append(", closedBy=").append(closedBy)
				.append(", closedDate=").append(closedDate).append(", status=").append(status).append(", severity=")
				.append(severity).append("]");
		return builder.toString();
	}

}