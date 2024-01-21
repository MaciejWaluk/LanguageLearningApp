package com.example.languagelearningapp.Controllers;

import com.example.languagelearningapp.Game.Game;
import com.example.languagelearningapp.Model.Word;
import com.example.languagelearningapp.Singleton.DatabaseProxy;
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

public class SettingsController {

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

    DatabaseProxy databaseProxy;
    private ObservableList<Word> wordList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
        translationColumn.setCellValueFactory(new PropertyValueFactory<>("translation"));
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        pronunciationColumn.setCellValueFactory(new PropertyValueFactory<>("pronunciation"));
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));

        databaseProxy = new DatabaseProxy();
        wordList.addAll(databaseProxy.getAllWords());

        tableView.setItems(wordList);

        languageChoiceBox.getItems().addAll("Angielski", "Polski");

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                wordTextField.setText(newSelection.getWord());
                translationTextField.setText(newSelection.getTranslation());
                pronunciationTextField.setText(newSelection.getPronunciation());
                languageChoiceBox.setValue(newSelection.getLanguage().equals("English") ? "Angielski" : "Polski");
            }
        });
    }


    @FXML
    void exitButtonClicked(ActionEvent event) {

    }

    @FXML
    void mainMenuButtonClicked(ActionEvent event) throws IOException {
        SceneSwitchUtil.switchScene("main-menu.fxml", (Stage) mainMenuButton.getScene().getWindow());

    }



    @FXML
    void addWordButtonClicked(ActionEvent event) {
        String word = wordTextField.getText().toLowerCase();
        String translation = translationTextField.getText().toLowerCase();
        String language = languageChoiceBox.getValue().equals("Angielski") ? "English" : "Polish";
        String pronunciation = pronunciationTextField.getText().toLowerCase();

        if(word.isEmpty() || translation.isEmpty() || language.isEmpty() || pronunciation.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Niepoprawne dane");
            alert.setContentText("Wszystkie pola muszą być wypełnione");
            alert.showAndWait();
        } else {
            Word newWord = new Word(0, word, translation, language, pronunciation, "url");
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Nie wybrano słowa");
            alert.setContentText("Musisz wybrać słowo, które chcesz usunąć");
            alert.showAndWait();
        }

    }

    @FXML
    void saveWordsButtonClicked(ActionEvent event) {
        databaseProxy.saveAllWords(wordList);
    }

    @FXML
    void updateWordButtonClicked(ActionEvent event) {
        Word selectedWord = tableView.getSelectionModel().getSelectedItem();
        if(selectedWord != null) {
            String word = wordTextField.getText().toLowerCase();
            String translation = translationTextField.getText().toLowerCase();
            String language = languageChoiceBox.getValue().equals("Angielski") ? "English" : "Polish";
            String pronunciation = pronunciationTextField.getText().toLowerCase();

            if(word.isEmpty() || translation.isEmpty() || language.isEmpty() || pronunciation.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Niepoprawne dane");
                alert.setContentText("Wszystkie pola muszą być wypełnione");
                alert.showAndWait();
            } else {
                selectedWord.setWord(word);
                selectedWord.setTranslation(translation);
                selectedWord.setLanguage(language);
                selectedWord.setPronunciation(pronunciation);
                wordTextField.clear();
                translationTextField.clear();
                pronunciationTextField.clear();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Nie wybrano słowa");
            alert.setContentText("Musisz wybrać słowo, które chcesz zaktualizować");
            alert.showAndWait();
        }

    }

}
