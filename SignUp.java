package com.example.projectpattern;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;

public class SignUp {

    @FXML
    private TextField email;


    @FXML
    private Button logBS;

    @FXML
    private PasswordField passwordS;

    @FXML
    private Button registerButS;

    @FXML
    private TextField userS;


    private Stage stage1;
    Parent root;

    @FXML
    void initialize()  {


        logBS.setOnAction(event ->{
            try {
                root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage1.setScene(scene);
            stage1.show();
        } );
        registerButS.setOnAction(event ->{
            try {
                root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage1.setScene(scene);
            stage1.show();
            try {
                SignUpFacade.signUpUser(userS.getText(), email.getText(), passwordS.getText());
            } catch (RuntimeException e) {
                e.printStackTrace();
            }


        } );
    }


}
