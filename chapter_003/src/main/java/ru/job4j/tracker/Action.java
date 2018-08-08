package ru.job4j.tracker;

interface Action  {
    String getName();
    boolean execute(Tracker trk, Input input);

}