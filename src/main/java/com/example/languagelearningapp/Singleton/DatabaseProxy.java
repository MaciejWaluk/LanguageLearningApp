package com.example.languagelearningapp.Singleton;

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

    public List<Word> getWords() {
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

        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String word = rs.getString("word");
                String translation = rs.getString("translation");
                String pronunciation = rs.getString("pronunciation");
                String language = rs.getString("language");
                words.add(new Word(id, word, translation, pronunciation, language));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return words;
    }

    public boolean compareWords(String word, String translation) {
        initializeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.databaseConnection.getConn().createStatement();
            String sql = "SELECT * FROM words WHERE word = '" + word + "' AND translation = '" + translation + "'";
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public void addWord(Word word) {
        initializeConnection();
        Statement stmt = null;
        try {
            stmt = this.databaseConnection.getConn().createStatement();
            String sql = "INSERT INTO words (word, translation, pronunciation, language) VALUES ('" + word.getWord() + "', '" + word.getTranslation() + "', '" + word.getPronunciation() + "', '" + word.getLanguage() + "')";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteWord(int id){
        initializeConnection();
        Statement stmt = null;
        try {
            stmt = this.databaseConnection.getConn().createStatement();
            String sql = "DELETE FROM words WHERE id = " + id;
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void editWord(int id, Word word){
        initializeConnection();
        Statement stmt = null;
        try {
            stmt = this.databaseConnection.getConn().createStatement();
            String sql = "UPDATE words SET word = '" + word.getWord() + "', translation = '" + word.getTranslation() + "', pronunciation = '" + word.getPronunciation() + "', language = '" + word.getLanguage() + "' WHERE id = " + id;
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }





}
