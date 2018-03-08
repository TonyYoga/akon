package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

public class Bomberman {
    final int dimX;
    final int dimY;
    public final Board board;

    public Bomberman(int dimX, int dimY) {
        this.dimX = dimX;
        this.dimY = dimY;
        board = new Board(dimX, dimY);
    }

    public Board getBoard() {
        return board;
    }

    public static void main(String[] args) {
        Bomberman bomberman = new Bomberman(20, 20);
        Hero hero1 = new Hero(new Position(1, 1), bomberman.getBoard());
        hero1.run();
//        Thread tr1 = new Thread(hero1, "Hero N1");
//        tr1.start();
//        try {
//            tr1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

}
