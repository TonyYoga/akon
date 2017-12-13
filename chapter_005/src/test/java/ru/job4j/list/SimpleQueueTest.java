package ru.job4j.list;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.NoSuchElementException;

public class SimpleQueueTest {
    private SimpleQueue<String> simpleQueue = new SimpleQueue<>();

    @Test(expected = NoSuchElementException.class)
    public void whenLastInThenFirstOut() {
        simpleQueue.push("first");
        simpleQueue.push("second");
        simpleQueue.push("third");

        assertThat(simpleQueue.poll(), is("first"));
        assertThat(simpleQueue.poll(), is("second"));
        assertThat(simpleQueue.poll(), is("third"));
        simpleQueue.poll();


    }
}