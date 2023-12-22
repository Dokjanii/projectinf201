package com.example.projectpattern;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;
import javafx.util.Duration;

import static com.example.projectpattern.PersonalityTypeDAO.getLatestInsertedUsername;

public class Questions2J {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button agr1;

    @FXML
    private Button agr2;

    @FXML
    private Button agr3;

    @FXML
    private Button agr4;

    @FXML
    private Button back;

    @FXML
    private Button dis1;

    @FXML
    private Button dis2;

    @FXML
    private Button dis3;

    @FXML
    private Button dis4;

    @FXML
    private TextField email;

    @FXML
    private Button help;

    @FXML
    private Button home;

    @FXML
    private Button next;
    @FXML
    private Button logOut;


    @FXML
    private Button setting;

    @FXML
    private Button takeTest;

    @FXML
    private Button types;
    private Stage stage1;
    Parent root;
    private Stage stage;
    private Timeline timeline;
    private List<Button> buttons;
    private List<AtomicBoolean> buttonClicks;
    private int score = 0;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "lolazz15";


    @FXML
    void initialize() {
        setupButtonClickAction(next, "Questions3.fxml");
        setupButtonClickAction(home, "Home.fxml");
        setupButtonClickAction(setting, "Setting.fxml");
        setupButtonClickAction(types, "PersTypes.fxml");
        setupButtonClickAction(back, "AfterLog.fxml");
        setupButtonClickAction(help, "HelpCenter.fxml");
        setupButtonClickAction(logOut, "FirstPage.fxml");
        buttons = new ArrayList<>();
        buttons.add(agr1);
        buttons.add(agr2);
        buttons.add(agr3);
        buttons.add(agr4);
        buttons.add(dis1);
        buttons.add(dis2);
        buttons.add(dis3);
        buttons.add(dis4);

        buttonClicks = new ArrayList<>();
        for (int i = 0; i < buttons.size(); i++) {
            buttonClicks.add(new AtomicBoolean(false));
        }

        setupButtonAnimationActions();

        timeline = new Timeline(new KeyFrame(Duration.millis(50), event -> {
            GaussianBlur blur = new GaussianBlur(43.56);
            for (int i = 0; i < buttons.size(); i++) {
                if (buttonClicks.get(i).get()) {
                    buttons.get(i).setEffect(blur);
                    buttons.get(i).setStyle("  -fx-background-radius: 25; -fx-effect-GaussianBlur;");
                } else {
                    buttons.get(i).setStyle("-fx-background-color: default;");
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void setupButtonClickAction(Button button, String resource) {
        button.setOnAction(event -> {
            try {
                root = FXMLLoader.load(getClass().getResource(resource));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
    }


    private void setupButtonAnimationActions() {
        for (int i = 0; i < buttons.size(); i++) {
            int buttonIndex = i;
            buttons.get(i).setOnAction(event -> {
                buttonClicks.get(buttonIndex).set(!buttonClicks.get(buttonIndex).get());
                if (buttonIndex >= 0 && buttonIndex <= 3) {
                    score++;
                    updateScoreInDatabase(score);
                }
                timeline.stop();
                timeline.play();
            });
        }
    }

    private void updateScoreInDatabase(int score) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String updateQuery = "UPDATE score SET sens_intu = ? WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            String username = getLatestInsertedUsername(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement.setInt(1, score);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}
