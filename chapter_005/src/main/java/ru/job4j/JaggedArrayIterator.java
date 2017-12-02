package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JaggedArrayIterator implements Iterator {

    private final int[][] values;
    private int indX = 0;
    private int indY = 0;

    public JaggedArrayIterator(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        // проверка на следующий элемент. Проверяем попадает ли индекс следующего элемента в диапазон матрицы
        //return values.length > indY && values[values.length - 1].length > indX;
        if ((values.length - 1) * (values[values.length - 1].length - 1) >= indY * indX) return true;
        else throw new NoSuchElementException();
        //return (values.length - 1) * (values[values.length - 1].length - 1) >= indY * indX;
    }

    @Override
    public Object next() {
        /* выполняем несколько проверок. Попадает ли элемент в диапазон индексов строки
        *  если не попадает, то проверяем попадает ли элемент в индекс стобца
        *  если индексы выходят за диапазоны - кидаем исключение
         */
        int element = 0;
        if (indX < values[indY].length && indY < values.length) {
            element = values[indY][indX++];

        } else if (indX > (values[indY].length - 1) && (indY+1) < values.length) {
            indX = 0;
            element = values[++indY][indX++];
        } else {
            throw new NoSuchElementException();
        }
        return element;
    }
}
