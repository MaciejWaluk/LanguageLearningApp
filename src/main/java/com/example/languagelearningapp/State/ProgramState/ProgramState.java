package com.example.languagelearningapp.State.ProgramState;

import com.example.languagelearningapp.Iterator.WordIterator;
import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.Singleton.DatabaseProxy;

import java.util.List;

public interface ProgramState {

    public Word getNextWord();
    public void getResults();

}
