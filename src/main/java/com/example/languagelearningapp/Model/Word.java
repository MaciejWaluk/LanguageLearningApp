package com.example.languagelearningapp.Model;

import java.awt.Image;
import lombok.*;

import java.awt.image.BufferedImage;
import java.util.Objects;

@Getter @Setter @RequiredArgsConstructor @AllArgsConstructor @ToString
public class Word implements Cloneable{

    @NonNull
    private int id;
    @NonNull
    private String word;
    @NonNull
    private String translation;
    @NonNull
    private String language;
    private String pronunciation;
    private String imageUrl;

    // Deep Copy Constructor
    public Word(Word other) {
        this.id = other.id;
        this.word = other.word;
        this.translation = other.translation;
        this.language = other.language;
        this.pronunciation = other.pronunciation;
        this.imageUrl = other.imageUrl;
    }


    @Override
    public Word clone() {
        try {
            return (Word) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Word(this);
        }
    }
}
