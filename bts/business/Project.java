package com.bts.business;

import java.sql.Date;

public class Project {
    private int projectId;
    private String name;
    private String description;
    private String startDate;
    private String status;
    private int createdBy;
    
    
    
    
    
    
	public Project(int projectId, String name, String description, String startDate, String status, int createdBy) {
	
		this.projectId = projectId;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.status = status;
		this.createdBy=createdBy;
	}
//public Project() {
//		// TODO Auto-generated constructor stub
//	}
	//	public Project() {
//		// TODO Auto-generated constructor stub
//	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", name=" + name + ", description=" + description + ", startDate="
				+ startDate + ", status=" + status + "]";
	}
	public void setCreatedBy(int createdBy) {
		// TODO Auto-generated method stub
		
		this.createdBy=createdBy;
		
	}
	public int getCreatedBy() {
		// TODO Auto-generated method stub
		return createdBy;
	}

    // Constructors, getters, and setters
    
    
    
    
}