package com.example.languagelearningapp.Strategy;

import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.State.LanguageState;

import java.util.ArrayList;
import java.util.List;

public class TextAnswersStrategy implements AnswersStrategy{
    @Override
    public List<Word> createAnswers(Word word, LanguageState languageState) {
        return new ArrayList<>();
    }
}
