package com.example.languagelearningapp.Memento;

import com.example.languagelearningapp.Game.Difficulty;
import com.example.languagelearningapp.Game.Game;
import com.example.languagelearningapp.Game.Language;
import com.example.languagelearningapp.Game.Mode;
import com.example.languagelearningapp.Iterator.WordIterator;
import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.State.LanguageState.LanguageState;
import com.example.languagelearningapp.State.ProgramState.LearningProgramState;
import com.example.languagelearningapp.State.ProgramState.ProgramState;
import com.example.languagelearningapp.Strategy.AnswersStrategy;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter @ToString
public class GameMemento {
    private final List<Word> wordsList;
    private final int currentWordIndex;
    private final Difficulty difficulty;
    private final Mode mode;
    private final Language language;
    private final AnswersStrategy answersStrategy;
    private final LanguageState languageState;
    private final ProgramState programState;
    private final List<Word> answers;

    public GameMemento(Game game) {
        this.wordsList = ListCopyUtil.copyList(game.getWordsList()); // Deep copy if necessary
        this.currentWordIndex = game.getCurrentWordIndex();
        this.difficulty = game.getDifficulty();
        this.mode = game.getMode();
        this.language = game.getLanguage();
        this.answersStrategy = game.getAnswersStrategy(); // Clone or deep copy if mutable
        this.languageState = game.getLanguageState(); // Clone or deep copy if mutable
        this.answers = ListCopyUtil.copyList(game.getAnswers());

        int iteratorIndex = ((LearningProgramState) game.getProgramState()).getWordIterator().getPosition();
        WordIterator wordIterator = ((LearningProgramState) game.getProgramState()).getWordIterator().clone(wordsList, iteratorIndex);
        this.programState = new LearningProgramState(wordsList, wordIterator);
    }

}
