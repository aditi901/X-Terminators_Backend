package com.bts.business;

//package com.bugtrackingsystem.model;

public class Bug {
   // private int bugId;
    private String title;
    private String description;
    private int projectId;
  
    private int createdBy;
    private String closedDate;
    private String openDate;
    private int assignedTo;
    private String marked_for_closing;
    private int closedBy;
    private String closedOn;
    private String status;
    private String severity_level;
    
    
    
    
    
    
	public Bug(String title, String description, int projectId, int createdBy, String closedDate, String openDate,
			int assignedTo, String marked_for_closing, int closedBy, String closedOn, String status,
			String severity_level) {
	//	super();
		this.title = title;
		this.description = description;
		this.projectId = projectId;
		this.createdBy = createdBy;
		this.closedDate = closedDate;
		this.openDate = openDate;
		this.assignedTo = assignedTo;
		this.marked_for_closing = marked_for_closing;
		this.closedBy = closedBy;
		this.closedOn = closedOn;
		this.status = status;
		this.severity_level = severity_level;
	}
	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public int getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(int assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getMarked_for_closing() {
		return marked_for_closing;
	}
	public void setMarked_for_closing(String marked_for_closing) {
		this.marked_for_closing = marked_for_closing;
	}
	public int getClosedBy() {
		return closedBy;
	}
	public void setClosedBy(int closedBy) {
		this.closedBy = closedBy;
	}
	public String getClosedOn() {
		return closedOn;
	}
	public void setClosedOn(String closedOn) {
		this.closedOn = closedOn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSeverity_level() {
		return severity_level;
	}
	public void setSeverity_level(String security_level) {
		this.severity_level = security_level;
	}

	@Override
	public String toString() {
		return "Bug [title=" + title + ", description=" + description + ", projectId=" + projectId + ", createdBy="
				+ createdBy + ", closedDate=" + closedDate + ", openDate=" + openDate + ", assignedTo=" + assignedTo
				+ ", marked_for_closing=" + marked_for_closing + ", closedBy=" + closedBy + ", closedOn=" + closedOn
				+ ", status=" + status + ", security_level=" + severity_level + "]";
	}
   
    
    
    

    
    // Constructors, getters, and setters
}

