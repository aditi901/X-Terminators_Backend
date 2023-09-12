package com.bts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bts.business.Project;

public class ProjectDAO {
    private static Connection con;
    
    public ProjectDAO(Connection con) {
        this.con = con;
    }
    	
    
    public static Connection getConn() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bug_tracking_software", "root", "eshalovesbts");
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return con;
	}

        // Initialize database connection
 

//    public ProjectDAO(Connection connection) {
//        this.connection = connection;
//    }

        private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bugtracking";
        private static final String DATABASE_USER = "root";
        private static final String DATABASE_PASSWORD = "eshalovesbts";

        public static Connection connect() throws SQLException {
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        }

    	
    

    public List<Project> getProjectsByManager(Connection con, int createdBy) {
    	
        // Implement code to retrieve projects managed by a specific manager
        List<Project> projects = new ArrayList<>();
       System.out.println(createdBy);
        try {
            String sql = "SELECT * FROM projects WHERE createdBy = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, createdBy);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
//                Project project = new Project();
//                project.setProjectId(resultSet.getInt("project_id"));
//                project.setName(resultSet.getString("name"));
//                project.setDescription(resultSet.getString("description"));
//                project.setStartDate(resultSet.getString("start_date"));
//                project.setStatus(resultSet.getString("status"));
//                project.setCreatedBy(resultSet.getInt("createdBy"));
            	   int projectId = resultSet.getInt("project_id");
                   String name = resultSet.getString("name");
                   String description = resultSet.getString("description");
                   String startDate = resultSet.getString("start_date");
                   String status = resultSet.getString("status");
                   int createdBy1 = resultSet.getInt("createdBy");
                   projects.add(new Project(projectId, name, description, startDate, status, createdBy1));
          

//                projects.add(project);
                System.out.println("list o f projects"+projects);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return projects;
    }

    public void createProject(Project project) {
    	 try {
             String sql = "INSERT INTO projects (name, description, start_date, status, createdBy) VALUES (?, ?, ?, ?, ?)";
             PreparedStatement preparedStatement = con.prepareStatement(sql);
             preparedStatement.setString(1, project.getName());
             preparedStatement.setString(2, project.getDescription());
             preparedStatement.setString(3, project.getStartDate());
             preparedStatement.setString(4, project.getStatus());
             preparedStatement.setInt(5, project.getCreatedBy());

             preparedStatement.executeUpdate();

         } catch (SQLException e) {
             e.printStackTrace();
         }
    }

    public void closeConnection() {
    	try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Implement code to close the database connection
    }
}