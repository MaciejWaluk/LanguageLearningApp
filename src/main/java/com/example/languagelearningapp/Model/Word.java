package com.example.languagelearningapp.Model;

import java.util.Objects;

public class Word {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String word;
    private String translation;
    private String pronunciation;
    private String language;

    public Word(int id, String word, String translation, String pronunciation, String language) {

        this.id = Objects.requireNonNull(id, "id must not be null");
        this.word = Objects.requireNonNull(word, "word must not be null");
        this.translation = Objects.requireNonNull(translation, "translation must not be null");
        this.pronunciation = Objects.requireNonNull(pronunciation, "pronunciation must not be null");
        this.language = Objects.requireNonNull(language, "language must not be null");
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
