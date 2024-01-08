package com.example.languagelearningapp.Builder;

import com.example.languagelearningapp.Model.Word;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public interface WordBuilder {

    public WordBuilder setId(int id);
    public WordBuilder setWord(String word);
    public WordBuilder setTranslation(String translation);
    public WordBuilder setLanguage(String language);
    public WordBuilder setPronunciation(String pronunciation);
    public WordBuilder setImage(BufferedImage image);
    public Word build();
}
