package ru.job4j.waitnotifynotifyall;


public class Lockmanager {
    private final Object lock = new Object();
    private boolean isLocked = false;

    void lock() throws InterruptedException {

            if (!isLocked) {
                synchronized (lock) {
                    isLocked = true;
                    System.out.println("I lock it");
                    lock.wait();
                }
            }

    }

    void unlock() {

            if (isLocked) {
                synchronized (lock) {
                    isLocked = false;
                    System.out.println("I unlock it");
                    lock.notify();
                }
            }


    }

    public static void main(String[] args) {
        Lockmanager lockmanager = new Lockmanager();
        Thread first = new Thread() {
            @Override
            public void run() {

                try {
                    lockmanager.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        Thread second = new Thread() {
            @Override
            public void run() {

                lockmanager.unlock();
            }
        };

        first.start();
        second.start();

        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
