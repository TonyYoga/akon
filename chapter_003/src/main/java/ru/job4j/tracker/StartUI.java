package ru.job4j.tracker;


import java.io.IOException;


public class StartUI {
    boolean endOfWork = false;

    void init() {



        Input input = new Input();


        try (Tracker tracker = new Tracker("chapter_003/src/main/resources/config.properties")) {



            MenuTracker menu = new MenuTracker();

            String inputKey;
            while (!endOfWork) {
                System.out.println("For exit type \"Q\"");
                System.out.println(menu.toString());

                try {
                    inputKey = input.getBr().readLine();
                    if (inputKey.equals("Q")) {
                        endOfWork = true;
                    } else if (menu.getMenu().containsKey(inputKey)) {

                        //working module if we have right answer
                        menu.getMenu().get(inputKey).execute(tracker, input);
                    } else {
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


