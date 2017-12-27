package ru.job4j.tree;


public class BinaryTree<E extends Comparable<? super E>> {
    private BiNode<E> root;
    private BiNode<E> current;

    public BinaryTree(E value) {
        this.root = new BiNode<E>(value, null, null);
    }

    public boolean add(E value) {
        if (value == null) {
            return false;
        }
        current = root;
        BiNode<E> newNode = new BiNode<>(value, null, null);
        recursiveSerachAndAdd(newNode);
        return true;
    }

    private void recursiveSerachAndAdd(BiNode<E> node) {

        //BiNode<E> prime = node;

        if (current.compareTo(node.value) <= 0) {
            if (current.left == null) {
                current.left = node;
                return;
            }
            current = current.left;
            recursiveSerachAndAdd(node);
        } else {
            if (current.right == null) {
                current.right = node;
                return;
            }
            current = current.right;
            recursiveSerachAndAdd(node);
        }
    }
}
