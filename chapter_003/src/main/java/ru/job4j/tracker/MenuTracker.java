package ru.job4j.tracker;


import java.util.HashMap;
import java.util.Map;

public class MenuTracker {


   final Map<String, Action> menu = new HashMap<String, Action>();

    public MenuTracker() {
        menu.put("0", new CreateItemAction());
        menu.put("1", new DeleteItemAction());
        menu.put("2", new UpdateItemAction());
    }

    public Map<String, Action> getMenu() {
        return menu;
    }

    @Override
    public String toString() {
        StringBuilder menuStr = new StringBuilder();
        //int countMenu = 0;

        for ( Map.Entry<String, Action> m : menu.entrySet()) {
            menuStr.append("Type \"" + m.getKey()+ "\" for " + m.getValue().getName() +"\n");
        }

        return menuStr.toString();
    }
}
