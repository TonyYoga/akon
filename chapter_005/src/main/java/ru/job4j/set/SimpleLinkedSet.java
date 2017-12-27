package ru.job4j.set;

import ru.job4j.list.ContainerLinkedList;
import java.util.Iterator;

public class SimpleLinkedSet<E> extends ContainerLinkedList<E> {

    public boolean add(E e) {
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (it.next().equals(e)) {
                return false;
            }
        }
        return super.add(e);
    }

}
