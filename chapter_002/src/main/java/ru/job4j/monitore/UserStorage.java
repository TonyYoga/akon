package ru.job4j.monitore;


import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;

@ThreadSafe
public class UserStorage {
    private final ArrayList<User> users;

    public UserStorage() {
        this.users = new ArrayList<>();
    }


    boolean add(User user) {
        for (User u : users) {
            if (u.getId() == user.getId()) {
                return false;
            }
        }
        users.add(user);
        return true;
    }

    boolean update(User user) {
        for (User u : users) {
            if (u.getId() == user.getId()) {
                u.setAmount(user.getAmount());
                return true;
            }
        }
        return false;
    }

    boolean delete(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    boolean transfer(int id, int toid, int amount) {
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
