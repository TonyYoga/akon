package ru.job4j.tree;

/*
package ru.job4j.tree;

public class BiNode<E extends Comparable<? super E>> implements Comparable<BiNode<E>>{
    E value;
    BiNode<E> left;
    BiNode<E> right;

    public BiNode(E value, BiNode<E> left, BiNode<E> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(BiNode<E> o) {
        return value.compareTo(o.value);
    }
}

 */

public class BiNode<E extends Comparable<? super E>> implements Comparable<E> {
    E value;
    BiNode<E> left;
    BiNode<E> right;

    public BiNode(E value, BiNode<E> left, BiNode<E> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(E o) {
        return -value.compareTo(o);
        //return 0;
    }

//    @Override
//    public int compareTo(BiNode<E> o) {
//        return value.compareTo(o.value);
//    }
}
