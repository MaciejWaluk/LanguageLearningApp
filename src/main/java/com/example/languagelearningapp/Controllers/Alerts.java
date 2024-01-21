package com.example.languagelearningapp.Controllers;

import javafx.scene.control.Alert;

public interface Alerts {
    static void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}
