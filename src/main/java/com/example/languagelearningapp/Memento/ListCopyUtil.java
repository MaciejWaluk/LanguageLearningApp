package com.example.languagelearningapp.Memento;

import com.example.languagelearningapp.Model.Word;

import java.util.ArrayList;
import java.util.List;

public class ListCopyUtil {

    public static List<Word> copyList(List<Word> list) {
        List<Word> copy = new ArrayList<>();
        for (Word word : list) {
            copy.add(word.clone());
        }
        return copy;
    }
}
