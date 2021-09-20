package Group1.CodeFury;


import java.util.Comparator;
import java.util.Date;

public class Bugs implements Comparator<Bugs> {
	private int UniqueId;
	private String Title;
	private String Description;
	private int ProjectId;
	private String createdBy;
	private int createdById;
	private Date OpenDate;
	private String AssignedBy;
	private boolean MarkedForClosing;
	private String ClosedBy;
	private int ClosedById;
	private Date ClosedOnDate;
	private String status;
	private String severity;
	private int AssignedById;
	public int getCreatedById() {
		return createdById;
	}
	public void setCreatedById(int createdById) {
		this.createdById = createdById;
	}
	public int getAssignedById() {
		return AssignedById;
	}
	public void setAssignedById(int assignedById) {
		AssignedById = assignedById;
	}
	public int getClosedById() {
		return ClosedById;
	}
	public void setClosedById(int closedById) {
		ClosedById = closedById;
	}
	
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
	public java.util.Date getClosedOnDate() {
		return ClosedOnDate;
	}
	public void setClosedOnDate(Date closedOnDate) {
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
	public Date getOpenDate() {
		return OpenDate;
	}
	public void setOpenDate(Date openDate) {
		OpenDate = openDate;
	}
	public int compare(Bugs b1, Bugs b2) {
		return b1.getProjectId()-b2.getProjectId();
	}
	
}
