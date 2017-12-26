package ru.job4j.tree;

import java.util.*;

public class Tree<E> implements SimpleTree<E> {

    Node<E> root;

    public Tree(E value) {
        this.root = new Node<>(value);
    }

    @Override
    public boolean add(E parent, E child) {
        if (parent == null || child == null) {
            return false;
        }
        if (root == null) {
            root = new Node<>(parent);
            root.add(new Node<>(child));
            return true;
        }
        Optional<Node<E>> tmp = findBy(parent);
        if (tmp.isPresent() && !findBy(child).isPresent()) {
            tmp.get().add(new Node<>(child));
            return true;
        }

        return false;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Queue<Node<E>> queue;

            {
                queue = new LinkedList<>();
                if (root != null) {
                    queue.offer(root);
                }

            }

            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> rslt = queue.poll();
                if (!rslt.leaves().isEmpty()) {
                    queue.addAll(rslt.leaves());
                }

                return rslt.getValue();
            }
        };
    }
}
