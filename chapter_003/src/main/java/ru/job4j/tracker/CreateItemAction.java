package ru.job4j.tracker;


public class CreateItemAction implements Action {
    private String name = "Create Item";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean execute(Tracker trk, Input input) {
        trk.add(input.ask());
        System.out.println(getName() + " done");
        return true;
    }

}
