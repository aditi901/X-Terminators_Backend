package com.bts.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.bts.dao.UserDAO;

public class AuthenticationService {
	  private User user;
	
	  private UserDAO userDAO; // Add a UserDAO instance

	    public AuthenticationService(UserDAO userDAO) {
	        this.userDAO = userDAO;
	    }
	
	
    public Map<String, User> users = new HashMap<>();

    public void register(Connection con, String name, String email, String password, int roleId, int projectId) {
        if (userDAO.isEmailRegistered(email)) {
            System.out.println("This email is already registered. Registration failed.");
            return;
        }
        userDAO.register(con, name, email, password, roleId,projectId);
        System.out.println("Registration successful.");
    }

    
    public boolean isEmailRegistered(String email) {
        return users.containsKey(email);
    }


//    public User login(Connection con,String email, String password) throws SQLException {
//      user= UserDAO.validateLogin(con, email, password);
//	 
//        System.out.println("user mail printing testtt"+user);
//        if (user != null && user.getPassword().equals(password)) {
//            user.setLastLoggedIn(LocalDateTime.now());
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            String formattedDateTime = user.getLastLoggedIn().format(formatter);
//            System.out.println("Welcome, " + user.getName() + "!");
//            System.out.println("rollid, " + user.getRoleId() + "!");
//
//            System.out.println("Email: " + user.getEmail());
//            System.out.println("Last Logged In: " + formattedDateTime);
//        } else {
//            System.out.println("Invalid email or password. Please try again.");
//        }
//        return user;
//    }
    public User login(Connection con, String email, String password) {
        user = userDAO.login(con, email, password);
        user.setLastLoggedIn(LocalDateTime.now());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = user.getLastLoggedIn().format(formatter);
        System.out.println("Welcome, " + user.getName() + "!");
        System.out.println("Email: " + user.getEmail());
        System.out.println("Last Logged In: " + formattedDateTime);

        return user;
    }

    
    public User getCurrentUser() {
    	//System.out.println(user);
        return user;
    }
    
    public int getCurrentUserId() {
        if (user != null) {
        	
            return user.getUserId();
        } else {
            return -1; // Return a default value or handle the case where no user is logged in
        }
    }



}
