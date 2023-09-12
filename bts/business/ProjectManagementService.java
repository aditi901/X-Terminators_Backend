package com.bts.business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bts.dao.*;
import com.bts.business.*;
public class ProjectManagementService {
    private static ProjectDAO projectDAO;
    private UserDAO userDAO;
    private static AuthenticationService authService;
    private static Connection con;
    
//    public static Connection getConn() {
//		try {
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bug_tracking_software", "root", "eshalovesbts");
//		} catch(SQLException e){
//			System.out.println(e.getMessage());
//		}
//		return con;
//	}

    public ProjectManagementService(ProjectDAO projectDAO, UserDAO userDAO, AuthenticationService authService) {
        this.projectDAO = projectDAO;
        this.authService = authService;
        this.userDAO= userDAO;
    }

    public static List<Project> getManagedProjects() {
    	
        User currentUser = authService.getCurrentUser();
        int pmId = authService.getCurrentUserId();
        System.out.println(pmId);
        System.out.println(currentUser.getRoleId());
        if (currentUser != null && 1==(currentUser.getRoleId())) {
        	System.out.println("hogya");
            // If the current user is a Project Manager, retrieve their managed projects
            return projectDAO.getProjectsByManager(con, pmId);
        } else {
        	System.out.println("na chalra");
            // If the current user is not a Project Manager or not logged in, return an empty list
            return new ArrayList<>();
        }
    }

    public void createProject(Project project) {
        User currentUser = authService.getCurrentUserId();
        
        if (currentUser != null && "Project Manager".equals(currentUser.getUserType())) {
            // If the current user is a Project Manager, create a new project
            projectDAO.createProject(project, currentUser.getUserId());
        } else {
            // If the current user is not a Project Manager or not logged in, show an error message
            System.out.println("Only Project Managers can create projects.");
        }
    }
}