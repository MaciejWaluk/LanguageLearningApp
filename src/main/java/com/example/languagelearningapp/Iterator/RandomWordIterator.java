package com.example.languagelearningapp.Iterator;

import com.example.languagelearningapp.Model.Word;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter @Setter
public class RandomWordIterator implements WordIterator{

    private int position;
    private List<Word> words;

    public RandomWordIterator(List<Word> words) {
        position = 0;
        this.words = words;
        Collections.shuffle(this.words);
    }

    public RandomWordIterator() {
        position = 0;
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

    @Override
    public void resetPosition() {
        position = 0;
    }

    @Override
    public WordIterator clone(List<Word> wordsList, int position) {
        RandomWordIterator newIterator = new RandomWordIterator();
        newIterator.setWords(wordsList);
        newIterator.setPosition(position);
        return newIterator;
    }

}
