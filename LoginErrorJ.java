package com.example.projectpattern;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class LoginErrorJ {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LoginButF;

    @FXML
    private Hyperlink aboutUs;

    @FXML
    private Button back;

    @FXML
    private Button lets;

    @FXML
    private ImageView photo;

    @FXML
    private Button regErr;

    @FXML
    private ImageView imageNot;

    private Stage stage1;
    Parent root;

    @FXML
    void initialize()  {
        regErr.setOnAction(event ->{
            try {
                root= FXMLLoader.load(getClass().getResource("SignInUp.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage1.setScene(scene);
            stage1.show();
        } );

        back.setOnAction(event ->{
            try {
                root= FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage1.setScene(scene);
            stage1.show();
        } );
        imageNot.setOnMouseClicked(event ->{
            try {
                root= FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage1.setScene(scene);
            stage1.show();
        } );
    }

}
