package com.example.languagelearningapp.Memento;

import com.example.languagelearningapp.Game.Game;


public class GameCaretaker {
    private GameMemento gameMemento;

    public void saveState(Game game) {
        gameMemento = new GameMemento(game);
    }

    public GameMemento restoreState() {
        return gameMemento;
    }


}
