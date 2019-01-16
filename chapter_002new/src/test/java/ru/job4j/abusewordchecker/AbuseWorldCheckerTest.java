package ru.job4j.abusewordchecker;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class AbuseWorldCheckerTest {
    private IAbuseWordChecker iawc;
    private String[] abuse;
    InputStream inputStream;
    OutputStream outputStream;
    String expected;

    @Before
    public void setUp() {
        iawc = new AbuseWorldChecker();
        abuse = new String[]{"abuse1", "abuse2", "abuse3"};
        String str = "aaaa abuse1 bbbb abuse2\nabuse3 aaaa\ncccc cccc cccc\n";
        expected = "aaaa bbbb\naaaa\ncccc cccc cccc\n";
        inputStream = new ByteArrayInputStream(str.getBytes());
        outputStream = new ByteArrayOutputStream();
    }
    @Test
    public void dropAbuses() {
        try (ByteArrayOutputStream ba = new ByteArrayOutputStream()) {
            iawc.dropAbuses(inputStream, ba, abuse);
            assertArrayEquals(expected.getBytes(), ba.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}