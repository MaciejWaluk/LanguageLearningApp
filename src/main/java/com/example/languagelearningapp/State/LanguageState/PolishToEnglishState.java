package com.example.languagelearningapp.State.LanguageState;

import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.Singleton.DatabaseProxy;

import java.util.List;

public class PolishToEnglishState implements LanguageState{
    @Override
    public String getLanguage() {
        return "polish";
    }

    @Override
    public List<Word> getWordsList(DatabaseProxy databaseProxy) {
        return databaseProxy.getWordsByLanguage("polish");
    }
}
