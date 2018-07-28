package ru.job4j.tracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class Input {



    public Item ask() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Item item = new Item();
        try {
            System.out.print("Type id: ");
            item.setId(br.readLine());
            System.out.print("Type name: ");
            item.setName(br.readLine());
            String[] comments = new String[3];
            int indx = 0;
            String key;
            System.out.print("Type comment or \"exit\" (max 3 rows): ");
            while (!(key = br.readLine()).equals("exit") && indx < 2) {
                comments[indx] = key;
                indx++;
            }
            item.setComments(comments);
            item.setCreate((new Date()).getTime());
            System.out.print("Type description: ");
            item.setDesk(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }
    void print(String data) {

    }
}
