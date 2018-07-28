package ru.job4j.tracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class UpdateItemAction implements Action {
    private String name = "Create Item";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean execute(Tracker trk, Input input) {
        trk.update(input.ask());
        System.out.println(getName() + "done");
        return false;
    }

}
