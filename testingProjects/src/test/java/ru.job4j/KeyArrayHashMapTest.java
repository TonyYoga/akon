package ru.job4j;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class KeyArrayHashMapTest {
    KeyArrayHashMap<Integer[], String> kahm;
    Iterator it;
    Integer[] int1 = {1, 2, 3};
    Integer[] int2 = {2, 3, 4};
    Integer[] int3 = {3, 4, 5};


    @Before
    public void setUp() {
        kahm = new KeyArrayHashMap<>();
    }

    @Test(expected = NoSuchElementException.class)
    public void testInsGetDelKeySets() {


        assertThat(kahm.insert(int1, "1"), is(true));
        assertThat(kahm.insert(int2, "2"), is(true));
        assertThat(kahm.insert(int3, "3"), is(true));
        assertThat(kahm.insert(int1, "1"), is(false));
        assertThat(kahm.get(int1), is("1"));
        assertThat(kahm.get(int2), is("2"));
        assertThat(kahm.get(int3), is("3"));

        assertThat(kahm.delete(int3), is(true));
        assertThat(kahm.get(int3), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void testIterator() {
        kahm = new KeyArrayHashMap<>();
        it = kahm.iterator();
        kahm.insert(int1, "1");
        kahm.insert(int2, "2");

        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        System.out.println(it.next());
        System.out.println(it.next());
        assertThat(it.hasNext(), is(false));
        it.next();

    }

}