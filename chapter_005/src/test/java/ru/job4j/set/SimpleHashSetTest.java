package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleHashSetTest {
    SimpleHashSet<String> shs;
    @Before
    public void setUp() throws Exception {
        shs = new SimpleHashSet<>();
    }

    @Test
    public void whenAddEuqlElementWeHaveFalse() {
        assertThat(shs.add("first"), is(true));
        assertThat(shs.add("second"), is(true));
        assertThat(shs.add("third"), is(true));
        assertThat(shs.add("fourth"), is(true));
        assertThat(shs.add("fifth"), is(true));
        assertThat(shs.add("fifth"), is(false));
        assertThat(shs.contains("first"), is(true));
        assertThat(shs.remove("first"), is(true));
        assertThat(shs.contains("first"), is(false));




    }

}