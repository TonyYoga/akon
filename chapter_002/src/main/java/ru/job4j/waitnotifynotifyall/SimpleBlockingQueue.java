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

    private final int size = 10;
    private boolean block = true;

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public void offer(T value) throws InterruptedException {
        synchronized (queue) {
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
        synchronized (queue) {
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
            synchronized (queue) {
                //System.out.println("block");
                this.block = enable;
                queue.wait();
            }
        } else { //unblock
            synchronized (queue) {
                System.out.println("unblock");
                this.block = enable;
                queue.notify();
            }
        }

    }


}
