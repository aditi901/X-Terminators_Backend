package com.bts.business;



import com.bts.dao.*;
import com.bts.business.Bug;
import com.bts.business.Project;
import com.bts.business.User;

import java.sql.Connection;
import java.util.List;

public class TesterService {
	private static ProjectDAO projectDAO;
	private static BugDAO bugDAO;
		
    private static User currentUser;

    public TesterService() {
        
    }

//    public List<Project> getAssignedProjects(Connection con) {
//        // Implement logic to retrieve projects assigned to the current Tester
//        List<Project> assignedProjects = projectDAO.getProjectsByTester(con, currentUser.getUserId());
//        return assignedProjects;
//    }

    public static void reportBug(Connection con, Bug bug) {
    	BugDAO bugDAO = new BugDAO(con);
        // Implement logic to report a new bug
//        Bug bug = new Bug(title, description, projectId, createdBy, closedDate,  openDate,
//    			 assignedTo,  marked_for_closing, closedBy, closedOn, status, security_level);
    	System.out.println(bug);
    	bugDAO.reportBugs(con,bug);
    }

    public static  List<Bug> getBugsCreatedByTester(Connection con, User currentUser1) {
    	BugDAO bugDAO = new BugDAO(con);
        // Implement logic to retrieve bugs created by the current Tester
        List<Bug> bugsCreatedByTester = bugDAO.getBugsByTester(con,currentUser1.getUserId());
        return bugsCreatedByTester;
    }

}
