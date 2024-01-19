package com.example.languagelearningapp.Model;

import java.awt.Image;
import lombok.*;

import java.awt.image.BufferedImage;
import java.util.Objects;

@Getter @Setter @RequiredArgsConstructor @AllArgsConstructor @ToString
public class Word {

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



}
