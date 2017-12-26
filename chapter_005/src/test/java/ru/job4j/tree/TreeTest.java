package ru.job4j.tree;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test (expected = NoSuchElementException.class)
    public void iteratorTest() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        Iterator it = tree.iterator();
        assertThat(it.hasNext(),
                is(true)
        );
        for (int i = 0; i < 4; i++) {
            System.out.println(it.next());
        }
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void whenTreeBenaryThenTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(3, 5);
        tree.add(2, 6);
        tree.add(4, 7);
        assertThat(tree.isBinary(), is(true));
        tree.add(7, 8);
        tree.add(7, 9);
        tree.add(7, 10);
        assertThat(tree.isBinary(), is(false));
    }
}