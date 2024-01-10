package com.example.languagelearningapp.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button exitButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button startButton;

    @FXML
    void exitButtonClicked(ActionEvent event) {

    }

    @FXML
    void settingsButtonClicked(ActionEvent event) throws IOException {
        SceneSwitchUtil.switchScene("settings.fxml", (Stage) settingsButton.getScene().getWindow());
    }

    @FXML
    void startButtonClicked(ActionEvent event) throws IOException {
        SceneSwitchUtil.switchScene("start-game.fxml", (Stage) settingsButton.getScene().getWindow());

    }

}
