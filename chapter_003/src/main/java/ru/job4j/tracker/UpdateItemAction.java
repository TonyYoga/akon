package ru.job4j.tracker;


public class UpdateItemAction implements Action {
    private String name = "Update Item";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean execute(Tracker trk, Input input)   {
        trk.update(input.ask());
        System.out.println(getName() + " done");
        return false;
    }

}
