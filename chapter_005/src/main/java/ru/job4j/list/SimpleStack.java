package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleStack<E> {

    private ContainerLinkedList<E> cll;

    public SimpleStack() {
        cll = new ContainerLinkedList<>();
    }

    public E poll() {
        if (cll.tail != null) {
            E value = cll.tail.value;
            if (cll.tail.prev != null) {
                cll.tail = cll.tail.prev;
                cll.tail.next = null;
            } else {
                cll.tail = null;
            }
            return value;
        } else {
            throw new NoSuchElementException();
        }
    }

    public void push(E value) {
        cll.add(value);
    }

}
