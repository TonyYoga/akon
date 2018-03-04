package ru.job4j.waitnotifynotifyall;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadPool {
    final int maxPol;
    private volatile int threadpullcounter;
    private Queue<Work> workQueue;


    static class Work {
        private int num;

        public Work(int num) {
            this.num = num;
        }

        void doSome() {
            System.out.println("Start task N "+ num);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("Task %s done", num));
        }
    }

    public ThreadPool() {
        this.maxPol = Runtime.getRuntime().availableProcessors();
        this.threadpullcounter = 0;
        this.workQueue = new LinkedList<>();
    }

    void addwork(Work work) {
        synchronized (workQueue) {
            System.out.println(String.format("Task %s added", work.num));
            workQueue.offer(work);

            while (!workQueue.isEmpty()) {
                if (threadpullcounter < maxPol) {
                    threadpullcounter++;
                    new Thread() {
                        @Override
                        public void run() {

                                workQueue.poll().doSome();
                                threadpullcounter--;
                        }
                    }.start();
                }
            }
        }

    }

    public static void main(String[] args) {
        ThreadPool tp = new ThreadPool();
        for (int i = 0; i < 20; i++) {
            tp.addwork(new Work(i));
        }
    }

}
