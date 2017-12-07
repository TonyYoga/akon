package ru.job4j;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleListTest {

    @Test
    public void whenCreateContanierShoudReturnSameType() {
        SimpleList<String> simpleList = new SimpleList<>(1);
        simpleList.add("test");
        String result = simpleList.get(0);

        assertThat(result, is("test"));

    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenUpdateElementArrayMustContainIt() {
        SimpleList<String> simpleList = new SimpleList<>(2);
        simpleList.add("test01");
        simpleList.add("test02");
        simpleList.update(1,"upd");
        String result = simpleList.get(1);

        assertThat(result, is("upd"));

        simpleList.update(2, "wrong");

    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenDeleteElemListExcludeThisElement() {
        SimpleList<String> simpleList = new SimpleList<>(2);
        simpleList.add("test01");
        simpleList.add("test02");
        simpleList.add("test03");

        assertThat(simpleList.get(1), is("test02"));

        simpleList.delete(1);

        assertThat(simpleList.get(1), is("test03"));

        simpleList.delete(4);

    }



}