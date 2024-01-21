package com.example.languagelearningapp.Observer;

import com.example.languagelearningapp.Game.Game;
import lombok.Getter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TimeObserver implements ActionListener {

    private List<Game> observers;
    private int secondsLeft;

    public TimeObserver(int wordsAmount) {
        observers = new ArrayList<>();
        secondsLeft = 10 * wordsAmount;
    }

    public void addObserver(Game observer) {
        observers.add(observer);
    }

    public void removeObserver(Game observer) {
        observers.remove(observer);
    }

    public void notifyObservers() throws IOException {
        for(Game observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        secondsLeft--;
        try {
            notifyObservers();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
