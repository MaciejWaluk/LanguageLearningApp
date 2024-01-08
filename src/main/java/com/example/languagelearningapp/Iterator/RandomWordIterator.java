package com.example.languagelearningapp.Iterator;

import com.example.languagelearningapp.Model.Word;

import java.util.Collections;
import java.util.List;

public class RandomWordIterator implements WordIterator{

    private int position;
    private List<Word> words;

    public RandomWordIterator(List<Word> words) {
        position = 0;
        this.words = words;
        Collections.shuffle(this.words);
    }
    @Override
    public boolean hasNext() {
        return position < words.size();
    }

    @Override
    public Word next() {
        if(this.hasNext()) {
            return words.get(position++);
        }
        else throw new IndexOutOfBoundsException("No more words");
    }
}
