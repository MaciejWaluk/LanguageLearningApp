package com.example.languagelearningapp.Game;

import com.example.languagelearningapp.Memento.GameCaretaker;
import com.example.languagelearningapp.Memento.GameMemento;
import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.Observer.TimeObserver;
import com.example.languagelearningapp.Observer.TimeUpdateListener;
import com.example.languagelearningapp.Singleton.DatabaseProxy;
import com.example.languagelearningapp.State.LanguageState.EnglishToPolishState;
import com.example.languagelearningapp.State.LanguageState.LanguageState;
import com.example.languagelearningapp.State.LanguageState.PolishToEnglishState;
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
    private Language language;
    private AnswersStrategy answersStrategy;
    private LanguageState languageState;
    private ProgramState programState;
    private Stage stage;
    @Setter
    private TimeUpdateListener timeUpdateListener;
    private GameCaretaker gameCaretaker;
    private List<Word> answers;


    public static Game instance;


    private Game() {
        difficulty = Difficulty.BEGINNER;
        mode = Mode.LEARNING;
        language = Language.POL;
        gameCaretaker = new GameCaretaker();
        databaseProxy = new DatabaseProxy();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void update(TimeObserver timeObserver) throws IOException {
        if(programState instanceof TestProgramState && timeUpdateListener != null) {
                timeUpdateListener.onTimeUpdate(timeObserver.getSecondsLeft());
        }
    }

    public void switchToTestMode(){
        TimeObserver timeObserver = new TimeObserver(TestProgramState.NUMBER_OF_WORDS);
        timeObserver.addObserver(this);
        programState = new TestProgramState(timeObserver, wordsList);
    }

    public void startGame(){
        wordsList = null;
        currentWordIndex = 0;
        answers = null;

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

    public List<Word> nextWord() {
        Word word = programState.getNextWord();
        if (word==null)
            return null;
        else{
            currentWordIndex = wordsList.indexOf(word);
            answers = answersStrategy.createAnswers(word, languageState);
            answers.add(word);
            return answers;
        }
    }

    public int getTestQuestionIndex(){
        if(programState instanceof TestProgramState)
            return ((TestProgramState) programState).getTestQuestionIndex();
        else return 0;
    }

    public void addTestCorrectQuestion(){
        if(programState instanceof TestProgramState)
            ((TestProgramState) programState).addCorrectQuestion();
    }

    public int getCorrectAnswers(){
        if(programState instanceof TestProgramState)
            return ((TestProgramState) programState).getCorrectQuestionsCount();
        else return 0;
    }


    public boolean checkAnswer(String answer){
        return wordsList.get(currentWordIndex).getTranslation().equals(answer);
    }

    public String getHint(){

        switch(language){
            case POL:
                return wordsList.get(currentWordIndex).getPronunciation();
            case ENG:
                return wordsList.get(currentWordIndex).getImageUrl();
            default:
                return "";
        }
    }

    public void saveGame(){
        gameCaretaker.saveState(this);
    }

    public void restoreFromMemento(GameMemento memento) {
        this.wordsList = memento.getWordsList();
        this.currentWordIndex = memento.getCurrentWordIndex();
        this.difficulty = memento.getDifficulty();
        this.mode = memento.getMode();
        this.language = memento.getLanguage();
        this.answersStrategy = memento.getAnswersStrategy();
        this.languageState = memento.getLanguageState();
        this.programState = memento.getProgramState();
        this.answers = memento.getAnswers();
    }





}
