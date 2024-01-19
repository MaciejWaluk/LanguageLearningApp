package com.example.languagelearningapp.Controllers;

import com.example.languagelearningapp.Game.Difficulty;
import com.example.languagelearningapp.Game.Game;
import com.example.languagelearningapp.Game.Language;
import com.example.languagelearningapp.Game.Mode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button difficultyButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button languageButton;

    @FXML
    private Button modeButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button startButton;

    private Difficulty difficulty = Difficulty.BEGINNER;
    private Language language = Language.POL;
    private Mode mode = Mode.LEARNING;


    @FXML
    void difficultyButtonClicked(ActionEvent event) {

        String s = difficultyButton.getText().substring(18);
        String prefix = "Poziom trudności: ";

        switch (s) {
            case "Początkujący":
                difficultyButton.setText(prefix + "Łatwy");
                difficulty = Difficulty.EASY;
                break;
            case "Łatwy":
                difficultyButton.setText(prefix + "Średni");
                difficulty = Difficulty.MEDIUM;
                break;
            case "Średni":
                difficultyButton.setText(prefix + "Trudny");
                difficulty = Difficulty.HARD;
                break;
            case "Trudny":
                difficultyButton.setText(prefix + "Ekspert");
                difficulty = Difficulty.EXPERT;
                break;
            case "Ekspert":
                difficultyButton.setText(prefix + "Początkujący");
                difficulty = Difficulty.BEGINNER;
                break;
            default:
                difficultyButton.setText(prefix + "Średni");
                difficulty = Difficulty.MEDIUM;
                break;
        }

    }

    @FXML
    void exitButtonClicked(ActionEvent event) {

    }

    @FXML
    void languageButtonClicked(ActionEvent event) {

        String s = languageButton.getText();

        switch (s) {
            case "Polski -> Angielski":
                languageButton.setText("Angielski -> Polski");
                language = Language.ENG;
                break;
            case "Angielski -> Polski":
                languageButton.setText("Polski -> Angielski");
                language = Language.POL;
                break;
            default:
                languageButton.setText("Język: Polski");
                language = Language.POL;
                break;
        }


    }

    @FXML
    void modeButtonClicked(ActionEvent event) {

        String s = modeButton.getText();

        switch (s) {
            case "Tryb: Nauka":
                modeButton.setText("Tryb: Test");
                mode = Mode.TEST;
                break;
            case "Tryb: Test":
                modeButton.setText("Tryb: Nauka");
                mode = Mode.LEARNING;
                break;
            default:
                modeButton.setText("Tryb nauki");
                mode = Mode.LEARNING;
                break;
        }

    }

    @FXML
    void settingsButtonClicked(ActionEvent event) {

    }

    @FXML
    void startButtonClicked(ActionEvent event) throws IOException {
        Game game = Game.getInstance();
        game.setStage((Stage) startButton.getScene().getWindow());
        game.setMode(mode);
        game.setDifficulty(difficulty);
        game.setLanguage(language);
        game.startGame();
        SceneSwitchUtil.switchScene("word-answer.fxml", (Stage) startButton.getScene().getWindow());

    }

}
