package com.example.languagelearningapp.Builder;

import com.example.languagelearningapp.Model.Word;

import java.awt.image.BufferedImage;

public class PronunciationWordBuilder implements WordBuilder{

    private int id;
    private String word;
    private String translation;
    private String language;
    private String pronunciation;
    @Override
    public WordBuilder setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public WordBuilder setWord(String word) {
        this.word = word;
        return this;
    }

    @Override
    public WordBuilder setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    @Override
    public WordBuilder setLanguage(String language) {
        this.language = language;
        return this;
    }

    public WordBuilder setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
        return this;
    }

    @Override
    public WordBuilder setImage(BufferedImage image) {
        return this;
    }

    @Override
    public Word build() {
        Word word = new Word(this.id, this.word, this.translation, this.language);
        word.setPronunciation(this.pronunciation);
        return word;
    }
}
