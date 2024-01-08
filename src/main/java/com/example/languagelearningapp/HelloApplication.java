package com.example.languagelearningapp;

import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.Singleton.DatabaseConnection;
import com.example.languagelearningapp.Singleton.DatabaseProxy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        DatabaseProxy db = new DatabaseProxy();
        List<Word> words = db.getWords();
        for(Word word : words) {
            System.out.println(word);
        }


    }

    public static void main(String[] args) {
        launch();
    }
}