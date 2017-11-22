package ru.job4j;

public interface Archer {
    boolean shot(Creature c);
    boolean attack(Creature c);
    boolean turn(Creature c);
}
