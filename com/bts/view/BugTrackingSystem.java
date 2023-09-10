package com.bts.view;

import java.util.Scanner;
import com.bts.business.*;

public class BugTrackingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthenticationService authService = new AuthenticationService();

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
                    System.out.print("Choose your role (Project Manager/Tester/Developer): ");
                    String role = scanner.nextLine();

                    if (authService.isEmailRegistered(email)) {
                        System.out.println("This email is already registered. Registration failed.");
                    } else if (password.equals(confirmPassword)) {
                        authService.register(name, email, password, role);
                        System.out.println("Registration successful.");
                    } else {
                        System.out.println("Password and Confirm Password do not match. Registration failed.");
                    }
                    break;
                case 2:
                    System.out.println("Login:");
                    System.out.print("Enter your email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    password = scanner.nextLine();
                    authService.login(email, password);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
