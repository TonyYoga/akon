package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MapTestTest {

    @Test
    public void test01() {
        Map<User, Object> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        User first = new User("First", 1, calendar);
        User second = new User("First", 1, calendar);
        System.out.println("hashCods:" + first.hashCode() + "  " + second.hashCode());
        System.out.println("equals:" + first.equals(second));
        map.put(first, "first");
        map.put(second, "second");
        System.out.println(map);

    }

}