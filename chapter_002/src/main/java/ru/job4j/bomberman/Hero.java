package ru.job4j.bomberman;
import ru.job4j.bomberman.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Hero implements Runnable {
    private final Position start;
    private Position current;
    private Board board;

    private int stepCounter = 0;

    public Hero(Position start, Board board) {
        this.board = board;
        this.start = start;
        current = start;
    }

    public Position getCurrent() {
        return current;
    }

    public void setCurrent(Position current) {
        this.current = current;
    }

    boolean step(Board board) {
        Random random = new Random();
        int gen = random.nextInt(4);
        switch (gen) {
            //Random stepgenerator
            case 0:
                if (moveRight(board)) {
                    break;
                }
            case 1:
                if (moveUp(board)) {
                    break;
                }
            case 2:
                if (moveLeft(board)) {
                    break;
                }
            case 3:
                if (moveDown(board)) {
                    break;

                }
                break;
            default:
                System.out.println("Что то пошло не так");
                break;

        }

        return true;
    }

    boolean moveUp(Board board) {
        System.out.println("Try go UP");
        if (moveLock(board, 0, 1)) {
            System.out.println("Go Up");
            return true;
        }
        return false;
    }

    boolean moveDown(Board board) {
        System.out.println("Try go Down");
        if (moveLock(board, 0, -1)) {
            System.out.println("Go Down");
            return true;
        }
        return false;

    }

    boolean moveLeft(Board board) {
        System.out.println("Try go Left");
        if (moveLock(board, -1, 0)) {
            System.out.println("Go Left");
            return true;
        }
        return false;
    }

    boolean moveRight(Board board) {
        System.out.println("Try go Right");
        if (moveLock(board, 1, 1)) {
            System.out.println("Go Right");
            return true;
        }
        return false;

    }
    /*
    moveLock private method used to try lock new cell after movement of the Hero. If it free - lock it, else
    return - false
     */
    private boolean moveLock(Board board, int x, int y) {

        int nextpositionY = current.getY() + y;
        int nextPositionX = current.getX() + x;

        if (nextPositionX < 0
                || nextpositionY < 0
                || nextPositionX > board.dimX
                || nextpositionY > board.dimY) {
            return false;
        }
        System.out.println(String.format("X %s : Y %s", nextPositionX, nextpositionY));
        try {
          //try to lock new cell
            if (board.board[nextpositionY][nextPositionX].tryLock(500, TimeUnit.MILLISECONDS)) {
                //System.out.println("Break point");
                board.board[current.getY()][current.getX()].unlock(); //unlock previos cell
                current.setY(nextpositionY);
                current.setX(nextPositionX);
                return true;
            }
        }
//        catch (NullPointerException e) {
//            System.out.println("WTF");
//            return false;
//        }
        catch (InterruptedException e) {
            System.out.println("WTF");
            return false;
        }


        return true;
    }

    @Override
    public void run() {
        while (stepCounter < 100) {
            step(board); //??
            stepCounter++;
        }
        System.out.println("Бензин закончился");


    }
}
