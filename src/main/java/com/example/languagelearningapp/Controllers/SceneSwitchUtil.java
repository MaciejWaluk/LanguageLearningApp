package com.example.languagelearningapp.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;


public final class SceneSwitchUtil {

    public static final double WIDTH = 600;
    public static final double HEIGHT = 400;

    public static void switchScene(String sceneName, Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(SceneSwitchUtil.class.getResource("/com/example/languagelearningapp/" + sceneName));
        Scene scene = new Scene(loader.load(), 600, 400);
        stage.setScene(scene);
        stage.show();
    }


}
