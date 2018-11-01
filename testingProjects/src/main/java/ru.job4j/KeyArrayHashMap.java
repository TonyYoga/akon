package ru.job4j;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class KeyArrayHashMap<K, V> implements Iterable {

    private int arraylength = 100;
    private int counter = 0;
    private Object[] hashmap = new Object[arraylength];

    public class HMentry {
        Object[] key;
        V value;

        public HMentry(Object[] key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            HMentry hMentry = (HMentry) o;
            // Probably incorrect - comparing Object[] arrays with Arrays.equals
            if (!Arrays.equals(key, hMentry.key)) {
                return false;
            }
            return value != null ? value.equals(hMentry.value) : hMentry.value == null;
        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(key);
            return result;
        }
    }

    //Calculating hashIndex
    private int hIndex(Object[] key) {
        return key != null ? Arrays.hashCode(key) % arraylength : 0;
    }

    //Method insert
    public boolean insert(Object[] key, V value) {
        if (contains(key)) {
            return false;
        }
        if (counter >= arraylength * 0.5) {
            hashmap = arrayGroover(hashmap);
        }
        hashmap[hIndex(key)] = new HMentry(key, value);
        counter++;
        return true;
    }

    //Method get
    public V get(Object[] key) {
        HMentry ks = (HMentry) hashmap[hIndex(key)];
        if (ks == null) {
            throw new NoSuchElementException();
        }
        return ks.value;
    }

    //method delete
    public boolean delete(Object[] key) {
        if (!contains(key)) {
            return false;
        }
        hashmap[hIndex(key)] = null;
        return true;
    }

    //method contains
    public boolean contains(Object[] key) {
        if (counter == 0) {
            return false;
        }
        HMentry entry = (HMentry) hashmap[hIndex(key)];
        return entry != null && key.equals(entry.key);
    }

    //privat method increasing the size of array
    private Object[] arrayGroover(Object[] table) {
        arraylength = table.length * 2;
        Object[] tmp = new Object[arraylength];
        for (Object o: table) {
            if (o != null) {
                HMentry ks = (HMentry) o;
                tmp[hIndex(ks.key)] = ks;
            }
        }
        return tmp;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int current = 0;
            int index = 0;

            @Override
            public boolean hasNext() {
                return current < counter;
            }

            @Override
            public Object next() {
                while (hasNext()) {
                    HMentry ks = (HMentry) hashmap[index++];
                    if (ks != null) {
                        current++;
                        return ks;
                    }
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }



}
