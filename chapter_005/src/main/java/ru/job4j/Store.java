package ru.job4j;

public interface Store<T extends Base> {
    boolean add(T model);
    T update(T model);
    boolean delete(String id);
}
