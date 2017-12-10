package ru.job4j;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenCreateContanierShoudReturnSameType() {
        SimpleArray<String> simpleArray = new SimpleArray<>(1);
        simpleArray.add("test");
        String result = simpleArray.get(0);

        assertThat(result, is("test"));

    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenUpdateElementArrayMustContainIt() {
        SimpleArray<String> simpleArray = new SimpleArray<>(2);
        simpleArray.add("test01");
        simpleArray.add("test02");
        simpleArray.update(1, "upd");
        String result = simpleArray.get(1);

        assertThat(result, is("upd"));

        simpleArray.update(2, "wrong");

    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenDeleteElemListExcludeThisElement() {
        SimpleArray<String> simpleArray = new SimpleArray<>(2);
        simpleArray.add("test01");
        simpleArray.add("test02");
        simpleArray.add("test03");

        assertThat(simpleArray.get(1), is("test02"));

        simpleArray.delete(1);

        assertThat(simpleArray.get(1), is("test03"));

        simpleArray.delete(4);

    }



}