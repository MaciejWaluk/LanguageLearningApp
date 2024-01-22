package com.example.languagelearningapp.Controllers;

import com.example.languagelearningapp.Game.Difficulty;
import com.example.languagelearningapp.Game.Game;
import com.example.languagelearningapp.Game.Mode;
import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.Observer.TimeUpdateListener;
import com.example.languagelearningapp.State.ProgramState.LearningProgramState;
import com.example.languagelearningapp.State.ProgramState.ProgramState;
import com.example.languagelearningapp.State.ProgramState.TestProgramState;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

public class WordController implements TimeUpdateListener, Alerts {

    @FXML private ToggleGroup answerGroup;
    @FXML private Button exitButton, hintButton, mainMenuButton, nextButton, saveGameButton, settingsButton, sortButton;
    @FXML private VBox fiveWordsBox, fourWordsBox;
    @FXML private HBox threeWordsBox, twoWordsBox;
    @FXML private ToggleButton fiveWordsFiveButton, fiveWordsFourButton, fiveWordsOneButton, fiveWordsThreeButton, fiveWordsTwoButton;
    @FXML private ToggleButton fourWordsFourButton, fourWordsOneButton, fourWordsThreeButton, fourWordsTwoButton;
    @FXML private ToggleButton threeWordsOneButton, threeWordsThreeButton, threeWordsTwoButton;
    @FXML private ToggleButton twoWordsOneButton, twoWordsTwoButton;
    @FXML private Label questionNumberLabel, timeLabel, wordLabel, hintLabel, timeDescriptionLabel;
    @FXML private Pane answerPane;
    @FXML private TextField answerTextField;
    @FXML
    private ImageView hintImageView;

    private Game game;
    private List<Word> answers;


    @FXML
    public void initialize() {
        game = Game.getInstance();
        game.setTimeUpdateListener(this);


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
                answerPane.setVisible(true);
                break;
        }

        if(game.getMode() == Mode.LEARNING){
            timeLabel.setVisible(false);
            questionNumberLabel.setVisible(false);
            timeDescriptionLabel.setVisible(false);
            sortButton.setDisable(false);
        }
        else {
            saveGameButton.setDisable(true);
        }

        answers = game.getAnswers();
        if(answers == null)
            answers = game.nextWord();
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
    void exitButtonClicked(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void hintButtonClicked(ActionEvent event) {
        switch(game.getLanguage()){
            case POL:
                hintLabel.setText(game.getHint());
                break;
            case ENG:
                String url = game.getHint();
                Image image = new Image(url);
                hintImageView.setImage(image);
                break;
        }
    }

    @FXML
    void mainMenuButtonClicked(ActionEvent event) throws IOException {
        SceneSwitchUtil.switchScene("main-menu.fxml", (Stage) mainMenuButton.getScene().getWindow());
    }

    public void nextWord(){
        hintLabel.setText("");
        hintImageView.setImage(null);
        answerTextField.clear();
        answers = game.nextWord();
        if(answers==null) {
            try {
                if(game.getMode() == Mode.TEST)
                    SceneSwitchUtil.switchScene("end-test.fxml", (Stage) timeLabel.getScene().getWindow());
                else
                    SceneSwitchUtil.switchScene("main-menu.fxml", (Stage) timeLabel.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
            setWord(answers.get(answers.size() - 1));

    }


    @FXML
    void nextButtonClicked(ActionEvent event) throws InterruptedException, IOException {


        if(game.getDifficulty() == Difficulty.EXPERT){
            if(answerTextField.getText().trim().isEmpty()){
                Alerts.showAlert("Błąd", "Wpisz odpowiedź");
                return;
            }
            if(game.getMode() == Mode.LEARNING){
                String answer = answerTextField.getText();

                Boolean isCorrect = game.checkAnswer(answer.toLowerCase());
                if(isCorrect){
                    nextWord();
                }
                else{
                    answerTextField.getStyleClass().clear();
                    answerTextField.getStyleClass().add("answer-text-field-wrong");
                    PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
                    pause.setOnFinished(ev -> {
                        answerTextField.getStyleClass().clear();
                        answerTextField.getStyleClass().add("answer-text-field");
                    });
                    pause.play();
                }
            }
            else{
                if(game.checkAnswer(answerTextField.getText().toLowerCase())){
                    game.addTestCorrectQuestion();
                }
                nextWord();
                questionNumberLabel.setText(game.getTestQuestionIndex() + "/" + TestProgramState.NUMBER_OF_WORDS);
            }

        }
        else{
            ToggleButton selectedButton = (ToggleButton) answerGroup.getSelectedToggle();

            if(selectedButton == null){
                Alerts.showAlert("Błąd", "Wybierz odpowiedź");
                return;
            }
            String answer = selectedButton.getText();
            Boolean isCorrect = game.checkAnswer(answer);

            switch(game.getMode()){
                case LEARNING:
                    if (isCorrect) {
                        changeButtonStyle(selectedButton, "answer-button-correct", this::nextWord);
                    } else {
                        changeButtonStyle(selectedButton, "answer-button-wrong", () -> {});
                        // The lambda is empty for the wrong answer as you may not want to load the next word immediately
                    }

                    break;
                case TEST:
                    if(isCorrect){
                        game.addTestCorrectQuestion();
                    }
                    nextWord();
                    questionNumberLabel.setText(game.getTestQuestionIndex() + "/" + TestProgramState.NUMBER_OF_WORDS);
                    break;

            }
        }


    }


    public void changeButtonStyle(ToggleButton selectedButton, String styleClass, Runnable afterStyleChange) {
        selectedButton.getStyleClass().clear();
        selectedButton.getStyleClass().add(styleClass);

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(ev -> {
            selectedButton.getStyleClass().clear();
            selectedButton.getStyleClass().add("answer-button");
            afterStyleChange.run();
        });
        pause.play();
    }


    @FXML
    void saveGameButtonClicked(ActionEvent event) {
        game.saveGame();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Zapisano grę");
        alert.setHeaderText(null);
        alert.setContentText("Gra została zapisana");
        alert.showAndWait();

    }

    @FXML
    void settingsButtonClicked(ActionEvent event) throws IOException {
        SceneSwitchUtil.switchScene("settings.fxml", (Stage) settingsButton.getScene().getWindow());

    }

    @FXML
    void sortButtonClicked(ActionEvent event) throws IOException {
        LearningProgramState programState = (LearningProgramState) game.getProgramState();
        programState.changeWordIterator();
        sortButton.setText(sortButton.getText().equals("Kolejność: losowa") ? "Kolejność: alfabetyczna" : "Kolejność: losowa");
        ((LearningProgramState) game.getProgramState()).getWordIterator().resetPosition();
        nextWord();


    }

    @Override
    public void onTimeUpdate(int secondsLeft) {
        if(secondsLeft != 0){
            Platform.runLater(() -> {
                timeLabel.setText(secondsLeft + " s");
            });
        }
        else{
            Platform.runLater(() -> {
                try {
                    SceneSwitchUtil.switchScene("end-test.fxml", (Stage) timeLabel.getScene().getWindow());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        }


    }
}
