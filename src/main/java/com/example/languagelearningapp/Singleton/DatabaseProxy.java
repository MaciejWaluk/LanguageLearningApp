package com.example.languagelearningapp.Singleton;

import com.example.languagelearningapp.Builder.WordDirector;
import com.example.languagelearningapp.Game.*;
import com.example.languagelearningapp.Model.Word;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseProxy {

    private DatabaseConnection databaseConnection = null;

    private void initializeConnection() {
        if(this.databaseConnection == null) {
            this.databaseConnection = DatabaseConnection.getInstance();
        }
    }

    public List<Word> getAllWords(){
        initializeConnection();
        Statement stmt = null;
        List<Word> words = new ArrayList<>();
        ResultSet rs = null;
        try {
            stmt = this.databaseConnection.getConn().createStatement();
            String sql = "SELECT * FROM words";
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        WordDirector wordDirector = new WordDirector();

        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String word = rs.getString("word");
                String translation = rs.getString("translation");
                String language = rs.getString("language");
                String pronunciation = rs.getString("pronunciation");
                String imageUrl = rs.getString("imageUrl");
                words.add(new Word(id, word, translation, language, pronunciation, imageUrl));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return words;
    }

    public List<Word> getWordsByLanguage(String language) {
        initializeConnection();
        Statement stmt = null;
        List<Word> words = new ArrayList<>();
        ResultSet rs = null;
        try {
            stmt = this.databaseConnection.getConn().createStatement();
            String sql = "SELECT * FROM words WHERE language = '" + language + "'";
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        WordDirector wordDirector = new WordDirector();

        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String word = rs.getString("word");
                String translation = rs.getString("translation");
                if(language.equals("English"))
                    words.add(wordDirector.constructWordWithPicture(id, word, translation, language, rs.getString("imageUrl")));
                else
                    words.add(wordDirector.constructWordWithPronunciation(id, word, translation, language, rs.getString("pronunciation")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return words;
    }

    public void saveAllWords(List<Word> words){
        initializeConnection();
        Statement stmt = null;
        try {
            stmt = this.databaseConnection.getConn().createStatement();
            String sql = "DELETE FROM words";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for(Word word : words){
            try {
                stmt = this.databaseConnection.getConn().createStatement();
                String sql = "INSERT INTO words (word, translation, language, pronunciation, imageUrl) VALUES ('" + word.getWord() + "', '" + word.getTranslation() + "', '" + word.getLanguage() + "', '" + word.getPronunciation() + "', '" + word.getImageUrl() + "')";
                stmt.executeUpdate(sql);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }





}
