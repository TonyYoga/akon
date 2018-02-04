package ru.job4j.monitore;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;

@ThreadSafe
class UserStorage {
    @GuardedBy("this")
    private ArrayList<User> users;

    UserStorage() {
        this.users = new ArrayList<>();
    }


    synchronized boolean add(User user) {

        for (User u : users) {
            if (u.getId() == user.getId()) {
                return false;
            }
        }
        users.add(user);

        return true;
    }

    synchronized boolean update(User user) {

        for (User u : users) {
            if (u.getId() == user.getId()) {
                u.setAmount(user.getAmount());
                return true;
            }
        }

        return false;
    }

    synchronized boolean delete(User user) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.remove(i);
                return true;
            }
        }

        return false;
    }

    synchronized boolean transfer(int id, int toid, int amount) {

        User fromUser = null, toUser = null;
        for (User u : users) {
            if (u.getId() == id) {
                fromUser = u;
            }
            if (u.getId() == toid) {
                toUser = u;
            }
        }
        if (fromUser != null && toUser != null) {
            if (fromUser.getAmount() <= amount) {
                toUser.setAmount(toUser.getAmount() + fromUser.getAmount());
                fromUser.setAmount(0);
                return true;
            }
            toUser.setAmount(toUser.getAmount() + amount);
            fromUser.setAmount(fromUser.getAmount() - amount);
            return true;
        }

        return false;
    }
}
