package ru.job4j;

import java.util.HashMap;
import java.util.Map;


public class CharSetEquals {
    private String word;

    public CharSetEquals() {

    }
    public CharSetEquals(String word) {
        this.word = word;
    }

    public static HashMap<Character, Integer> setBaseWord(String firstWord) {
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

    private static boolean mapHaveZeroValues(HashMap<Character, Integer> word) {
        for (Map.Entry<Character, Integer> pair : word.entrySet()) {
            if (pair.getValue() != 0) {
                return false;
            }
        }
        return true;

    }

    public static boolean compareAll(String left, String right) {
        if(left == null || right == null) {
            return false;
        }
        HashMap<Character, Integer> wordForTest = setBaseWord(right);
        for (int i = 0; i < left.length(); i++) {
            if (!wordForTest.containsKey(left.charAt(i))) {
                return false;
            } else {
                wordForTest.put(left.charAt(i), wordForTest.get(left.charAt(i)) - 1);
            }
        }
        return mapHaveZeroValues(wordForTest);
    }


}
