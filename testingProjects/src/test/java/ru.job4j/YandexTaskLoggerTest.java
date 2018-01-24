package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class YandexTaskLoggerTest {

    private YandexTaskLogger ytl;

    @Test
    public void loggerTest() {
        ytl = new YandexTaskLogger();

        ytl.logmaker("test");

        System.out.println(ytl.printLogCounter(60000));
        //1000*60 = 60000 милисек - минута
        System.out.println(ytl.printLogCounter(3600000));
        //1000*60*60 = 3600000 милисек - час
        System.out.println(ytl.printLogCounter(86400000));
        //1000*60*60*24 = 86400000 милисек - сутки

    }


}