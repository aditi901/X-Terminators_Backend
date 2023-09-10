package com.bts.business;

import com.bts.dao.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class BugTrackingLogin {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the Login Page");

            while (true) {
                System.out.print("Email: ");
                String email = scanner.nextLine();

                System.out.print("Password: ");
                String password = scanner.nextLine();

                try (Connection connection = UserDao.connect()) {
                    // Validate login using the UserDao
                    if (UserDao.validateLogin(connection, email, password)) {
                        System.out.println("Login successful! Welcome, " + email);
                        UserDao.markUserAsLoggedIn(connection, email);
                    } else {
                        System.out.println("Login failed. Invalid email or password.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                System.out.print("Do you want to continue? (yes/no): ");
                String response = scanner.nextLine().toLowerCase();
                if (!response.equals("yes")) {
                    break;
                }
            }
        }
    }
}
