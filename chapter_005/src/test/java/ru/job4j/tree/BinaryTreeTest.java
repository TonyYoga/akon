package ru.job4j.tree;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BinaryTreeTest {

    @Test
    public void testBinary() {
        BinaryTree<Integer> bt = new BinaryTree<>(15);

        assertThat(bt.add(10), is(true));
        assertThat(bt.add(10), is(true)); //left
        assertThat(bt.add(7), is(true));   //left
        assertThat(bt.add(19), is(true));  //right
        assertThat(bt.add(5), is(true));  //left
        assertThat(bt.add(8), is(true));   //right
        assertThat(bt.add(12), is(true));  //left
        assertThat(bt.add(3), is(true));   //left
        assertThat(bt.add(20), is(true));  //right
        assertThat(bt.add(null), is(false));


    }

}