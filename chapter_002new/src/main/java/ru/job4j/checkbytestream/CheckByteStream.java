package ru.job4j.checkbytestream;

import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Konkin Anton
 * @version 1.0
 * @since 09/01/2019
 */

public class CheckByteStream implements ICheckByteStream {
    @Override
    public boolean isNumber(InputStream in) {
        System.setIn(in);
        boolean res = false;
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext() && !res) {
                if (scanner.hasNextInt()) {
                    res = (scanner.nextInt() % 2 == 0);
                } else {
                    scanner.next();
                }
            }
        }
        return res;
    }
}
