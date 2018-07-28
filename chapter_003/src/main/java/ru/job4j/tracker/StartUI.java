package ru.job4j.tracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartUI {
    boolean endOfWork = false;

    void init() {

        //Input input = new Input();


        try (Tracker tracker = new Tracker("chapter_003/src/main/resources/config.properties")) {
            //try (Tracker tracker = new Tracker("/home/user/projects/akon/chapter_003/src/main/resources/config.properties")) {


            MenuTracker menu = new MenuTracker();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String inputKey;
            while (!endOfWork == true) {
                System.out.println("For exit type \"Q\"");
                System.out.println(menu.toString());

                try {
                    inputKey = br.readLine();
                    if (inputKey.equals("Q")) {
                        endOfWork = true;
                    }
                    else if (menu.getMenu().containsKey(inputKey) ){

                        //working module if we have right answer
                        menu.getMenu().get(inputKey).execute(tracker, new Input());
                    }
                    else {
                        System.out.println("Wrong key, plz retype");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("End of work, Thank you!");

    }

    public static void main(String[] args) {
        StartUI ui = new StartUI();
        ui.init();
    }
}


