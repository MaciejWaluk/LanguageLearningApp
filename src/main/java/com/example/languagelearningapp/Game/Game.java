package com.example.languagelearningapp.Game;

import com.example.languagelearningapp.Controllers.SceneSwitchUtil;
import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.Observer.TimeObserver;
import com.example.languagelearningapp.Singleton.DatabaseProxy;
import com.example.languagelearningapp.State.LanguageState.LanguageState;
import com.example.languagelearningapp.State.ProgramState.LearningProgramState;
import com.example.languagelearningapp.State.ProgramState.ProgramState;
import com.example.languagelearningapp.State.ProgramState.TestProgramState;
import com.example.languagelearningapp.Strategy.*;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

@Getter @Setter
public class Game {


    private DatabaseProxy databaseProxy;
    private List<Word> wordsList;
    private int currentWordIndex;
    private Difficulty difficulty;
    private Mode mode;
    private AnswersStrategy answersStrategy;
    private LanguageState languageState;
    private ProgramState programState;
    private Stage stage;


    public Game(LanguageState languageState, Stage stage) {
        difficulty = Difficulty.BEGINNER;
        this.languageState = languageState;
        mode = Mode.LEARNING;
        this.stage = stage;
    }

    public void update(TimeObserver timeObserver) throws IOException {
        if(programState instanceof TestProgramState) {
            programState.getResults(); //TODO: implement passing results to the controller
            SceneSwitchUtil.switchScene("end-test.fxml:", stage);

        }
    }

    public void chooseDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;

    }

    public void chooseMode(Mode mode) {
        this.mode = mode;
    }

    public void chooseLanguage(LanguageState languageState) {
        this.languageState = languageState;
    }

    public void switchToTestMode(){
        TimeObserver timeObserver = new TimeObserver(TestProgramState.NUMBER_OF_WORDS);
        timeObserver.addObserver(this);
        programState = new TestProgramState(timeObserver, wordsList);
    }

    public void startGame(){
        wordsList = languageState.getWordsList(databaseProxy);
        switch(difficulty) {
            case BEGINNER:
                answersStrategy = new TwoAnswersStrategy();
                break;
            case EASY:
                answersStrategy = new ThreeAnswersStrategy();
                break;
            case MEDIUM:
                answersStrategy = new FourAnswersStrategy();
                break;
            case HARD:
                answersStrategy = new FiveAnswersStrategy();
                break;
            case EXPERT:
                answersStrategy = new TextAnswersStrategy();
                break;
        }

        switch(mode) {
            case LEARNING:
                programState = new LearningProgramState(wordsList);
                break;
            case TEST:
                switchToTestMode();
                break;
        }

        if(programState instanceof TestProgramState)
            ((TestProgramState) programState).startTimer();

        nextWord();

    }

    public void nextWord(){
        Word word = programState.getNextWord();
        currentWordIndex = wordsList.indexOf(word);
        List<Word> answers;
        if(difficulty != Difficulty.EXPERT)
            answers = answersStrategy.createAnswers(word, languageState);
    }


    public boolean checkAnswer(String answer){
        return wordsList.get(currentWordIndex).getTranslation().equals(answer);
    }

    //TODO: Think about how store answers list

    public void endGame(){
        //TODO: Implement ending game, saving and displaying result by passing to the view
    }



}
