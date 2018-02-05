package ru.job4j.threads;

public class OutputWaiter {

    private volatile boolean waiter = false;
    private MainThread mt;
    private Calculator calc;

    OutputWaiter() {
        mt = new MainThread();
        calc = new Calculator();
    }

    public class MainThread implements Runnable {

        @Override
        public void run() {
            System.out.println("Start program");
            while (true) {
                if (waiter) {
                    break;
                }
            }
            System.out.println("End program");
        }
    }

    public class Calculator implements Runnable {

        @Override
        public void run() {
            TextSpaceWordCounter tswc = new TextSpaceWordCounter("chapter_002/src/main/resources/test.txt");
            tswc.countIt();
            waiter = true;
        }
    }

    public static void main(String[] args) {
        OutputWaiter ow = new OutputWaiter();
        Thread mainThread = new Thread(ow.mt, "Thread info");
        Thread calcThread = new Thread(ow.calc, "Thread calc");
        mainThread.start();
        calcThread.start();

        try {
            mainThread.join();
            calcThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
