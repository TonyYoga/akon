package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class YandexTaskLoggerTest {

    private YandexTaskLogger ytl;

    @Test
    public void loggerTest() {
        ytl = new YandexTaskLogger();

        for (int i = 0; i < 100; i++) {
            ytl.add("test " + i);
        }

        System.out.println(ytl.countBy(60000));
        //1000*60 = 60000 милисек - минута
        System.out.println(ytl.countBy(60000));
        //1000*60*60 = 3600000 милисек - час
        System.out.println(ytl.countBy(60000));
        //1000*60*60*24 = 86400000 милисек - сутки

    }


}