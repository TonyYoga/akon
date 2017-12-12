package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamycList<E> implements Iterable<E>{
    Object[] container;

    public DynamycList() {
        this.container = new Object[10];
    }

    /*
    Search and fill next null-element at the container.
    If container is full - make it bigger: +10 elements;
     */
    boolean add(E value){
        for (int index = 0; index < container.length; index++) {
            if (container[index] == null) {
                container[index] = value;
                return true;
            }
        }
        Object[] tmpcontainer = container;
        container = new Object[container.length + 10];
        for (int index = 0; index < tmpcontainer.length; index++) {
            container[index] = tmpcontainer[index];
        }
        container[tmpcontainer.length] = value;
        return true;
    }

    E get(int index) {
        E value;
        if (index < container.length && container[index] != null){
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
                return index<container.length && container[index] != null;
            }

            @Override
            public E next() {
                if(hasNext()) {
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
