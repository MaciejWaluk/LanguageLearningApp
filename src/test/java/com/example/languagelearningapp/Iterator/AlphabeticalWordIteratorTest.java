package com.example.languagelearningapp.Iterator;

import com.example.languagelearningapp.Model.Word;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlphabeticalWordIteratorTest {

    static List<Word> words;

    @BeforeAll
    static void setUp() {
        words = Arrays.asList(
                new Word(1, "banana", "banan", "pl", "banana.jpg", "banana.mp3"),
                new Word(2, "apple", "jabłko", "pl", "apple.jpg", "apple.mp3"),
                new Word(3, "cherry", "wiśnia", "pl", "cherry.jpg", "cherry.mp3")
        );
    }

    @Test
    void should_return_word_on_a() {
        AlphabeticalWordIterator alphabeticalWordIterator = new AlphabeticalWordIterator(words);
        Word result = alphabeticalWordIterator.next();
        assertEquals("apple", result.getWord());
    }

    @Test
    void should_stop_after_last_word() {
        AlphabeticalWordIterator alphabeticalWordIterator = new AlphabeticalWordIterator(words);
        for(int i = 0; i < words.size(); i++) {
            alphabeticalWordIterator.next();
        }
        assertFalse(alphabeticalWordIterator.hasNext());
    }

    @Test
    void should_reset_position() {
        AlphabeticalWordIterator alphabeticalWordIterator = new AlphabeticalWordIterator(words);
        for(int i = 0; i < words.size(); i++) {
            alphabeticalWordIterator.next();
        }
        alphabeticalWordIterator.resetPosition();
        assertEquals(0, alphabeticalWordIterator.getPosition());
    }
}