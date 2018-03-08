package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

public class Board {
    final int dimX;
    final int dimY;
    final ReentrantLock[][] board;

    public Board(int dimX, int dimY) {
        this.dimX = dimX;
        this.dimY = dimY;
        board = new ReentrantLock[dimY][dimX];
    }
}
