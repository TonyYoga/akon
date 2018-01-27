package ru.job4j.threads;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TreedInteraptor {
    private CountChar countChar;
    private long workTime;
    private String filename;
    private long startTimer;

    public TreedInteraptor(long ms, String filename) {
        this.workTime = ms * 1000000; //to nanosec
        this.filename = filename;
        startTimer = System.nanoTime();
        countChar = new CountChar();
        try {

            new Time().thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public class Time implements Runnable {
        Thread thread;


        public Time() {
            thread = new Thread(this, "Counting time");
            this.thread.start();
        }

        @Override
        public void run() {

            //System.out.println(String.format("%s ... %s", t1,  workTime));
            while ((System.nanoTime() - startTimer < workTime) && countChar.thread.isAlive()) {
                System.out.println(); //do cycle while we have time and counting process alive
            }


            //mark process if time over
            if (!countChar.thread.isInterrupted() && countChar.thread.isAlive()) {
                countChar.thread.interrupt();
                //System.out.println(countChar.thread.isInterrupted());
                //System.out.println("Thread was interrupted");

            }

        }
    }

    public class CountChar implements Runnable {
        Thread thread;

        public CountChar() {
            thread = new Thread(this, "Char counting");
            thread.start();

        }

        @Override
        public void run() {
            int charcounter = 0;
            //System.out.println(thread.isInterrupted());
            try {
                FileReader fr = new FileReader(filename);
                while (fr.ready() && !thread.isInterrupted()) {
                    int sym = fr.read();
                    if (((sym >= 32) && (sym <= 126))) {
                        charcounter++;
                    }
                }
                if (thread.isInterrupted()) {
                    System.out.println("Thread was interrupted");
                }
                System.out.println(String.format("Char counter: %s", charcounter));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
