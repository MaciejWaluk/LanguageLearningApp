package com.example.languagelearningapp.Strategy;

import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.Singleton.DatabaseProxy;
import com.example.languagelearningapp.State.LanguageState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwoAnswersStrategy implements AnswersStrategy{
    @Override
    public List<Word> createAnswers(Word word, LanguageState languageState) {
        DatabaseProxy databaseProxy = new DatabaseProxy();
        List<Word> words = languageState.getWordsList(databaseProxy);
        words.remove(word);
        Collections.shuffle(words);

        List<Word> result = new ArrayList<>();
        result.add(word);
        result.add(words.get(0));
        Collections.shuffle(result);
        return result;
    }
}
