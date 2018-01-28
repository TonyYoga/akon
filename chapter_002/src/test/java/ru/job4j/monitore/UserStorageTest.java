package ru.job4j.monitore;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    UserStorage storage = new UserStorage();
    @Test
    public void whenAddSameThenFalse() throws Exception {
        storage.add(new User(1, 100));
        storage.add(new User(2, 200));
        storage.transfer(1, 2, 50);
        assertThat(storage.add(new User(1, 100)), is(false));
    }

}