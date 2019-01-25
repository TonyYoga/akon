package ru.job4j.lenghtsort;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Queue;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * 3. Сортировка >3G файл [#860][#103649]
 * @author Konkin Anton
 * @version 3 with MultiThreading
 * @since 16.01.2019
 *
 * testing information
 * to sort file 1GB - 250 seconds
 */


public class LenghtSortV3MT implements ILenghtSort {
//    private final int maxPartSize = 100; // 100 kB
    private final int maxPartSize = 10 * 1024 * 1024; // 1MB
    private final String tmpDir = "/home/user/projects/akon/chapter_002new/src/main/resources/tmpDir/";

    @Override
    public void sort(File source, File distance) {
        ArrayDeque<File> filesForSort = fileDivider(source);
        SortingThread st = new SortingThread(filesForSort, tmpDir);
        while (!st.isEmpty()) {
            if(filesForSort.size() > 1) {
                st.addSort();
            }
        }
        File lastTmp = filesForSort.pollFirst();
        try {
            Files.move(lastTmp.toPath(), distance.toPath(), REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Divide big file to files less then 1MB
     * @param source - sourse file
     * @return list of divided files
     */
    public ArrayDeque<File> fileDivider(File source) {
        ArrayDeque<File> result = new ArrayDeque<>();
        byte[] buf;
        long startPointer = 0;
        long curPointer;
        try (RandomAccessFile raf = new RandomAccessFile(source, "r")) {
            int counter = 0;
            int lenght = 0;
            if (raf.length() > maxPartSize) {
                raf.seek(maxPartSize);
                raf.readLine();
                curPointer = raf.getFilePointer();
                raf.seek(0);
                lenght = (int) (curPointer - startPointer);
                buf = new byte[lenght];

            } else {
                curPointer = raf.length();
                lenght = (int) raf.length();
                buf = new byte[lenght];
            }

            while (buf.length != 0) {
                raf.read(buf, 0, buf.length);
                File outFile = new File(tmpDir + "tmpfilepart" + counter + "." + LocalTime.now().getNano());
                fileWriter(buf, outFile);
                File tmp = getSortedFile(outFile);
                result.add(tmp);
                counter++;
                startPointer = curPointer;
                if ((curPointer + maxPartSize) < raf.length()) {
                    raf.seek(curPointer + maxPartSize);
                    raf.readLine();
                    curPointer = raf.getFilePointer();
                } else {
                    curPointer = raf.length();
                }

                lenght = (int) (curPointer - startPointer);
                buf = new byte[lenght];
                raf.seek(startPointer);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *  fileWriter write byte array to file
     * @param buf - array of information
     * @param distination - destination file
     */
    private void fileWriter(byte[] buf, File distination) {
        try (FileOutputStream fos = new FileOutputStream(distination)) {
            fos.write(buf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * next idea for sorting files
     */
//    private File sortPairFiles(final File file1, final File file2) {
//        File result = new File(tmpDir + "tmpfilepart"  + "." + LocalTime.now().getNano());
//        try (FileWriter outPutFile = new FileWriter(result);
//            BufferedReader br1 = new BufferedReader(new FileReader(file1));
//            BufferedReader br2 = new BufferedReader(new FileReader(file2))) {
//                boolean file1End = false;
//                boolean file2End = false;
//                String fromBr1 = br1.readLine();
//                String fromBr2 = br2.readLine();
//                while (!file1End | !file2End) {
//                    file1End = fromBr1 == null;
//                    file2End = fromBr2 == null;
//                    if (!file1End & !file2End) {
//                        if (Integer.compare(fromBr1.length(), fromBr2.length()) < 1) {
//                            outPutFile.write(fromBr1  + "\n");
//                            fromBr1 = br1.readLine();
//                        } else {
//                            outPutFile.write(fromBr2 + "\n");
//                            fromBr2 = br2.readLine();
//                        }
//                    } else if (file1End & !file2End) {
//                        outPutFile.write(fromBr2  + "\n");
//                        fromBr2 = br2.readLine();
//                    } else if (file2End & !file1End) {
//                        outPutFile.write(fromBr1  + "\n");
//                        fromBr1 = br1.readLine();
//                }
//            }
//            Files.delete(file1.toPath());
//            Files.delete(file2.toPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
    private File getSortedFile(final File file) {
        File result = new File(tmpDir + "tmpfilepart"  + "." + LocalTime.now().getNano());
        try (BufferedReader br = new BufferedReader(new FileReader(file));
            FileWriter fw = new FileWriter(result)) {
            ArrayList<String> arrayLines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                arrayLines.add(line);
            }
            arrayLines.sort(Comparator.comparing(String::length));
            for (String str: arrayLines) {
                fw.write(str  + "\n");
            }
            Files.delete(file.toPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
