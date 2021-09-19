package Group1.CodeFury;

import java.util.Comparator;

public class Bugs implements Comparator<Bugs> {
	private int UniqueId;
	private String Title;
	private String Description;
	private int ProjectId;
	private String createdBy;
	private long OpenDate;
	private String AssignedBy;
	private boolean MarkedForClosing;
	private String ClosedBy;
	private long ClosedOnDate;
	private String status;
	private String severity;
	public String getAssignedBy() {
		return AssignedBy;
	}
	public void setAssignedBy(String assignedBy) {
		AssignedBy = assignedBy;
	}
	public boolean isMarkedForClosing() {
		return MarkedForClosing;
	}
	public void setMarkedForClosing(boolean markedForClosing) {
		MarkedForClosing = markedForClosing;
	}
	public String getClosedBy() {
		return ClosedBy;
	}
	public void setClosedBy(String closedBy) {
		ClosedBy = closedBy;
	}
	public long getClosedOnDate() {
		return ClosedOnDate;
	}
	public void setClosedOnDate(long closedOnDate) {
		ClosedOnDate = closedOnDate;
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
	
	public int getUniqueId() {
		return UniqueId;
	}
	public void setUniqueId(int uniqueId) {
		UniqueId = uniqueId;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
		
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getProjectId() {
		return ProjectId;
	}
	public void setProjectId(int projectId) {
		ProjectId = projectId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public long getOpenDate() {
		return OpenDate;
	}
	public void setOpenDate(long openDate) {
		OpenDate = openDate;
	}
	public int compare(Bugs b1, Bugs b2) {
		return b1.getProjectId()-b2.getProjectId();
	}
	
}
