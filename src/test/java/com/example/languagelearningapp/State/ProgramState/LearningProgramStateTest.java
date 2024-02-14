package com.example.languagelearningapp.State.ProgramState;

import com.example.languagelearningapp.Iterator.AlphabeticalWordIterator;
import com.example.languagelearningapp.Iterator.RandomWordIterator;
import com.example.languagelearningapp.Model.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LearningProgramStateTest {

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
    void shouldChangeIterator() {
        LearningProgramState learningProgramState = new LearningProgramState(words);

        learningProgramState.changeWordIterator();

        assertInstanceOf(AlphabeticalWordIterator.class, learningProgramState.getWordIterator());

        learningProgramState.changeWordIterator();

        assertInstanceOf(RandomWordIterator.class, learningProgramState.getWordIterator());
    }


}