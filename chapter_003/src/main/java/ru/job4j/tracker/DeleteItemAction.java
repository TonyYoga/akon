package ru.job4j.tracker;

public class DeleteItemAction implements Action {
    private String name = "Delete Item";

    @Override
    public String getName() {
        return name;
    }
    @Override
    public boolean execute(Tracker trk, Input input) {
        System.out.println("Type only ID, other fields you can leave blank ");
        trk.delete(input.ask().getId());
        System.out.println(getName() + " done");
        return false;
    }
}
