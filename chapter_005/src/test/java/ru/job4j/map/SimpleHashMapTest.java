package ru.job4j.map;


import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
    private SimpleHashMap<String, String> shm;
    private Iterator it;

    @Before
    public void setUp() {
        shm = new SimpleHashMap<>();

        
    }

    @Test (expected = NoSuchElementException.class)
    public void testInsGetDelKeySets() {
        assertThat(shm.insert("one", "1"), is(true));
        assertThat(shm.insert("two", "2"), is(true));
        assertThat(shm.insert("three", "3"), is(true));
        assertThat(shm.insert("four", "4"), is(true));
        assertThat(shm.insert("five", "5"), is(true));
        assertThat(shm.insert("five", "6"), is(false));
        
        assertThat(shm.get("one"), is("1"));
        assertThat(shm.get("two"), is("2"));
        assertThat(shm.get("three"), is("3"));
        
        assertThat(shm.delete("four"), is(true));
        assertThat(shm.get("four"), is(false));
        
    }

    @Test (expected = NoSuchElementException.class)
    public void testIterator() {
        shm = new SimpleHashMap<>();
        it = shm.iterator();
        shm.insert("one", "1");
        shm.insert("two", "2");

        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        System.out.println(it.next());
        System.out.println(it.next());
        assertThat(it.hasNext(), is(false));
        it.next();
        
    }

//    @Test
//    public void testIncreasinOfEllements() {
//
//        for (int i = 0; i < 51; i++) {
//            shm.insert("el" + i, "test" + 1);
//        }
//        String value1 = shm.get("el10");
//        for (int i = 0; i < 51; i++) {
//            shm.insert("new" + i, "test" + 1);
//        }
//        //String value2 = shm.get("el10");
//        //assertThat(value1.equals(value2), is(true));
//
//    }
}