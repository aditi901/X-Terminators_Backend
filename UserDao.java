package com.bts.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bugtracking";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "@Tn4903200";

    private static final String SELECT_USER_SQL = "SELECT * FROM users WHERE email = ? AND password = ?";
    private static final String UPDATE_USER_SQL = "UPDATE users SET loggedIn = ? WHERE email = ?";
    private static final String CHECK_LOGGED_IN_SQL = "SELECT loggedIn FROM users WHERE email = ?";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

    public static boolean validateLogin(Connection connection, String email, String password) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_SQL)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();  // Valid login if a matching user is found
        }
    }

    public static void markUserAsLoggedIn(Connection connection, String email) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL)) {
            statement.setBoolean(1, true);
            statement.setString(2, email);
            statement.executeUpdate();
        }
    }

    public static boolean isUserLoggedIn(Connection connection, String email) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CHECK_LOGGED_IN_SQL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() && resultSet.getBoolean("loggedIn");
        }
    }
}

