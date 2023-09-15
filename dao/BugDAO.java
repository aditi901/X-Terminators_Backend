package com.bts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bts.business.Bug;
import com.bts.business.Project;

public class BugDAO {
	private static Connection con;

	public BugDAO(Connection con) {
		this.con = con;
	}

	public static Connection getConn() {
		try {
			if (con == null || con.isClosed()) {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bug_tracking_software", "root",
						"eshalovesbts");
			}
		} catch (SQLException e) {
			System.err.println("Error establishing the database connection: " + e.getMessage());
		}
		return con;
	}

	public void reportBugs(Connection con, Bug bug) {
		System.out.println(bug);

		try {
			System.out.println(bug);
			String sql = "INSERT INTO bug (title, project_id,description,createdBy,closeDate,openDate,assignedTo,marked_for_closing,closedBy,closedOn,status,severity_level\r\n"
					+ "	 ) VALUES (?, ?, ?, ?,?, ?, ?, ?,?, ?, ?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, bug.getTitle());

			preparedStatement.setInt(2, bug.getProjectId());
			preparedStatement.setString(3, bug.getDescription());
			preparedStatement.setInt(4, bug.getCreatedBy());
			preparedStatement.setString(5, bug.getClosedDate());
			preparedStatement.setString(6, bug.getOpenDate());
			preparedStatement.setInt(7, bug.getAssignedTo());
			preparedStatement.setString(8, bug.getMarked_for_closing());
			preparedStatement.setInt(9, bug.getClosedBy());
			preparedStatement.setString(10, bug.getClosedOn());
			preparedStatement.setString(11, bug.getStatus());
			preparedStatement.setString(12, bug.getSeverity_level());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Bug> getBugsByTester(Connection connection, int userId) {
		// Implement code to retrieve projects managed by a specific manager
		List<Bug> bugs = new ArrayList<>();

		System.out.println("list of bugs created by tester");

		try {
			String sql = "SELECT * FROM bug WHERE createdBy = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
//		                
				String name = resultSet.getString("title");
				String description = resultSet.getString("description");
				int projectId = resultSet.getInt("project_id");
				int createdBy = resultSet.getInt("createdBy");
				String openDate = resultSet.getString("openDate");

				String closeDate = resultSet.getString("closeDate");
				int assignedTo = resultSet.getInt("assignedTo");
				String mark_for_closing = resultSet.getString("marked_for_closing");
				int closedBy = resultSet.getInt("closedBy");
				String closedOn = resultSet.getString("closedOn");
				String status = resultSet.getString("status");
				String severity_level = resultSet.getString("severity_level");
				bugs.add(new Bug(name, description, projectId, createdBy, openDate, closeDate, assignedTo,
						mark_for_closing, closedBy, closedOn, status, severity_level));
				// System.out.println(bugs);

			}
			System.out.println(bugs);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bugs;

	}
	


	public void getAssignedDeveloper(Connection connection, int userId) {
		// Implement code to retrieve projects managed by a specific manager
		List<Bug> bugs = new ArrayList<>();

		System.out.println("list of bugs asssigned to developer");

		try {
			String sql = "SELECT * FROM bug WHERE assignedTo = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
//		                
				String name = resultSet.getString("title");
				String description = resultSet.getString("description");
				int projectId = resultSet.getInt("project_id");
				int createdBy = resultSet.getInt("createdBy");
				String openDate = resultSet.getString("openDate");

				String closeDate = resultSet.getString("closeDate");
				int assignedTo = resultSet.getInt("assignedTo");
				String mark_for_closing = resultSet.getString("marked_for_closing");
				int closedBy = resultSet.getInt("closedBy");
				String closedOn = resultSet.getString("closedOn");
				String status = resultSet.getString("status");
				String severity_level = resultSet.getString("severity_level");
				bugs.add(new Bug(name, description, projectId, createdBy, openDate, closeDate, assignedTo,
						mark_for_closing, closedBy, closedOn, status, severity_level));
				// System.out.println(bugs);

			}
			System.out.println(bugs);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	//	return bugs;

	}
	
	
	public void markStatusDeveloper(Connection con,String status ,int userId)
	{
		try {
			 String sql = "Update bug set status = ? where assignedTo = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, userId);
            
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