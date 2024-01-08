package com.example.languagelearningapp.Game;

import com.almasb.fxgl.localization.Language;
import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.Singleton.DatabaseProxy;
import com.example.languagelearningapp.State.LanguageState;

import java.util.List;

public class Game {

    private DatabaseProxy databaseProxy;
    private List<Word> wordList;
    private Difficulty difficulty;
    private LanguageState languageState;
}
