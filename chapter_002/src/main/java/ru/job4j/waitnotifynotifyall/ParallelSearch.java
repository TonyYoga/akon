package ru.job4j.waitnotifynotifyall;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
  Параллельный поиск текста в файле
  задача 1106
 */

@ThreadSafe
public class ParallelSearch {

    private final String root;
    private final String text;
    private List<String> exts;
    @GuardedBy("this")
    private final Queue<String> files = new LinkedList<>(); // очередь на проверку
    @GuardedBy("this")
    private final List<String> path = new ArrayList<>(); //результаты

    private volatile boolean finish = false;

    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    class FileSearcher implements Runnable {

        class Visitor extends SimpleFileVisitor {

            @Override
            public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
                String extension = "";

                int i = file.toString().lastIndexOf(".");
                if (i >= 1) {
                    extension = file.toString().substring(i + 1);
                }
                if (extension != "" && exts.contains(extension)) {
                    synchronized (files) {
                        files.offer(file.toString());
                    }
                    changeLock(files, false);
                }


                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

        }


        @Override
        public void run() {
            Visitor visitor = new Visitor();
            try {
                Path p = Paths.get(root);
                Files.walkFileTree(p, visitor);
            } catch (IOException e) {
                e.printStackTrace();
            }
            finish = true;
            //unlock and finish searching in the files
            changeLock(files, false);
        }
    }

    class TextSearcher implements Runnable {
        @Override
        public void run() {
            Pattern pattern = Pattern.compile(text);

            while (!finish) {
                synchronized (files) {
                    while (!files.isEmpty()) {
                        boolean isFound = false;

                        String curfile = files.poll();
                        File file = new File(curfile);
                        try {
                            BufferedReader br = new BufferedReader(new FileReader(file));
                            while (br.ready() && !isFound) {
                                Matcher m = pattern.matcher(br.readLine());

                                if (m.matches()) {

                                    isFound = true;
                                    path.add(curfile);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                //if list of files - empty - take a pause
                changeLock(files, true);
            }
        }
    }

    private void changeLock(Object sync, boolean flag) {
        try {
            if (flag) {
                synchronized (sync) {
                    //lock
                    sync.wait();
                }

            } else {
                synchronized (sync) {
                    //unlock
                    sync.notify();
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public void init() {

        Thread search = new Thread(new FileSearcher());
        Thread read = new Thread(new TextSearcher());

        search.start();
        read.start();
        try {
            search.join();
            read.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    synchronized List<String> result() {
        return this.path;
    }

    public static void main(String[] args) {
        List<String> extensions = new ArrayList<>();
        extensions.add("txt");
        ParallelSearch ps = new ParallelSearch("/home/user", "test", extensions);
        ps.init();
        System.out.println(ps.result());
    }


}
