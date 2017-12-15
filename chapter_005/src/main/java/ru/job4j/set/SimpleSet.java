package ru.job4j.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<E> implements Iterable<E> {

    private Object[] set = new Object[10];
    private int index = 0;

    boolean add(E e) {
        if (index == 0) {
            set[index++] = e;
            return true;
        } else {
            for (int i = 0; i < index; i++) {
                if (set[i].equals(e)) {
                    return false;
                }
            }
        }
        if (index >= set.length) {
            set = setGrover(set);
        }
        set[index++] = e;
        return true;
    }

    private Object[] setGrover(Object[] set) {
        Object[] tmp = new Object[set.length * 2];
        for (int i = 0; i < set.length; i++) {
            tmp[i] = set[i];
        }
        return tmp;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < set.length && set[index] != null;
            }


            @Override
            public E next() {
                if (hasNext()) {
                    return (E) set[index++];
                } else {
                    throw new NoSuchElementException();
                }
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();

            }

        };
    }
}

