package com.example.languagelearningapp.Game;

import com.example.languagelearningapp.Controllers.SceneSwitchUtil;
import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.Observer.TimeObserver;
import com.example.languagelearningapp.Singleton.DatabaseProxy;
import com.example.languagelearningapp.State.LanguageState.EnglishToPolishState;
import com.example.languagelearningapp.State.LanguageState.LanguageState;
import com.example.languagelearningapp.State.LanguageState.PolishToEnglishState;
import com.example.languagelearningapp.State.ProgramState.LearningProgramState;
import com.example.languagelearningapp.State.ProgramState.ProgramState;
import com.example.languagelearningapp.State.ProgramState.TestProgramState;
import com.example.languagelearningapp.Strategy.*;
import javafx.stage.Stage;
import lombok.Data;
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
    private Language language;
    private AnswersStrategy answersStrategy;
    private LanguageState languageState;
    private ProgramState programState;
    private Stage stage;

    public static Game instance;


    private Game() {
        difficulty = Difficulty.BEGINNER;
        mode = Mode.LEARNING;
        language = Language.POL;

        databaseProxy = new DatabaseProxy();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
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

        switch(language){
            case POL:
                languageState = new PolishToEnglishState();
                break;
            case ENG:
                languageState = new EnglishToPolishState();
                break;
        }

        wordsList = languageState.getWordsList(databaseProxy);

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



    }

    public List<Word> nextWord(){
        Word word = programState.getNextWord();
        currentWordIndex = wordsList.indexOf(word);
        List<Word> answers = answersStrategy.createAnswers(word, languageState);;
        answers.add(word);
        return answers;
    }


    public boolean checkAnswer(String answer){
        return wordsList.get(currentWordIndex).getTranslation().equals(answer);
    }

    //TODO: Think about how store answers list

    public void endGame(){
        //TODO: Implement ending game, saving and displaying result by passing to the view
    }



}
