package ru.job4j;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class YandexTaskLoggerTest {

    private YandexTaskLogger ytl;

    @Test
    public void loggerTest() {
        ytl = new YandexTaskLogger();

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ytl.add("test " + i);
        }
        long curTime = (new Date()).getTime();
        System.out.println(ytl.binsearch(15000, curTime));
        System.out.println(ytl.countBy(15000, curTime));
        //1000*60 = 60000 милисек - минута
       // System.out.println(ytl.8binsearch(30000));
        //1000*60*60 = 3600000 милисек - час
//        System.out.println(ytl.countBy(60000));
//        //1000*60*60*24 = 86400000 милисек - сутки



    }


}