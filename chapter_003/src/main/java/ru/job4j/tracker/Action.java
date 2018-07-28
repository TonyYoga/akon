package ru.job4j.tracker;

interface Action  {
    public String name = null;
    String getName();
    boolean execute(Tracker trk, Input input);

}