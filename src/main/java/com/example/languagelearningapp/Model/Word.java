package com.example.languagelearningapp.Model;

import java.awt.Image;
import lombok.*;

import java.awt.image.BufferedImage;
import java.util.Objects;

@Getter @Setter @RequiredArgsConstructor @ToString
public class Word {

    @NonNull
    private int id;
    @NonNull
    private String word;
    @NonNull
    private String translation;
    @NonNull
    private String language;

    private String pronunciation = null;
    private BufferedImage image = null;


}
