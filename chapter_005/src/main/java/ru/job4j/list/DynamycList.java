package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamycList<E> implements Iterable<E> {
    private Object[] container;
    private Integer last; // pointed on last element

    public DynamycList() {
        this.container = new Object[10];
        this.last = 0;
    }

    /*
    Search and fill next null-element at the container.
    If container is full - make it bigger: +10 elements;
     */
    public boolean add(E value) {
        if (last >= container.length) {
            container = Arrays.copyOf(container, container.length + 10);
        }
        container[last++] = value;
        return true;
    }

    public E get(int index) {
        if (index < last) {
            return (E) container[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < last;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return (E) container[index++];
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
