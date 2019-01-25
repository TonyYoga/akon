package ru.job4j.lenghtsort;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.Queue;

public class SortingThread {
    private final int maxPool;
    private volatile int threadpullcounter;
    private Queue<Job> workQueue;
    private ArrayDeque<File> filesForSort;
    private String tmpDir;

    public SortingThread(ArrayDeque<File> filesForSort, String tmpDir) {
        this.filesForSort = filesForSort;
        maxPool = Runtime.getRuntime().availableProcessors();
        threadpullcounter = 0;
        workQueue = new ArrayDeque<>();
        this.tmpDir = tmpDir;
    }

    public class Job {

        private File file1;
        private File file2;

        public Job(File file1, File file2) {
            this.file1 = file1;
            this.file2 = file2;
        }

        private void sortAndMergeFiles() {
            File result = new File(tmpDir + "tmpfileSorted"  + "." + LocalTime.now().getNano());
            try (FileWriter outPutFile = new FileWriter(result);
                 BufferedReader br1 = new BufferedReader(new FileReader(file1));
                 BufferedReader br2 = new BufferedReader(new FileReader(file2))) {
                boolean file1End = false;
                boolean file2End = false;
                String fromBr1 = br1.readLine();
                String fromBr2 = br2.readLine();
                while (!file1End | !file2End) {
                    file1End = fromBr1 == null;
                    file2End = fromBr2 == null;
                    if (!file1End & !file2End) {
                        if (Integer.compare(fromBr1.length(), fromBr2.length()) < 1) {
                            outPutFile.write(fromBr1  + "\n");
                            fromBr1 = br1.readLine();
                        } else {
                            outPutFile.write(fromBr2 + "\n");
                            fromBr2 = br2.readLine();
                        }
                    } else if (file1End & !file2End) {
                        outPutFile.write(fromBr2  + "\n");
                        fromBr2 = br2.readLine();
                    } else if (file2End & !file1End) {
                        outPutFile.write(fromBr1  + "\n");
                        fromBr1 = br1.readLine();
                    }
                }
                Files.delete(file1.toPath());
                Files.delete(file2.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            setFileToQueue(result);
        }




    }
    private synchronized File getFileFromQueue() { return filesForSort.poll(); }
    private synchronized void setFileToQueue(File file) { filesForSort.addLast(file);

    }
    public void addSort() {
        synchronized (workQueue) {
            workQueue.offer(new Job(getFileFromQueue(), getFileFromQueue()));
            System.out.println("add job");
                while (!workQueue.isEmpty()) {
                    if (threadpullcounter < maxPool) {
                        threadpullcounter++;

                        new Thread(() -> {
                            System.out.println("Thread is started" + threadpullcounter);
                            workQueue.poll().sortAndMergeFiles();
                            threadpullcounter--;
                        }).start();
                    }
                }
        }
    }

    public boolean isEmpty() {
        return workQueue.isEmpty();
    }


}
