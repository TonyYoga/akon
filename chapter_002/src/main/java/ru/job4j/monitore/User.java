package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;

public class User {

    private final int id;

    private volatile int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

     int getId() {
        return id;
    }


     int getAmount() {
        return amount;
    }

     void setAmount(int amount) {
        this.amount = amount;
    }


}
