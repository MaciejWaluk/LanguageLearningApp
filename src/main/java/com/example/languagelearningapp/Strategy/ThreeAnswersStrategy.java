package com.example.languagelearningapp.Strategy;

import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.Singleton.DatabaseProxy;
import com.example.languagelearningapp.State.LanguageState.LanguageState;

import java.util.Collections;
import java.util.List;

public class ThreeAnswersStrategy implements AnswersStrategy {
    @Override
    public List<Word> createAnswers(Word word, LanguageState languageState) {
        DatabaseProxy databaseProxy = new DatabaseProxy();
        List<Word> words = languageState.getWordsList(databaseProxy);
        words.removeIf(w -> w.getWord().equals(word.getWord()));
        Collections.shuffle(words);

        List<Word> result = words.subList(0, 2);
        result.add(word);
        Collections.shuffle(result);
        return result;

    }
}
