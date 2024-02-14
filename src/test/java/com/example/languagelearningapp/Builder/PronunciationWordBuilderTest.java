package com.example.languagelearningapp.Builder;

import com.example.languagelearningapp.Model.Word;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PronunciationWordBuilderTest {

    @Test
    void should_build_word_with_pronunciation() {

        PronunciationWordBuilder pronunciationWordBuilder = new PronunciationWordBuilder();
        Word result = pronunciationWordBuilder
                .setId(1)
                .setWord("apple")
                .setTranslation("jabłko")
                .setLanguage("pl")
                .setHint("apple.jpg")
                .build();

        assertNotNull(result);
        assertNotNull(result.getPronunciation());
        assertNull(result.getImageUrl());
    }
}