package ru.job4j.checkbytestream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CheckBiteStreamTest {
    ICheckByteStream biteStream;
    InputStream inputStreamOdd;
    InputStream inputStreamEven;

    @Before

    public void setUp() {
        biteStream = new CheckByteStream();
        String strEven = "a\n1\na\n2\n";
        String strOdd = "1\n3\na\n";
        inputStreamEven = new ByteArrayInputStream(strEven.getBytes());
        inputStreamOdd = new ByteArrayInputStream(strOdd.getBytes());
    }

    @Test
    public void testEvenInputStream() {
        Assert.assertTrue(biteStream.isNumber(inputStreamEven));
    }

    @Test
    public void testOddInputStream() {
        Assert.assertFalse(biteStream.isNumber(inputStreamOdd));
    }
}