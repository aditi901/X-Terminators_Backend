package com.bts.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import com.bts.business.*;
import com.bts.dao.UserDAO;
import com.bts.dao.ProjectDAO;
public class BugTrackingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
     
        String jdbcUrl = "jdbc:mysql://localhost:3306/your_database_name";
        String username = "your_username";
        String password = "your_password";

        // Initialize the database connection
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1); // Exit the application if the connection cannot be established
        }

        // Create DAOs and services using the established connection
        UserDAO userDAO = new UserDAO(connection);
        ProjectDAO projectDAO = new ProjectDAO(connection);
        AuthenticationService authService = new AuthenticationService(userDAO);
        ProjectManagementService projectManagementService = new ProjectManagementService(projectDAO, userDAO, authService);

        while (true) {
            System.out.println("Bug Tracking System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Registration:");
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    System.out.print("Confirm your password: ");
                    String confirmPassword = scanner.nextLine();
                    System.out.print("Enter  your role Id (1.Project Manager/2.Tester/3.Developer): ");
                    int roleId = scanner.nextInt();
                    System.out.print("Enter  your Project Id (1.Project1/2.Project2/3.Project3): ");
                    int projectId = scanner.nextInt();

//                    if (authService.isEmailRegistered(email)) {
//                        System.out.println("This email is already registered. Registration failed.");
//                    } else
                    try (Connection con = UserDAO.getConn()){
                    	if (password.equals(confirmPassword)) {
                            authService.register(con, name, email, password, roleId, projectId );
                            System.out.println("Registration successful.");
                        } else {
                            System.out.println("Password and Confirm Password do not match. Registration failed.");
                        }
                    }catch (SQLException e) {
                            e.printStackTrace();
                        }
                    	
                    
                    
                    
                    break;
                case 2:
                	
                    System.out.println("Login:");
                    System.out.print("Enter your email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    password = scanner.nextLine();
                    try (Connection con = UserDAO.getConn()){
                    	authService.login(con ,email, password);
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                    
//                    break;
//                case 3:
//                    // Display User Information code
//                    break;
//                case 4:
                    System.out.println("Project Manager Options:");
                    System.out.println("1. View Managed Projects");
                    System.out.println("2. Create New Project");
                    System.out.print("Enter your choice: ");

                    int pmOption = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (pmOption) {
                        case 1:
                            List<Project> managedProjects = ProjectManagementService.getManagedProjects();
                            for (Project project : managedProjects) {
                                System.out.println("Project Name: " + project.getName());
                                System.out.println("Project Description: " + project.getDescription());
                                System.out.println("Project Start Date: " + project.getStartDate());
                                System.out.println("Project Status: " + project.getStatus());
                            }
                            break;
                        case 2:
                        	  System.out.print("Enter Project id: ");
                              int project_Id = scanner.nextInt();
                           
                            System.out.print("Enter Project Name: ");
                            String projectName = scanner.nextLine();
                            System.out.print("Enter Project Description: ");
                            String projectDescription = scanner.nextLine();
                            System.out.print("Enter Project date: ");
                            String startDate = scanner.next();
                            System.out.print("Enter Project status: ");
                            String status = scanner.nextLine();
                            System.out.print("Enter Project createdby: ");
                            int  createdBy = scanner.nextInt();

                            Project newProject = new Project(project_Id, projectName, projectDescription, startDate, status, createdBy);
                            projectDAO.createProject(newProject);
                            System.out.println("New project created successfully.");
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    userDAO.closeConnection();
                    projectDAO.closeConnection();
                    scanner.close();
                    System.exit(0);
               
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
