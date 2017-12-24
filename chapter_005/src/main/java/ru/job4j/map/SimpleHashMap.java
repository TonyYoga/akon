package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable {
    private int arraylength = 100;
    private int counter = 0;
    private Object[] hashmap = new Object[arraylength];



    public class HMentry {
        K key;
        V value;

        public HMentry(K key, V value) {
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

            HMentry entry = (HMentry) o;

            if (!key.equals(entry.key)) {
                return false;
            }
            return value.equals(entry.value);
        }

        @Override
        public int hashCode() {
            int result = key.hashCode();
            result = 31 * result + value.hashCode();
            return result;
        }
    }

    //Calculating hashIndex

    private int hIndex(K key) {
        return key != null ? key.hashCode() % arraylength : 0;
    }


    //Method insert
    public boolean insert(K key, V value) {
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
    public V get(K key) {
        HMentry ks = (HMentry) hashmap[hIndex(key)];
        if (ks == null) {
            throw new NoSuchElementException();
        }
        return ks.value;
    }

    //method delete
    public boolean delete(K key) {
        if (!contains(key)) {
            return false;
        }
        hashmap[hIndex(key)] = null;
        return true;
    }

    //method contains
    public boolean contains(K key) {
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
//        for (int i = 0; i < table.length; i++) {
//            if (table[i] != null) {
//                HMentry ks = (HMentry) table[i];
//                tmp[hIndex(ks.key)] = ks;
//            }
//        }
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
