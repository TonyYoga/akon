package ru.job4j.map;

import java.util.Calendar;

public class User {
    String name;
    int children;
    Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
    @Override
    public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + children;
            result = 31 * result + birthday.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (children != user.children) {
            return false;
        }
        if (!name.equals(user.name)) {
            return false;
        }
        return birthday.equals(user.birthday);
    }
}
