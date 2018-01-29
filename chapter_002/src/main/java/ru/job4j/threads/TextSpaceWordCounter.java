package ru.job4j.threads;

import java.io.*;


public class TextSpaceWordCounter {
    private String filename;
    private int spacecount;
    private int wordcount;

    public TextSpaceWordCounter(String filename) {
        this.filename = filename;
    }

    private class SpaceCounter implements Runnable {
//        private Thread thread;
//
//        private SpaceCounter() {
//            thread = new Thread(this, "Thread for space");
//            thread.start();
//        }

        @Override
        public void run() {

            try {
                FileReader fr = new FileReader(filename);
                while (fr.ready()) {
                    if (fr.read() == 32) {
                        spacecount++;
                        System.out.println("i found space");
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(String.format("Space counter: %s", spacecount));


        }
    }

    private class WordCounter implements Runnable {
//        private Thread thread;
//
//        private WordCounter() {
//            thread = new Thread(this, "Thread for words");
//            thread.start();
//        }

        @Override
        public void run() {
            boolean prevSym = false;
            try {
                FileReader fr = new FileReader(filename);
                while (fr.ready()) {
                    int sym = fr.read();
                    if (((sym >= 65) && (sym <= 90)) || ((sym >= 97) && (sym <= 122))) {
                        if (!prevSym) {
                            wordcount++;
                            prevSym = true;
                            System.out.println("i found word");
                        }
                    } else {
                        if (prevSym) {
                            prevSym = false;
                        }
                    }
                }
                fr.close();
                System.out.println(String.format("Word counter: %s", wordcount));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void countIt() {
        Thread t1 = new Thread(new WordCounter(), "Thread for wordCounter");
        Thread t2 = new Thread(new SpaceCounter(), "Thread for spaceCounter");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}
