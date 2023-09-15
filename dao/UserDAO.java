package com.bts.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.bts.business.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserDAO {
    private Map<String, User> users = new HashMap<>();
private static Connection con;

public UserDAO(Connection con) {
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

	  public User login(Connection connection, String email, String password) {
	        try {
	            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, email);
	            preparedStatement.setString(2, password);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                int userId = resultSet.getInt("id");
	                String name = resultSet.getString("name");
	                String email1 = resultSet.getString("email");
	                String pass = resultSet.getString("password");
	                
	                int roleId = resultSet.getInt("role_id");
	                int projectId = resultSet.getInt("project_id");
	                


	                return new User(userId, name, email1, pass, roleId, projectId);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }

	public void register(Connection con, String name, String email, String password, int roleId, int projectId) {
		

		try {
			// Check if the email is already registered
			if (isEmailRegistered(email)) {
				System.out.println("This email is already registered. Registration failed.");
				return;
			}
			
//			 
			  String sql = "INSERT INTO Users (name, email, password, role_id, project_id) VALUES (?,?,?,?,?);";
//			 

	            PreparedStatement preparedStatement = con.prepareStatement(sql);
	            preparedStatement.setString(1, name);
	            preparedStatement.setString(2, email);
	            preparedStatement.setString(3, password);
	            preparedStatement.setInt(4, roleId);
	            preparedStatement.setInt(5, projectId);
	            preparedStatement.executeUpdate();
	            
	           // con.commit();
//	            User user = new User(name, email, password, roleId, projectId);
//	            users.put(email, user);
	            System.out.println("Registration successful.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	 public static User validateLogin(Connection connection, String email, String password) throws SQLException {
		  final String SELECT_USER_SQL = "SELECT * FROM users WHERE email = ? AND password = ?";
	        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_SQL)) {
	            statement.setString(1, email);
	            statement.setString(2, password);
	            ResultSet resultSet = statement.executeQuery();

	          //  ResultSet resultSet = preparedStatement.executeQuery();
	            
	            if (resultSet.next()) {
	                // User found, return user details
	                return new User(
//	                    resultSet.getInt("id"),
	                    resultSet.getString("email"),
	                    resultSet.getString("password")
//	                    resultSet.getBoolean("logged_in")
	                );
	            }
	        }
			return null;
	    }
	
	public boolean isEmailRegistered(String email) {
		return users.containsKey(email);
	}

	public User getUserByEmail(String userEmail) {
		return users.get(userEmail);
	}

	public void closeConnection() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}

//
//package com.bts.dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//import com.bts.business.User;
//
//public class UserDAO {
//    private Map<String, User> users = new HashMap<>();
//    private static Connection connection;
//
//    public static Connection getConn() {
//        try {
//            if (connection == null || connection.isClosed()) {
//                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bug_tracking_software", "root", "eshalovesbts");
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return connection;
//    }
//
//    public void register(String name, String email, String password, String role) {
//        Connection userConnection = null;
//        try {
//            userConnection = getConn();
//            
//            // Check if the email is already registered
//            if (isEmailRegistered(email)) {
//                System.out.println("This email is already registered. Registration failed.");
//                return;
//            }
//            
//            // Fetch the role_id based on roleName from the Role database
//            String roleQuery = "SELECT id FROM roles WHERE name = ?";
//            PreparedStatement roleStmt = userConnection.prepareStatement(roleQuery);
//            roleStmt.setString(1, role);
//            ResultSet roleResultSet = roleStmt.executeQuery();
//            int roleId = 0;
//            if (roleResultSet.next()) {
//                roleId = roleResultSet.getInt("id");
//            }
//
//            // Insert the user into the database
//            String sql = "INSERT INTO users (name, email, password, role_id) VALUES (?, ?, ?, ?)";
//            PreparedStatement preparedStatement = userConnection.prepareStatement(sql);
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, email);
//            preparedStatement.setString(3, password);
//            preparedStatement.setInt(4, roleId);
//            preparedStatement.executeUpdate();
//            
//            User user = new User(name, email, password, role);
//            users.put(email, user);
//            System.out.println("Registration successful.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (userConnection != null) {
//                    userConnection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public boolean isEmailRegistered(String email) {
//        return users.containsKey(email);
//    }
//
//    public User getUserByEmail(String email) {
//        return users.get(email);
//    }
//
//    public void closeConnection() {
//        try {
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
