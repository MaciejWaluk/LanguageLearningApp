package com.example.languagelearningapp.Iterator;

import com.example.languagelearningapp.Model.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RandomWordIteratorTest {

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
    void should_stop_after_last_word() {
        RandomWordIterator randomWordIterator = new RandomWordIterator(words);
        for(int i = 0; i < words.size(); i++) {
            randomWordIterator.next();
        }
        assertFalse(randomWordIterator.hasNext());
    }

    @Test
    void should_reset_position() {
        RandomWordIterator randomWordIterator = new RandomWordIterator(words);
        for(int i = 0; i < words.size(); i++) {
            randomWordIterator.next();
        }
        randomWordIterator.resetPosition();
        assertEquals(0, randomWordIterator.getPosition());
    }

}