package ru.job4j.searchspecificfiles;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SearchTest {
    private ISearch iSearch;
    private List<String> listExt;
    private String dirPath;
    private File[] expected;

    @Before
    public void setUp() {
        iSearch = new Search();
        listExt = new ArrayList<>();
        listExt.add("txt");
        listExt.add("log");
        dirPath = System.getProperty("java.io.tmpdir") + "/javatest";
        expected = new File[]{
                new File("/tmp/javatest/file01.log"),
                new File("/tmp/javatest/file02.txt"),
                new File("/tmp/javatest/j2/file03.log"),
                new File("/tmp/javatest/j2/file04.txt"),
                new File("/tmp/javatest/j1/j11/file06.txt"),
                new File("/tmp/javatest/j1/j11/file05.log"),
                new File("/tmp/javatest/j1/j11/java111/file08.txt"),
                new File("/tmp/javatest/j1/j11/java111/file07.log")

        };

    }
    @Test
    public void testSearchSpecificFiles() {

        List<File> fileList = iSearch.files(dirPath, listExt);
        assertArrayEquals(expected, fileList.toArray(new File[0]));

    }
}