package ru.job4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {


    public Converter() {
    }

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {


        return new Iterator<Integer>() {
            private Iterator<Integer> current = it.next();

            @Override
            public boolean hasNext() {

                return current.hasNext() || it.hasNext();
            }

            @Override
            public Integer next() {

                if (current.hasNext()) {
                    return current.next();
                } else if (!current.hasNext() && it.hasNext()) {
                    current = it.next();
                    return current.next();
                } else {
                    throw new NoSuchElementException();
                }

            }
        };
    }
}