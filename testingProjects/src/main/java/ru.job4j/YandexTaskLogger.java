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

    int countBy(long period) {
        int counter = 0;
        Date date = new Date();
        long curTime = date.getTime();
        for (int i = loglist.size() - 1; i >= 0 ; i--) {
            if (loglist.get(i).getDate() > (curTime - period)) {
                counter++;
            }
            else {
                return counter;
            }

        }
        return counter;
    }
}
