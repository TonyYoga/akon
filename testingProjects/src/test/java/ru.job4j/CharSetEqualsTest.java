package ru.job4j;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CharSetEqualsTest {

    CharSetEquals cse;

    @Before
    public void SetUp() {
        cse = new CharSetEquals("Hello");
    }

    @Test
    public void hasTrueIfCharactersEquals() {
        assertThat(cse.equals("olleH"), is(true));
        assertThat(cse.equals("olleh"), is(false));
        assertThat(cse.equals("olle"), is(false));
        assertThat(cse.equals("oellH"), is(true));
        assertThat(cse.equals(null), is(false));
    }

    @Test
    public void testcompareAll() {
        assertThat(CharSetEquals.compareAll("hello", "hello"), is(true));
        assertThat(CharSetEquals.compareAll("hello", "olleh"), is(true));
        assertThat(CharSetEquals.compareAll("hello", "olle"), is(false));
        assertThat(CharSetEquals.compareAll("hello", "Hello"), is(false));
        assertThat(CharSetEquals.compareAll(null, "hello"), is(false));

    }


}