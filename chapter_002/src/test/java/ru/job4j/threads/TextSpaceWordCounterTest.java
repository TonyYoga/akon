package ru.job4j.threads;

import org.junit.Test;

public class TextSpaceWordCounterTest {
    TextSpaceWordCounter tswc;

    @Test
    public void test() {
        tswc = new TextSpaceWordCounter("src/main/resources/test.txt");
        tswc.countIt();


    }


}