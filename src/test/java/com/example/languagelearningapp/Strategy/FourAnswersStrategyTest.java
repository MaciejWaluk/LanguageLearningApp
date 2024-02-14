package com.example.languagelearningapp.Strategy;

import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.State.LanguageState.EnglishToPolishState;
import com.example.languagelearningapp.State.LanguageState.LanguageState;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FourAnswersStrategyTest {

    static LanguageState languageState;
    FourAnswersStrategy fourAnswersStrategy;
    List<Word> result;
    Word word;

    @BeforeAll
    static void setUpAll() {
        languageState = new EnglishToPolishState();
    }

    @BeforeEach
    void setUp() {
        word = new Word(367, "cat", "kot", "English", null, "https://news.artnet.com/app/news-upload/2019/01/Cat-Photog-Feat-256x256.jpg");
        fourAnswersStrategy = new FourAnswersStrategy();
        result = fourAnswersStrategy.createAnswers(word, languageState);
    }

    @Test
    void shouldIncludeCorrectWord() {
        assertTrue(result.contains(word));
    }

    @Test
    void shouldHaveCorrectAmountOfAnswers() {
        assertEquals(4, result.size());
    }
}