package com.bts.business;




import com.bts.dao.*;
import com.bts.business.Bug;
import com.bts.business.Project;
import com.bts.business.User;

import java.sql.Connection;
import java.util.List;

public class DeveloperService {
	private static ProjectDAO projectDAO;
	private static BugDAO bugDAO;
	
    private static User currentUser;

    public DeveloperService() {
        
    }

    public static void getBugsAssignedToDeveloper(Connection con, User currentUser1) {
        // Implement logic to retrieve bugs assigned to developer
    	BugDAO bugDAO = new BugDAO(con);
      
      bugDAO.getAssignedDeveloper(con,currentUser1.getUserId());
//      /List<Bug> bugsAssignedtoDeveloper = 
     // return bugsAssignedtoDeveloper;
    }

    public static void markStatus(Connection con, String status,User currentUser1) {
    	BugDAO bugDAO = new BugDAO(con);
        // Implement logic to report mark status
    	
    	
//        Bug bug = new Bug(title, description, projectId, createdBy, closedDate,  openDate,
//    			 assignedTo,  marked_for_closing, closedBy, closedOn, status, security_level);
//    	System.out.println(bug);
    	bugDAO.markStatusDeveloper(con,status,currentUser1.getUserId());
    }
//
//    public static  List<Bug> getBugsCreatedByTester(Connection con, User currentUser1) {
//    	ProjectDAO projectDAO = new ProjectDAO(con);
//        // Implement logic to retrieve bugs created by the current Tester
//        List<Bug> bugsCreatedByTester = projectDAO.getBugsByTester(con,currentUser1.getUserId());
//        return bugsCreatedByTester;
//    }

}
