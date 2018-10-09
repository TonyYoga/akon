package ru.job4j;

import java.util.HashMap;
import java.util.Map;


public class CharSetEquals {
    private String word;

    public CharSetEquals(String word) {
        this.word = word;
    }

    public HashMap<Character, Integer> setBaseWord(String firstWord) {
        HashMap<Character, Integer> baseWord = new HashMap<>();
        ;
        for (int i = 0; i < firstWord.length(); i++) {
            if (baseWord.containsKey(firstWord.charAt(i))) {
                baseWord.put(firstWord.charAt(i), baseWord.get(firstWord.charAt(i)) + 1);
            } else {
                baseWord.put(firstWord.charAt(i), 1);
            }
        }
        return baseWord;
    }

    @Override
    public boolean equals(Object o) {
        if (word == o) return true;
        if (o == null) return false;
        String that = (String) o;
        HashMap<Character, Integer> wordForTest = setBaseWord(that);
        ;
        for (int i = 0; i < word.length(); i++) {
            if (!wordForTest.containsKey(word.charAt(i))) {
                return false;
            } else {
                wordForTest.put(word.charAt(i), wordForTest.get(word.charAt(i)) - 1);
            }
        }
        return mapHaveZeroValues(wordForTest);
    }

    private boolean mapHaveZeroValues(HashMap<Character, Integer> word) {
        for (Map.Entry<Character, Integer> pair : word.entrySet()) {
            if (pair.getValue() != 0) {
                return false;
            }
        }
        return true;

    }


}
