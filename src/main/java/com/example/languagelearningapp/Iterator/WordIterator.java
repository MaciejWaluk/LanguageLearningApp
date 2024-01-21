package com.example.languagelearningapp.Iterator;

import com.example.languagelearningapp.Model.Word;

import java.util.List;

public interface WordIterator {
    public boolean hasNext();
    public Word next();
    public int getPosition();
    public WordIterator clone(List<Word> wordsList, int position);
}
