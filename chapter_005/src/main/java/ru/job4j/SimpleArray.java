package ru.job4j;


public class SimpleList <E> {
    Object[] objects;
    int index = 0;

    public SimpleList(int size) {
        this.objects = new Object[size];
    }

    public void add(E value){
        this.objects[index++] = value;
    }

    public E get(int position) {
        return (E) this.objects[position];
    }

    public boolean update(int position, E value){
        if (position<objects.length) {
            this.objects[position] = value;
            return true;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean delete(int position){
        if (position<objects.length) {
            int tempIndex = 0;
            Object[] tempObjects = new Object[this.objects.length-1];
            for (int index = 0; index < this.objects.length ; index++) {
                if (index != position) {
                    tempObjects[tempIndex++] = this.objects[index];
                }
            }
            this.objects = tempObjects;
            return true;
        } else {
            throw new IndexOutOfBoundsException();
        }

    }
}
