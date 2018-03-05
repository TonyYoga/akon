package ru.job4j.nonblockingalg;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class NonBlockingTest {
    NonBlocking nb;
    NonBlocking.Model mod1, mod2, mod3;

    @Before
    public void setUp() {
        nb = new NonBlocking();
        mod1 = new NonBlocking.Model("first");
        mod2 = new NonBlocking.Model("second");
        mod3 = new NonBlocking.Model("third");
        assertThat(nb.add(mod1), is(true));
        assertThat(nb.add(mod2), is(true));
        assertThat(nb.add(mod3), is(true));

    }
    @Test
    public void add() throws Exception {

        assertThat(nb.add(new NonBlocking.Model("fourth")), is(true));

    }

    @Test //(expected = NonBlocking.OplimisticException.class)
    public void update() {
        NonBlocking.Model mod1upd = mod1;
        NonBlocking.Model mod2upd = mod2;
        mod2upd.setName("first1upd");
        new Thread() {
            @Override
            public void run() {
                try {
                    assertThat(nb.update(0, mod1upd), is(true));
                    nb.update(1, mod2upd);
                } catch (NonBlocking.OplimisticException e) {
                    e.printStackTrace();
                }


            }
        }.start();


    }

    @Test
    public void delete() throws Exception {
        assertThat(nb.delete(2), is(true));

    }

}