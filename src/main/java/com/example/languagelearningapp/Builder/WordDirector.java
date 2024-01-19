package com.example.languagelearningapp.Builder;

import com.example.languagelearningapp.Model.Word;
import lombok.AllArgsConstructor;

import java.awt.image.BufferedImage;

public class WordDirector {

    private WordBuilder pictureWordBuilder;
    private WordBuilder pronunciationWordBuilder;

    public Word constructWordWithPronunciation(int id, String word, String translation, String language, String pronunciation) {
        if(pronunciationWordBuilder == null) {
            pronunciationWordBuilder = new PronunciationWordBuilder();
        }
        return pronunciationWordBuilder.setId(id)
                .setWord(word)
                .setTranslation(translation)
                .setLanguage(language)
                .setHint(pronunciation)
                .build();
    }

    public Word constructWordWithPicture(int id, String word, String translation, String language, String imageUrl) {
        if(pictureWordBuilder == null) {
            pictureWordBuilder = new PictureWordBuilder();
        }
        return pictureWordBuilder.setId(id)
                .setWord(word)
                .setTranslation(translation)
                .setLanguage(language)
                .setHint(imageUrl)
                .build();
    }
}
