package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {
    private SimpleSet<String> ss = new SimpleSet<>();
    @Before
    public void setUp() {

        ss.add("first");
        ss.add("second");
        ss.add("third");
    }

    @Test
    public void whenAddDuplicateThenFalse() {
        assertThat(ss.add("fourth"), is(true));
        assertThat(ss.add("second"), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorTest() {
        Iterator it = ss.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("first"));
        assertThat(it.next(), is("second"));
        assertThat(it.next(), is("third"));
        assertThat(it.next(), is("fourth"));
        assertThat(it.hasNext(), is(false));
        it.next();

    }

}