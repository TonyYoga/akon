package ru.job4j.tree;


import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

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
            //recursiveSerachAndAdd(node);
            searchAndAddBFS(node);
        }
    }

    private void searchAndAddBFS(BiNode<E> node) {
        Queue<BiNode<E>> treestek = new LinkedList<>();
        if (root == null) {
            root = node;
            return;
        }
        treestek.offer(root);
        while (!treestek.isEmpty()) {
            BiNode<E> current = treestek.poll();
            if (current.compareTo(node.value) <= 0) {
                if (current.left == null) {
                    current.left = node;
                    return;
                }
                treestek.offer(current.left);
            } else {
                if (current.right == null) {
                    current.right = node;
                    return;
                }
                treestek.offer(current.right);
            }
        }

    }
}
