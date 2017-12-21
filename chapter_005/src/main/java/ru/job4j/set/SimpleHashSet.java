package ru.job4j.set;


public class SimpleHashSet<E> {
    private int arraylength = 100;
    private Object[] hashtab = new Object[arraylength];
    private int counter = 0;

    private int getIndex(E e) {

        return e != null ? Math.abs(e.hashCode()) % arraylength : 0;
    }


    public boolean add(E e) {

        if (contains(e)) {
            return false;
        }
        if (counter >= arraylength * 0.75) {
            hashtab = hashTabGrove(hashtab);
        }
        hashtab[getIndex(e)] = e;
        counter++;
        return true;

    }

    public boolean contains(E e) {
        return e.equals(hashtab[getIndex(e)]);

    }

    public boolean remove(E e) {
        if (contains(e)) {
            hashtab[getIndex(e)] = null;
            counter--;
            return true;
        }
        return false;
    }

    private Object[] hashTabGrove(Object[] table) {

        arraylength = table.length * 2;
        Object[] tmp = new Object[arraylength];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                E value = (E) table[i];
                tmp[value.hashCode()] = value;
            }
        }
        return tmp;

    }
}
