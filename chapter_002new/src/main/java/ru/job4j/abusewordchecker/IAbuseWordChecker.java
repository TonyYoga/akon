package ru.job4j.abusewordchecker;

import java.io.InputStream;
import java.io.OutputStream;

public interface IAbuseWordChecker {
    void dropAbuses(InputStream in, OutputStream out, String[] abuse);
}
