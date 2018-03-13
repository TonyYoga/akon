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

    @GuardedBy("queue")
    private final Queue<T> queue = new LinkedList<>();

    public void offer(T value) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() > 10) {
                System.out.println("Queue full - wait");
                queue.wait();
            }
            queue.add(value);
            queue.notify();
            System.out.println("offer " + value + " and unblock");
            //add
        }

    }

    public T poll() throws InterruptedException {
        T value;
        synchronized (queue) {
            while (queue.isEmpty()) {
                System.out.println("Queue empty - wait");
                queue.wait();
            }
            value = queue.poll();
            System.out.println("peek " + value);
            queue.notify();
            System.out.println("Queue unblock");
            //peek
        }
        return value;
    }


}
