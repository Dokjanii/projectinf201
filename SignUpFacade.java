package com.example.projectpattern;

import java.io.IOException;
import java.sql.*;

public class SignUpFacade {

    public static void signUpUser(String username, String email, String password) {
        try {
            Connection connection = establishConnection();
            signup(connection, username, email, password);
            closeConnection(connection);
            System.out.println("Data Registered");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection establishConnection() throws SQLException {
        String connectionString = "jdbc:postgresql://localhost:5432/postgres";
        String usernameDatabase = "postgres";
        String passwordDatabase = "lolazz15";
        return DriverManager.getConnection(connectionString, usernameDatabase, passwordDatabase);
    }

    private static void signup(Connection connection, String username, String email, String password) throws SQLException {
        String signupSQL = "INSERT INTO database2 (username, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(signupSQL)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            System.out.println("Signup successful!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username already exists. Please choose a different username.");
        }
    }

    private static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error closing connection", e);
        }
    }
}

