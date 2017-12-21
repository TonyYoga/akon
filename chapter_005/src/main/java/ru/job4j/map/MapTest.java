package ru.job4j.map;

import java.util.Calendar;

public class MapTest {
    public static final class User {
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
            int result = 10;
//            int result = name.hashCode();
//            result = 31 * result + children;
//            result = 31 * result + birthday.hashCode();
            return result;
        }
    }
    @Override
    public int hashCode() {
        int result = 10;
//            int result = name.hashCode();
//            result = 31 * result + children;
//            result = 31 * result + birthday.hashCode();
        return result;
    }


}
