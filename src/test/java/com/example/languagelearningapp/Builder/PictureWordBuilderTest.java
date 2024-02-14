package com.example.languagelearningapp.Builder;

import com.example.languagelearningapp.Model.Word;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PictureWordBuilderTest {

    @Test
    void should_build_word_with_picture() {
        PictureWordBuilder pictureWordBuilder = new PictureWordBuilder();
        Word result = pictureWordBuilder
                .setId(1)
                .setWord("apple")
                .setTranslation("jabłko")
                .setLanguage("pl")
                .setHint("apple.jpg")
                .build();

        assertNotNull(result);
        assertNotNull(result.getImageUrl());
        assertNull(result.getPronunciation());


    }
}