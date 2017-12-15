package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleLinkedSetTest {
    private SimpleLinkedSet<String> sls;
    @Before
    public void setSls() {
        sls = new SimpleLinkedSet<>();
        sls.add("first");
        sls.add("second");
        sls.add("third");
    }

    @Test
    public void whenAddDuplicatesReturnFalse() {
        assertThat(sls.add("fourth"), is(true));
        assertThat(sls.add("second"), is(false));

    }

    @Test(expected = NoSuchElementException.class)
    public  void iteratorTest() {
        Iterator<String> it = sls.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("first"));
        assertThat(it.next(), is("second"));
        assertThat(it.next(), is("third"));
        assertThat(it.next(), is("fourth"));
        it.next();


    }
}