package ru.job4j;

import java.util.ArrayList;

public class Generation extends Creature {
     String gName;
     ArrayList<Creature> unit = new ArrayList<>();

    public ArrayList<Creature> getUnit() {
        return unit;
    }

    public void setUnit(ArrayList<Creature> unit) {

        this.unit = unit;
    }



    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {

        this.gName = gName;
    }

    public int avalibleCreatureCount() { // count availble creature in the unit;
        int counter = 0;

        for (Creature c: this.unit) {
            //c.setAvalible();
            if (c.isAvalible()) {
                counter++;
            } //counts only avalible creature did't take action
        }
        return counter;
    }


}
