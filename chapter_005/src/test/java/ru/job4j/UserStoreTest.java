package ru.job4j;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void whenUserAddTestAndCheck() {
        UserStore us = new UserStore();
        User user;
        for (int i = 0; i < 10; i++) {
            user = new User("Test" + i);
            user.setId("" + i);
            us.add(user);
        }
        user = new User("Test10"); // additional
        user.setId("10");
        us.add(user); //расширение массива
        User upduser = new User("UpdTest");
        upduser.setId("5");
        assertThat(us.update(upduser).getName(), is("Test5")); // return oldelement
        upduser = new User("None");
        upduser.setId("a1");
        assertThat(us.update(upduser).getName(), is("None"));
        assertThat(us.delete("9"), is(true));
        assertThat(us.delete("b1"), is(false));
    }

}