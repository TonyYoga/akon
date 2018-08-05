package ru.job4j.tracker;

interface Action  {
    //String name = null;
    String getName();
    boolean execute(Tracker trk, Input input);

}