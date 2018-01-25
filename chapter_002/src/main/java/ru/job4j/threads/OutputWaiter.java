package ru.job4j.threads;

public class OutputWaiter {

    public OutputWaiter() {
        new MainThread();

        try {
            new Calculator().t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void someCalculation() {
        for (int i = 0; i < 10; i++) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("Some calculations" + i);
        }

        notify();
        }

    synchronized void printInfo() {

        System.out.println("Start program");
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End program");

    }

    public class MainThread implements Runnable {
        Thread t;
        public MainThread() {
           t =  new Thread(this);
           t.start();
        }

        @Override
        public void run() {
            printInfo();
        }
    }

    public class Calculator implements Runnable {
        Thread t;
        public Calculator() {
            t = new Thread(this);
            t.start();
        }

        @Override
        public void run() {
            someCalculation();
        }
    }
}
