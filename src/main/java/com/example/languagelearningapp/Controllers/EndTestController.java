package com.example.languagelearningapp.Controllers;

import com.example.languagelearningapp.Game.Game;
import com.example.languagelearningapp.State.ProgramState.TestProgramState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class EndTestController {

    @FXML
    private Button mainMenuButton, quitButton;

    @FXML
    private Label resultLabel;

    @FXML
    private void initialize() {
        resultLabel.setText("Wynik: " + Game.getInstance().getCorrectAnswers() + "/" + TestProgramState.NUMBER_OF_WORDS);
    }


        @FXML
    void mainMenuButtonClicked(ActionEvent event) throws IOException {
        SceneSwitchUtil.switchScene("main-menu.fxml", (Stage) mainMenuButton.getScene().getWindow());

    }

    @FXML
    void quitButtonClicked(ActionEvent event) {

    }

}
