package ru.job4j;

import java.util.ArrayList;
import java.util.Date;

public class YandexTaskLogger {
    ArrayList<Event> loglist = new ArrayList<>();

    class Event {
        private long date;
        private String note;

        Event(String note) {
            this.date = new Date().getTime();
            this.note = note;
        }

        long getDate() {
            return date;
        }


        String getNote() {
            return note;
        }

        void setNote(String note) {
            this.note = note;
        }
    }

    void add (String note) {

        loglist.add(new Event(note));

    }

    int countBy(long period, long curTime) {
        int counter = 0;
        long timeLabel = curTime - period;
        for (int i = loglist.size() - 1; i >= 0 ; i--) {
            if (loglist.get(i).getDate() > timeLabel) {
                counter++;
            }
            else {
                return counter;
            }

        }
        return counter;
    }

    int binsearch(long period, long curTime) {
        long timeLabel = curTime - period;
        int indStart = 0;
        int indEnd = loglist.size() - 1;
        int size = loglist.size();
        int indMidle = 0;
        while(size > 1) {
            indMidle = indStart + (indEnd - indStart) / 2;
            if (loglist.get(indMidle).getDate() == timeLabel) {
                return loglist.size() - 1 - indMidle;
            } else if (loglist.get(indMidle).getDate() < timeLabel) {
                indStart = indMidle;
            } else if (loglist.get(indMidle).getDate() > timeLabel) {
                indEnd = indMidle;
            }
            size = indEnd - indStart;

        }

        return loglist.size() - 1 - indStart;
    }
}
