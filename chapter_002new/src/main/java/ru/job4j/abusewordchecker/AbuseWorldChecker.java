package ru.job4j.abusewordchecker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class AbuseWorldChecker implements IAbuseWordChecker {
    @Override
    public void dropAbuses(InputStream in, OutputStream out, final String[] abuse) {
        System.setIn(in);
        String current;
        try (Scanner inStreamScan = new Scanner(System.in)) {
            while (inStreamScan.hasNext()) {
                current = inStreamScan.nextLine();
                out.write(dropAbusesInString(current, abuse).getBytes());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String dropAbusesInString(String str, final String[] abuse) {
        String result = str;
        for (String abuseWord : abuse) {
            result = result.replaceAll(abuseWord, "");
        }
        result = result.replaceAll("  ", " ").trim();
        return result + "\n";
    }
}
