package ru.job4j.jmm;

public class JavaMemoryModelProblemExamples {
    Integer visibility, raceCondition;

    public class ThreadFirst implements Runnable {
        @Override
        public void run() {
            int localvis = visibility++;
            System.out.println("Thread #1 visibility variable:" + localvis);
            raceCondition++;


        }
    }

    public class ThreadSecond implements Runnable {
        @Override
        public void run() {
            int localvis = visibility;
            System.out.println("Thread #2 visibility variable:" + localvis);
            raceCondition++;
        }
    }

    public void chekmethod() {
        visibility = 1;
        raceCondition = 1;
        Thread first = new Thread(new ThreadFirst(), "First Thread");
        Thread second = new Thread(new ThreadSecond(), "Second Thread");
        first.start();
        second.start();
        System.out.printf("checkmethod race condition: %s must be 3 \n", raceCondition);
        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("checkmethod variables visibility %s \n", visibility);
        //System.out.printf("checkmethod race condition: %s must be 3 \n", raceCondition);

    }
}
