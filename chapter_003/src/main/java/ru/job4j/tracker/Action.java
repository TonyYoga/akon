package ru.job4j.tracker;

interface Action  {
    public String name = null;
    String getName();
    boolean execute(Tracker trk, Input input);




//    public boolean createItemAction() {
//        return true;
//    }
//
//    public boolean updateItemAction() {
//        return true;
//    }
//
//    public boolean deleteItemAction() {
//        return true;
//    }
}