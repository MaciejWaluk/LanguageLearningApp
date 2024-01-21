package com.example.languagelearningapp.State.ProgramState;

import com.example.languagelearningapp.Iterator.RandomWordIterator;
import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.Observer.TimeObserver;
import lombok.Getter;

import javax.swing.*;
import java.util.List;

@Getter
public class TestProgramState implements ProgramState {

    public static final int NUMBER_OF_WORDS = 20;
    private int correctQuestions;

    private TimeObserver timeObserver;
    private RandomWordIterator randomWordIterator;
    private List<Word> wordsList;

    public TestProgramState(TimeObserver timeObserver, List<Word> wordsList) {
        this.timeObserver = timeObserver;
        this.wordsList = wordsList;
        randomWordIterator = new RandomWordIterator(wordsList);
        correctQuestions = 0;
    }

    public int getTestQuestionIndex() {
        return randomWordIterator.getPosition();
    }

    public void startTimer() {
        Timer timer = new Timer(1000, timeObserver);
        timer.start();
    }


    @Override
    public Word getNextWord() {
        if(randomWordIterator.getPosition() < NUMBER_OF_WORDS)
            return randomWordIterator.next();
        else return null;
    }

    public void addCorrectQuestion() {
        correctQuestions++;
    }

    @Override
    public void getResults() {

    }
}
