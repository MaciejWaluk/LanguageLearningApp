package com.example.languagelearningapp.Memento;

import com.example.languagelearningapp.Game.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameCaretaker {
    private final Stack<GameMemento> mementos = new Stack<>();

    public void saveState(Game game) {
//        mementos.add(new GameMemento(game));
        mementos.push(new GameMemento(game));
    }

    public GameMemento restoreState() {
        return mementos.pop();
    }


}
