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
                lock.wait();
            }
            queue.add(value);
            //add
        }

    }

    public T peek() throws InterruptedException {
        T value;
        synchronized (lock) {
            if (queue.isEmpty()) {
                lock.wait();
            }
            value = queue.peek();
        }
        return value;
    }

    private void changeBlock() {

    }

}
