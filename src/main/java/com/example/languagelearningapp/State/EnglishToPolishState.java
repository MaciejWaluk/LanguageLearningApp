package com.example.languagelearningapp.State;

import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.Singleton.DatabaseProxy;

import java.util.List;

public class EnglishToPolishState implements LanguageState{

    @Override
    public String getLanguage() {
        return "english";
    }

    @Override
    public List<Word> getWordsList(DatabaseProxy databaseProxy) {
        return databaseProxy.getWordsByLanguage("english");
    }
}
