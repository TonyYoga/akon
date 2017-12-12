package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class ContainerLinkedListTest {

    private ContainerLinkedList<String> list;

    @Before
    public void setUp() {
        list = new ContainerLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add("test"+i);
        }
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddElementArrayGrow() {
        assertThat(list.get(9), is("test9"));
        list.add("oneMore");

        assertThat(list.get(10), is("oneMore"));

        list.get(11); //IndexOut...

    }

    @Test (expected = NoSuchElementException.class)
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        Iterator<String> it = list.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test0"));
        assertThat(it.next(), is("test1"));
        assertThat(it.next(), is("test2"));
        assertThat(it.next(), is("test3"));
        assertThat(it.next(), is("test4"));
        assertThat(it.next(), is("test5"));
        assertThat(it.next(), is("test6"));
        assertThat(it.next(), is("test7"));
        assertThat(it.next(), is("test8"));
        assertThat(it.next(), is("test9"));

        assertThat(it.hasNext(), is(false));
        it.next();
    }

}