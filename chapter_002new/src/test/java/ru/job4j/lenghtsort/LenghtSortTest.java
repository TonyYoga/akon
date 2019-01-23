package ru.job4j.lenghtsort;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

public class LenghtSortTest {
    ILenghtSort iLenghtSort;
    File source;
    File distination;

    @Before
    public void setUp() {
        source = new File("/home/user/projects/akon/chapter_002new/src/main/resources/testfile40.txt");
        distination = new File("/home/user/projects/akon/chapter_002new/src/main/resources/desttestfile.txt");
        iLenghtSort = new LenghtSortV2();

    }

    @Test
    public void testSortingFile() {
        LocalTime start = LocalTime.now();
        iLenghtSort.sort(source, distination);
        LocalTime end = LocalTime.now();
        System.out.println(ChronoUnit.SECONDS.between(start, end));
    }

}