package com.example.languagelearningapp.Controllers;

import com.example.languagelearningapp.Builder.WordDirector;
import com.example.languagelearningapp.Game.Game;
import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.Singleton.DatabaseProxy;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Data;

import java.io.IOException;

public class SettingsController implements Alerts{

    @FXML
    private Button exitButton, mainMenuButton, addWordButton, deleteWordButton, saveWordsButton, updateWordButton;

    @FXML
    private TableView<Word> tableView;

    @FXML
    private TableColumn<Word, String> wordColumn, translationColumn, languageColumn, pronunciationColumn, urlColumn;

    @FXML
    private ChoiceBox<String> languageChoiceBox;

    @FXML
    private TextField pronunciationTextField, translationTextField, wordTextField;

    @FXML
    private Label hintLabel;

    DatabaseProxy databaseProxy;
    WordDirector wordDirector;
    private ObservableList<Word> wordList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
        translationColumn.setCellValueFactory(new PropertyValueFactory<>("translation"));
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        pronunciationColumn.setCellValueFactory(new PropertyValueFactory<>("pronunciation"));
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));

        wordDirector = new WordDirector();

        databaseProxy = new DatabaseProxy();
        wordList.addAll(databaseProxy.getAllWords());

        tableView.setItems(wordList);

        languageChoiceBox.getItems().addAll("Angielski", "Polski");

        languageChoiceBox.setOnAction((event) -> {
            if(languageChoiceBox.getValue().equals("Angielski")) {
                hintLabel.setText("Link do obrazka:");
            } else {
                hintLabel.setText("Wymowa:");
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                wordTextField.setText(newSelection.getWord());
                translationTextField.setText(newSelection.getTranslation());
                languageChoiceBox.setValue(newSelection.getLanguage().equals("English") ? "Angielski" : "Polski");
                if(newSelection.getLanguage().equals("English")) {
                    hintLabel.setText("Link do obrazka:");
                    pronunciationTextField.setText(newSelection.getImageUrl());
                } else {
                    hintLabel.setText("Wymowa:");
                    pronunciationTextField.setText(newSelection.getPronunciation());

                }
            }
        });
    }


    @FXML
    void exitButtonClicked(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void mainMenuButtonClicked(ActionEvent event) throws IOException {
        SceneSwitchUtil.switchScene("main-menu.fxml", (Stage) mainMenuButton.getScene().getWindow());

    }



    @FXML
    void addWordButtonClicked(ActionEvent event) {
        String word = wordTextField.getText().toLowerCase();
        String translation = translationTextField.getText().toLowerCase();
        String language = languageChoiceBox.getValue();
        String hint = pronunciationTextField.getText().toLowerCase();

        if(word.isEmpty() || translation.isEmpty() || language.isEmpty() || hint.isEmpty() || languageChoiceBox.getValue() == null) {
            Alerts.showAlert("Błąd", "Wszystkie pola muszą być wypełnione");
        } else {
            language = language.equals("Angielski") ? "English" : "Polish";
            Word newWord = language.equals("English") ? wordDirector.constructWordWithPicture(0, word, translation, language, hint) :
                    wordDirector.constructWordWithPronunciation(0, word, translation, language, hint);
            wordList.add(newWord);
            wordTextField.clear();
            translationTextField.clear();
            pronunciationTextField.clear();
        }

    }

    @FXML
    void deleteWordButtonClicked(ActionEvent event) {
        Word selectedWord = tableView.getSelectionModel().getSelectedItem();
        if(selectedWord != null) {
            wordList.remove(selectedWord);
        } else {
            Alerts.showAlert("Błąd", "Musisz wybrać słowo, które chcesz usunąć");
        }

    }

    @FXML
    void saveWordsButtonClicked(ActionEvent event) {
        databaseProxy.saveAllWords(wordList);
        Alerts.showAlert("Zapisano słowa", "Słowa zostały zapisane w bazie danych");
    }



    @FXML
    void updateWordButtonClicked(ActionEvent event) {
        Word selectedWord = tableView.getSelectionModel().getSelectedItem();
        if(selectedWord != null) {
            String word = wordTextField.getText().toLowerCase();
            String translation = translationTextField.getText().toLowerCase();
            String language = languageChoiceBox.getValue().equals("Angielski") ? "English" : "Polish";
            String hint = pronunciationTextField.getText().toLowerCase();

            if(word.isEmpty() || translation.isEmpty() || language.isEmpty() || hint.isEmpty() || languageChoiceBox.getValue() == null) {
                Alerts.showAlert("Błąd", "Wszystkie pola muszą być wypełnione");
            } else {
                selectedWord.setWord(word);
                selectedWord.setTranslation(translation);
                selectedWord.setLanguage(language);
                if(language.equals("English")) {
                    selectedWord.setImageUrl(hint);
                    selectedWord.setPronunciation("null");
                } else {
                    selectedWord.setPronunciation(hint);
                    selectedWord.setImageUrl("null");
                }
                wordTextField.clear();
                translationTextField.clear();
                pronunciationTextField.clear();
            }
        } else {
            Alerts.showAlert("Błąd", "Musisz wybrać słowo, które chcesz zaktualizować");
        }

    }

}
