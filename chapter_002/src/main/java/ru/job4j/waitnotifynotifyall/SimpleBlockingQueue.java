package ru.job4j.waitnotifynotifyall;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/*
#1098
Pattern Producer - Consumer
 */

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private final Object lock = new Object();
    private final int size = 10;
    private boolean block = true;

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public void offer(T value) throws InterruptedException {
        synchronized (lock) {
            if (queue.size() > 10) {
                System.out.println("block offer");
                changeBlock(true);
            }
            queue.add(value);
            System.out.println("offer " + value);
            if (block) {
                changeBlock(false);
            }
            //add
        }

    }

    public T poll() throws InterruptedException {
        T value;
        synchronized (lock) {
            if (queue.isEmpty()) {
                System.out.println("block poll");
                changeBlock(true);
            }
            value = queue.poll();
            System.out.println("peek " + value);
            if (block) {
                changeBlock(false);
            }
            //peek
        }
        return value;
    }

    void changeBlock(boolean enable) throws InterruptedException {
        if (enable) {
            synchronized (lock) {
                //System.out.println("block");
                this.block = enable;
                lock.wait();
            }
        } else { //unblock
            synchronized (lock) {
                System.out.println("unblock");
                this.block = enable;
                lock.notify();
            }
        }

    }


}
