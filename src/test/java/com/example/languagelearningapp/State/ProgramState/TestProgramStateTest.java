package com.example.languagelearningapp.State.ProgramState;

import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.Observer.TimeObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestProgramStateTest {

    List<Word> words;

    @BeforeEach
    void setUp() {
        words = Arrays.asList(
                new Word(1, "banana", "banan", "pl", "banana.jpg", "banana.mp3"),
                new Word(2, "apple", "jabłko", "pl", "apple.jpg", "apple.mp3"),
                new Word(3, "cherry", "wiśnia", "pl", "cherry.jpg", "cherry.mp3")
        );
    }

    @Test
    void shouldIncreaseCorrectAnswersCount() {
        TestProgramState testProgramState = new TestProgramState(new TimeObserver(words.size()), words);

        int correctAnswersCount = testProgramState.getCorrectQuestionsCount();
        testProgramState.addCorrectQuestion();

        assertEquals(correctAnswersCount + 1, testProgramState.getCorrectQuestionsCount());
    }


}