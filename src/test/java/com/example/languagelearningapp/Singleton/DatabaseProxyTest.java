package com.example.languagelearningapp.Singleton;

import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.State.LanguageState.EnglishToPolishState;
import com.example.languagelearningapp.State.LanguageState.LanguageState;
import com.example.languagelearningapp.State.LanguageState.PolishToEnglishState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseProxyTest {

    @Test
    void databaseObjectShouldNotBeNull() {
        DatabaseProxy databaseProxy = new DatabaseProxy();
        assertNotNull(databaseProxy);
    }

    @ParameterizedTest
    @MethodSource("languageStateProvider")
    void wordListCannotBeNull(LanguageState languageState) {
        DatabaseProxy databaseProxy = new DatabaseProxy();
        assertNotNull(databaseProxy.getWordsByLanguage(languageState.getLanguage()));
    }

    @ParameterizedTest
    @MethodSource("languageStateProvider")
    void wordListShouldNotIncludeWordsOfOtherLanguage(LanguageState languageState) {
        DatabaseProxy databaseProxy = new DatabaseProxy();

        List<Word> words = databaseProxy.getWordsByLanguage(languageState.getLanguage());

        boolean result = words
                .stream()
                .anyMatch(w -> !w.getLanguage().equals(languageState.getLanguage()));

        assertFalse(result);
    }

    static Iterable<LanguageState> languageStateProvider() {
        return Arrays.asList(new EnglishToPolishState(), new PolishToEnglishState());
    }

}