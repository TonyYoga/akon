package ru.job4j.set;

import ru.job4j.list.DynamycList;

import java.util.Iterator;


public class SimpleSet<E> extends DynamycList<E> {

    public boolean add(E e) {
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (it.next().equals(e)) {
                return false;
            }
        }
        super.add(e);
        return true;
    }
}

