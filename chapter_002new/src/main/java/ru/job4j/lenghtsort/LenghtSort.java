package ru.job4j.lenghtsort;

import java.io.*;
import java.util.*;

public class LenghtSort implements ILenghtSort {
    @Override
    public void sort(final File source, File distance) {

        Iterable<LineLenghtWithPos> sortedList = getAndSort(source);
        try (RandomAccessFile sourceFile = new RandomAccessFile(source, "r");
                FileWriter fileWriter = new FileWriter(distance)) {
            for (LineLenghtWithPos entry: sortedList) {
                sourceFile.seek(entry.getPos());
                fileWriter.write(sourceFile.readLine() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Iterable<LineLenghtWithPos> getAndSort(final File source) {
        ArrayList<LineLenghtWithPos> strList = new ArrayList<>();
        TreeMap<Integer, Long> stringList = new TreeMap<>();
        long lineCounter = 0;
        try (RandomAccessFile sourceFile = new RandomAccessFile(source, "r")) {
            String line;
            while ((line = sourceFile.readLine()) != null) {
                strList.add(new LineLenghtWithPos(line.length(), lineCounter));
                //stringList.put(line.length(), lineCounter);
                lineCounter = sourceFile.getFilePointer();
            }
            strList.sort(Comparator.comparing(LineLenghtWithPos::getLineLenght));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strList;
    }

    private class LineLenghtWithPos {
        private int lineLenght;
        private long pos;

        public LineLenghtWithPos(int lineLenght, long pos) {
            this.lineLenght = lineLenght;
            this.pos = pos;
        }

        public int getLineLenght() {
            return lineLenght;
        }

        public long getPos() {
            return pos;
        }


//        @Override
//        public int compare(LineLenghtWithPos o1, LineLenghtWithPos o2) {
//            return Integer.compare(o1.lineLenght, o2.lineLenght);
//        }
    }
}
