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
	public static void main(String[] args) throws SQLException {
		Scanner scanner = new Scanner(System.in);

		String jdbcUrl = "jdbc:mysql://localhost:3306/bug_tracking_software";
		String username = "root";
		String password = "eshalovesbts";

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
//		ProjectManagementService projectManagementService = new ProjectManagementService(projectDAO, userDAO,
//				authService);
		// TesterService testerService = new TesterService(projectDAO, user);

		while (true) {
			System.out.println("Bug Tracking System");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				System.out.println("Registration:");
				System.out.print("Enter your name: ");
				String name = scanner.nextLine();
				System.out.print("Enter your email: ");
				String email = scanner.nextLine();
				System.out.print("Enter your password: ");
				String password1 = scanner.nextLine();
				System.out.print("Confirm your password: ");
				String confirmPassword = scanner.nextLine();
				System.out.print("Enter  your role Id (1.Project Manager/2.Tester/3.Developer): ");
				int roleId = scanner.nextInt();
				System.out.print("Enter  your Project Id (1.Project1/2.Project2/3.Project3): ");
				int projectId = scanner.nextInt();

//                    if (authService.isEmailRegistered(email)) {
//                        System.out.println("This email is already registered. Registration failed.");
//                    } else
				try (Connection con = UserDAO.getConn()) {
					if (password1.equals(confirmPassword)) {
						authService.register(con, name, email, password1, roleId, projectId);
						System.out.println("Registration successful.");
					} else {
						System.out.println("Password and Confirm Password do not match. Registration failed.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				break;
			case 2:

				System.out.println("Login:");
				System.out.print("Enter your email: ");
				email = scanner.nextLine();
				System.out.print("Enter your password: ");
				password1 = scanner.nextLine();
				try (Connection con = UserDAO.getConn()) {
					authService.login(con, email, password1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Enter role id");

				int role = scanner.nextInt();

				switch (role) {
				// role id for project manager
				case 1:

					System.out.println("Project Manager Options:");
					System.out.println("1. View Managed Projects");
					System.out.println("2. Create New Project");
					System.out.print("Enter your choice: ");

					int pmOption = scanner.nextInt();
					scanner.nextLine(); // Consume newline

					switch (pmOption) {
					case 1:

						try (Connection con = UserDAO.getConn()) {
							List<Project> managedProjects = ProjectManagementService.getManagedProjects(con);
							for (Project project : managedProjects) {
								System.out.println("Project Name: " + project.getName());
								System.out.println("Project Description: " + project.getDescription());
								System.out.println("Project Start Date: " + project.getStartDate());
								System.out.println("Project Status: " + project.getStatus());
							}

						} catch (SQLException e) {
							e.printStackTrace();
						}

						break;
					case 2:
						try (Connection con = UserDAO.getConn()) {
							System.out.println("Enter Project id: ");
							int project_Id = scanner.nextInt();
							scanner.nextLine();

							System.out.println("Enter Project Description ,date,status,createdby");
							String projectName = scanner.nextLine();

							// System.out.println("Enter Project Description: ");
							String projectDescription = scanner.nextLine();
							// System.out.println("Enter Project date: ");
							String startDate = scanner.nextLine();
							// System.out.println("Enter Project status: ");
							String status = scanner.nextLine();
							// System.out.println("Enter Project createdby: ");
							int createdBy = scanner.nextInt();

							Project newProject = new Project(project_Id, projectName, projectDescription, startDate,
									status, createdBy);
							projectDAO.createProject(con, newProject);
							// ProjectManagementService.createProject(con, newProject);
							System.out.println("New project created successfully.");

						} catch (Exception e) {
							// TODO: handle exception2

						}
						break;
					default:
						System.out.println("Invalid option. Please try again.");
						break;
					}
					// pm option swich case
					break;

				case 2:
					// role id for developer

					System.out.println("Main Menu for Developer :");

					System.out.println("1. View Assigned Bugs");
					System.out.println("2. Mark a bug's status as: closed");
					System.out.println("3. Exit");
					System.out.print("Select an option: ");

					int option = scanner.nextInt();
					scanner.nextLine();

					switch (option) {
					case 1:
						// Logic to view bugs assigned to the Developer
						User currentuser1 = authService.getCurrentUser();

						try (Connection con = UserDAO.getConn()) {
							DeveloperService.getBugsAssignedToDeveloper(con, currentuser1);

						} catch (SQLException e) {
							e.printStackTrace();
						}

						break;

					case 2:
                               // mark bug status as closed
						System.out.println("Enter status for the bug");
						String status= scanner.next();
						User currentuser2 = authService.getCurrentUser();

						try (Connection con = UserDAO.getConn()) {
							
							DeveloperService.markStatus(con,status, currentuser2);
							System.out.println("Status updated successfully");

						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						
						break;

					case 3:
						System.out.println("Exiting Bug Tracking System.");
						// closeDatabaseConnection(connection);
						System.exit(0);
						break;

					default:
						System.out.println("Invalid option. Please try again.");
					}

					break;
					

				case 3:

					while (true) {
						// Display main menu and handle user actions
						System.out.println("Main Menu for Tester :");

						System.out.println("1. Report a Bug");
						System.out.println("2. View Bugs Created by You");
						System.out.println("3. Exit");
						System.out.print("Select an option: ");

						int option1 = scanner.nextInt();
						scanner.nextLine(); // Consume the newline character

						switch (option1) {
						case 1:

							// Logic to report a bug
							User currentuser = authService.getCurrentUser();
							System.out.print("Enter Bug Title: ");
							String bugTitle = scanner.nextLine();
							System.out.print("Enter Bug Description: ");
							String bugDescription = scanner.nextLine();
							System.out.print("Enter Project ID: ");
							int projectID = scanner.nextInt();
							scanner.nextLine();
							int createdBy = currentuser.getUserId();
							System.out.println(createdBy);
							System.out.print("Enter Bug closedDate: ");
							String closedDate = scanner.nextLine();
							System.out.print("Enter Bug opendate: ");
							String opendate = scanner.nextLine();
							System.out.print("Enter Project assignedTo: ");
							int assignedTo = scanner.nextInt();
							scanner.nextLine();
							System.out.print("Enter Bug marked_for_closing: ");
							String marked_for_closing = scanner.nextLine();
							System.out.print("Enter Bug closedBy: ");
							int closedBy = scanner.nextInt();
							scanner.nextLine();
							System.out.print("Enter Project closedOn: ");
							String closedOn = scanner.nextLine();
							System.out.print("Enter Bug status: ");
							String status = scanner.nextLine();
							System.out.print("Enter Project security_level: ");
							String severity_level = scanner.nextLine();

							try (Connection connection1 = UserDAO.getConn()) {
								Bug bug = new Bug(bugTitle, bugDescription, projectID, createdBy, closedDate, opendate,
										assignedTo, marked_for_closing, closedBy, closedOn, status, severity_level);
//					            	projectDAO.reportBugs(connection1, bug);
								TesterService.reportBug(connection1, bug);
								System.out.println("Bug reported successfully.");

							} catch (SQLException e) {
								e.printStackTrace();
							}

//					            
							break;
						case 2:
							// Logic to view bugs created by the Tester
							User currentuser1 = authService.getCurrentUser();

							try (Connection con = UserDAO.getConn()) {
								TesterService.getBugsCreatedByTester(con, currentuser1);

							} catch (SQLException e) {
								e.printStackTrace();
							}

							// ...
							break;
						case 3:
							// Exit the application
							System.out.println("Exiting Bug Tracking System.");
							// closeDatabaseConnection(connection);
							System.exit(0);
						default:
							System.out.println("Invalid option. Please try again.");
							break;

						}

					}
				
				default:
					System.out.println("Invalid role id");
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