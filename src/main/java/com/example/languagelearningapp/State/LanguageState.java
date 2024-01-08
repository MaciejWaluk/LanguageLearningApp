package com.example.languagelearningapp.State;

import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.Singleton.DatabaseProxy;

import java.util.List;

public interface LanguageState {

    public String getLanguage();
    public List<Word> getWordsList(DatabaseProxy databaseProxy);
}
