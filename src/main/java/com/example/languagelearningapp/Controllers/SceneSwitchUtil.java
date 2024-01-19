package com.example.languagelearningapp.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;


public final class SceneSwitchUtil {

    public static final double WIDTH = 750;
    public static final double HEIGHT = 500;

    public static void switchScene(String sceneName, Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(SceneSwitchUtil.class.getResource("/com/example/languagelearningapp/" + sceneName));
        Scene scene = new Scene(loader.load(), WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }


}
