package com.example.languagelearningapp.Controllers;

import com.example.languagelearningapp.Game.Game;
import com.example.languagelearningapp.Game.Mode;
import com.example.languagelearningapp.Model.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class WordController {

    @FXML
    private Button exitButton;

    @FXML
    private VBox fiveWordsBox;

    @FXML
    private VBox fourWordsBox;

    @FXML
    private Button hintButton;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button nextButton;

    @FXML
    private Label questionNumberLabel;

    @FXML
    private Button saveGameButton;

    @FXML
    private Button settingsButton;

    @FXML
    private HBox threeWordsBox;

    @FXML
    private Label timeLabel;

    @FXML
    private HBox twoWordsBox;

    @FXML
    private Label wordLabel;

    @FXML
    private Button fiveWordsFiveButton;

    @FXML
    private Button fiveWordsFourButton;

    @FXML
    private Button fiveWordsOneButton;

    @FXML
    private Button fiveWordsThreeButton;

    @FXML
    private Button fiveWordsTwoButton;

    @FXML
    private Button fourWordsFourButton;

    @FXML
    private Button fourWordsOneButton;

    @FXML
    private Button fourWordsThreeButton;

    @FXML
    private Button fourWordsTwoButton;

    @FXML
    private Button threeWordsOneButton;

    @FXML
    private Button threeWordsThreeButton;

    @FXML
    private Button threeWordsTwoButton;

    @FXML
    private Button twoWordsOneButton;

    @FXML
    private Button twoWordsTwoButton;

    private Game game;
    private List<Word> answers;

    @FXML
    public void initialize() {
        game = Game.getInstance();
        answers = game.nextWord();

        switch(game.getDifficulty()){
            case BEGINNER:
                twoWordsBox.setVisible(true);
                break;
            case EASY:
                threeWordsBox.setVisible(true);
                break;
            case MEDIUM:
                fourWordsBox.setVisible(true);
                break;
            case HARD:
                fiveWordsBox.setVisible(true);
                break;
            case EXPERT:
                break;
        }

        if(game.getMode() == Mode.LEARNING){
            timeLabel.setVisible(false);
            questionNumberLabel.setVisible(false);
        }
        else {
            saveGameButton.setDisable(true);
        }

        setWord(answers.get(answers.size() - 1));



    }

    void setWord(Word word){
        wordLabel.setText(word.getWord());

        switch(game.getDifficulty()){
            case BEGINNER:
                twoWordsOneButton.setText(answers.get(0).getTranslation());
                twoWordsTwoButton.setText(answers.get(1).getTranslation());
                break;
            case EASY:
                threeWordsOneButton.setText(answers.get(0).getTranslation());
                threeWordsTwoButton.setText(answers.get(1).getTranslation());
                threeWordsThreeButton.setText(answers.get(2).getTranslation());
                break;
            case MEDIUM:
                fourWordsOneButton.setText(answers.get(0).getTranslation());
                fourWordsTwoButton.setText(answers.get(1).getTranslation());
                fourWordsThreeButton.setText(answers.get(2).getTranslation());
                fourWordsFourButton.setText(answers.get(3).getTranslation());
                break;
            case HARD:
                fiveWordsOneButton.setText(answers.get(0).getTranslation());
                fiveWordsTwoButton.setText(answers.get(1).getTranslation());
                fiveWordsThreeButton.setText(answers.get(2).getTranslation());
                fiveWordsFourButton.setText(answers.get(3).getTranslation());
                fiveWordsFiveButton.setText(answers.get(4).getTranslation());
                break;
            case EXPERT:
                break;
        }
    }


    @FXML
    void answerButtonClicked(ActionEvent event) {

        System.out.println(((Button)event.getSource()).getText());

    }

    @FXML
    void exitButtonClicked(ActionEvent event) {

    }

    @FXML
    void hintButtonClicked(ActionEvent event) {

    }

    @FXML
    void mainMenuButtonClicked(ActionEvent event) throws IOException {
        SceneSwitchUtil.switchScene("main-menu.fxml", (Stage) mainMenuButton.getScene().getWindow());

    }

    @FXML
    void nextButtonClicked(ActionEvent event) {
        answers = game.nextWord();
        setWord(answers.get(answers.size() - 1));

    }

    @FXML
    void saveGameButtonClicked(ActionEvent event) {

    }

    @FXML
    void settingsButtonClicked(ActionEvent event) {

    }

}
