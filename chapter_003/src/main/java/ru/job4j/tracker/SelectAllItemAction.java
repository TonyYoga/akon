package ru.job4j.tracker;

import java.util.ArrayList;

public class SelectAllItemAction implements Action {
    private String name = "Show All Items";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean execute(Tracker trk, Input input) {
        ArrayList<Item> items = trk.getAll();
        for (Item i : items) {
            input.print(i);
        }

        return true;
    }
}
