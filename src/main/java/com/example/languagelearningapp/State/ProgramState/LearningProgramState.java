package com.example.languagelearningapp.State.ProgramState;

import com.example.languagelearningapp.Iterator.AlphabeticalWordIterator;
import com.example.languagelearningapp.Iterator.RandomWordIterator;
import com.example.languagelearningapp.Iterator.WordIterator;
import com.example.languagelearningapp.Memento.ListCopyUtil;
import com.example.languagelearningapp.Model.Word;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class LearningProgramState implements ProgramState{

    private WordIterator wordIterator;
    private List<Word> wordsList;

    public LearningProgramState(List<Word> wordsList) {
        this.wordsList = wordsList;
        wordIterator = new RandomWordIterator(wordsList);
    }

    public LearningProgramState(List<Word> wordsList, WordIterator wordIterator) {
        this.wordsList = ListCopyUtil.copyList(wordsList);
        this.wordIterator = wordIterator;
    }

    public void changeWordIterator() {
        wordIterator = wordIterator instanceof RandomWordIterator ? new AlphabeticalWordIterator(wordsList) : new RandomWordIterator(wordsList);
    }

    @Override
    public Word getNextWord() {
        return wordIterator.next();
    }

    @Override
    public void getResults() {

    }

}
