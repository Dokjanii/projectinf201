package com.example.projectpattern;


import java.sql.*;

public class LoginModel {
    private Connection getConnection() throws SQLException {
        String connectionString = "jdbc:postgresql://localhost:5432/postgres";
        String usernameDatabase = "postgres";
        String passwordDatabase = "lolazz15";
        return DriverManager.getConnection(connectionString, usernameDatabase, passwordDatabase);
    }

    public boolean login(String username, String password) {
        try (Connection connection = getConnection()) {
            String loginSQL = "SELECT * FROM database2 WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(loginSQL)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void loginSaveVisiting(String username) {
        try (Connection connection = getConnection()) {
            String signupSQL = "INSERT INTO visiting (username) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(signupSQL)) {
                preparedStatement.setString(1, username);
                preparedStatement.executeUpdate();
                System.out.println("Login saved for visiting");
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("Error. Login data for visiting can't be saved.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loginSaveScore(String username) {
        try (Connection connection = getConnection()) {
            String signupSQL = "INSERT INTO score (username) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(signupSQL)) {
                preparedStatement.setString(1, username);
//                preparedStatement.executeUpdate();
                System.out.println("Login saved for score");
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("Error. Login data for score can't be saved.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmailFromDatabase(String email) {
        try (Connection connection = getConnection()) {
            String deleteQuery = "DELETE FROM database2 WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, email);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Row deleted successfully.");
                } else {
                    System.out.println("Email not found in the database.");
                    System.out.println(email);
                }

            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("Error deleting email from database: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

