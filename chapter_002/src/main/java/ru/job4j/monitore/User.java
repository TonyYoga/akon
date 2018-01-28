package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;

public class User {
    @GuardedBy("this")
    private int id;
    @GuardedBy("this")
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    synchronized int getId() {
        return id;
    }


    synchronized int getAmount() {
        return amount;
    }

    synchronized void setAmount(int amount) {
        this.amount = amount;
    }


}
