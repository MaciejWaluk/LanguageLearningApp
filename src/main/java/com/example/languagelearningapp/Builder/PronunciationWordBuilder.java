package com.example.languagelearningapp.Builder;

import com.example.languagelearningapp.Model.Word;

import java.awt.image.BufferedImage;

public class PronunciationWordBuilder implements WordBuilder{

    private int id;
    private String word;
    private String translation;
    private String language;
    private String hint;
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

    @Override
    public WordBuilder setHint(String hint) {
        this.hint = hint;
        return this;
    }


    @Override
    public Word build() {
        Word word = new Word(this.id, this.word, this.translation, this.language, this.hint, null);
        return word;
    }
}
