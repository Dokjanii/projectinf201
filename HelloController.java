package com.example.projectpattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class HelloController {

    @FXML
    private Hyperlink forgotpass;

    @FXML
    private Button loginBut;

    @FXML
    private PasswordField password;

    @FXML
    private Button registerBut;

    @FXML
    private TextField user;

    private LoginModel userModel;

    private Stage stage1;
    Parent root;

    @FXML
    void initialize() {
        userModel = new LoginModel();

        registerBut.setOnAction(event -> navigateToSignUp());
        loginBut.setOnAction(event -> {
            try {
                loginAction();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
        forgotpass.setOnAction(event -> showForgotPasswordDialog());
    }

    private void navigateToSignUp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignInUp.fxml"));
            Parent root = loader.load();
            Stage signUpStage = new Stage();
            signUpStage.setTitle("Sign Up");
            signUpStage.setScene(new Scene(root));
            signUpStage.show();
            Stage currentStage = (Stage) registerBut.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loginAction() throws SQLException, IOException {
        String enteredUsername = user.getText();
        String enteredPassword = password.getText();

        if (userModel.login(enteredUsername, enteredPassword)) {
            userModel.loginSaveVisiting(enteredUsername);
            userModel.loginSaveScore(enteredUsername);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfterLog.fxml"));
                Parent root = loader.load();

                Stage afterLoginStage = new Stage();
                afterLoginStage.setTitle("After Login");
                afterLoginStage.setScene(new Scene(root));
                afterLoginStage.show();
                Stage currentStage = (Stage) loginBut.getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Username or password incorrect. Please try again.");
            alert.showAndWait();
        }
    }


    private void showForgotPasswordDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Forgot Password");
        dialog.setHeaderText("Enter your email to reset password:");
        dialog.setContentText("Email:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(email -> {
            userModel.deleteEmailFromDatabase(email);
            Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
            confirmation.setTitle("Password Reset");
            confirmation.setHeaderText(null);
            confirmation.setContentText("Now you can register again.");
            confirmation.showAndWait();
        });
    }

}
