package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleQueue<E> {

    private ContainerLinkedList<E> cll;

    public SimpleQueue() {
        cll = new ContainerLinkedList<>();
    }
    /*
    FIFO logic. When poll - return and remove node. After that decrement all indexs on queue;
     */
    public E poll() {

        if (cll.root != null) {
            E value = cll.root.value;
            if (cll.root.next != null) {
                cll.root = cll.root.next;
                cll.root.prev = null;
                ContainerLinkedList.Node tmp = cll.root;
                while (tmp.next != null) {
                    tmp.index--;
                    tmp = tmp.next;
                }
            } else {
                cll.root = null;
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
