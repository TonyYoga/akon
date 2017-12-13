package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ContainerLinkedList<E> implements Iterable<E> {
    Node<E> root;
    Node<E> tail;
    class Node<E> {
        E value;
        int index;
        Node<E> next;
        Node<E> prev;

        public Node(E value, Node next, Node prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
            this.index++;
        }
    }

    void add(E value) {
        if (root == null) {
            root = new Node<>(value, null, null);
            tail = root;
        } else {
            Node<E> tmp = new Node<>(value, null, tail); // new node  with field prev pointing on the previuos element
            tail.next = tmp; // tail field next pointing on new element
            tail = tmp; // new tail
        }
    }

    E get(int index) {
        Node<E> tmp = root;
        while (tmp != null) {
            if (index == tmp.index) {
                return tmp.value;
            } else {
                tmp = tmp.next;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> current = root;

            @Override
            public boolean hasNext()  {
                return current != null ? true : false;
            }

            @Override
            public E next() {
                Node<E> result;
                if (hasNext()) {
                    result = current;
                    current = result.next;
                    return result.value;
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
