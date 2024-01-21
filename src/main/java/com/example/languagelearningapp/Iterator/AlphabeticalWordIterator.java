package com.example.languagelearningapp.Iterator;

import com.example.languagelearningapp.Model.Word;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Getter @Setter
public class AlphabeticalWordIterator implements WordIterator{

    private int position;
    private List<Word> words;

    public AlphabeticalWordIterator(List<Word> words) {
        position = 0;
        this.words = words;
        Collections.sort(this.words, Comparator.comparing(Word::getWord));
    }

    public AlphabeticalWordIterator() {
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
    public WordIterator clone(List<Word> wordsList, int position) {
        AlphabeticalWordIterator newIterator = new AlphabeticalWordIterator();
        newIterator.setWords(wordsList);
        newIterator.setPosition(position);
        return newIterator;
    }

}
