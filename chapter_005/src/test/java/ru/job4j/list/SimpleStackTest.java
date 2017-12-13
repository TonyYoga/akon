package ru.job4j.list;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.NoSuchElementException;

public class SimpleStackTest {
    private SimpleStack<String> simpleStack = new SimpleStack<>();

    @Test(expected = NoSuchElementException.class)
    public void whenLastInThenFirstOut() {
        simpleStack.push("first");
        simpleStack.push("second");
        simpleStack.push("third");

        assertThat(simpleStack.poll(), is("third"));
        assertThat(simpleStack.poll(), is("second"));
        assertThat(simpleStack.poll(), is("first"));
        simpleStack.poll();

    }


}