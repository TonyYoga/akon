package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator {
    private final int[] values;
    private int index;


    public PrimeIterator(int[] values) {
        this.values = values;
    }

    private boolean isPrime(int value) {
        int halfValue = value;
        if (value < 4) {
            return true;
        } else if (value % 2 == 0) {
            return false;
        } else {
            halfValue = value < 25 ? value : (int) Math.sqrt(value);
            for (int i = 5; i <= halfValue; i = i + 2) {
                if (value % i == 0 && value != i) {
                    return false;
                }

            }
        }
        return true;
    }
    @Override
    public boolean hasNext() {
        int indexTemp = index; // временный индекс, нужен для того, чтобы не менять основной индекс
        while (indexTemp + 1 < values.length) {
            if (isPrime(values[++indexTemp])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object next() {
        int res;
        //if (index)
        if (hasNext()) {
            do {
                res = values[index++];
            } while (!isPrime(res));
            return res;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();

    }
}
