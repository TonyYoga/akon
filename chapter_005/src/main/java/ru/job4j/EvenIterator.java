package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {
    private final int[] values;
    private int index;

    public EvenIterator(int[] values) {
        this.values = values;
    }

    //метод isEven возвращает true если переданное в метод число - четное
    private boolean isEven(int value) {
        return value % 2 == 0;
    }

    @Override
    public boolean hasNext() {
        int indexTemp = index; // временный индекс, нужен для того, чтобы не менять основной индекс
        while (indexTemp + 1 < values.length) {
            if (isEven(values[++indexTemp])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object next() {
        int res;
        if (hasNext()) {
            do {
                res = values[index++];
             } while (!isEven(res));
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
