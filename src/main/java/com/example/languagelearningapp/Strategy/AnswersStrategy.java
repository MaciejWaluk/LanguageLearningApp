package com.example.languagelearningapp.Strategy;

import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.State.LanguageState;

import java.util.List;

public interface AnswersStrategy {

    public List<Word> createAnswers(Word word, LanguageState languageState);

}
