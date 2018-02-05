package ru.job4j.threads;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TreedInteraptor {
    private long workTime;
    private static String filename;
    private volatile boolean timeOut;

    public boolean isTimeOut() {
        return timeOut;
    }

    public void setTimeOut(boolean timeOut) {
        this.timeOut = timeOut;
    }

    public TreedInteraptor(long ms, String filename) {
        this.workTime = ms; //to nanosec
        this.filename = filename;



    }

    public class TimeCount implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(workTime);
                setTimeOut(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public class CountChar implements Runnable {

        @Override
        public void run() {
            int charcounter = 0;
            try (FileReader fr = new FileReader(filename)) {

                while (fr.ready() && !Thread.currentThread().isInterrupted()) {
                    int sym = fr.read();
                    if (((sym >= 32) && (sym <= 126))) {
                        charcounter++;
                    }
                }
                System.out.println(String.format("Char counter: %s", charcounter));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    void doWork() {
        Thread timecount = new Thread(new TimeCount(), "Timer thread");
        Thread counter = new Thread(new CountChar(), "Counting thread");
        timecount.start();
        counter.start();


        while (counter.isAlive()) {
            if (isTimeOut()) {
                counter.interrupt();
            }
        }
    }

    public static void main(String[] args) {
        TreedInteraptor ti = new TreedInteraptor(50, "chapter_002/src/main/resources/test.txt");
        ti.doWork();


    }
}
