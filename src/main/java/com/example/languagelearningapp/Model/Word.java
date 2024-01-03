package com.example.languagelearningapp.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter @AllArgsConstructor
public class Word {

    @NonNull
    private int id;
    @NonNull
    private String word;
    @NonNull
    private String translation;
    @NonNull
    private String pronunciation;
    @NonNull
    private String language;


}
