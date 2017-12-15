package ru.job4j.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedSet<E> implements Iterable<E> {

    private Node<E> root;
    private Node<E> tail;

    private class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        public Node(E value, Node<E> prev, Node<E> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    boolean add(E e) {
        if (root == null) {
            root = new Node<>(e, null, null);
            tail = root;
        } else {
            Node<E> tmp = root;
            while (tmp != null) {
                if (tmp.value.equals(e)) {
                    return false;
                } else {
                    tail = tmp;
                    tmp = tmp.next;
                }
            }
            tmp = tail;
            tail = new Node<>(e, tail, null);
            tmp.next = tail;

        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = root;
            @Override
            public boolean hasNext() {

                return current != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E result = current.value;
                    current = current.next;
                    return result;
                }
                throw new NoSuchElementException();
            }
        };
    }
}
