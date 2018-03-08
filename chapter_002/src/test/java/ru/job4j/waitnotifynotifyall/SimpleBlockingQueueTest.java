package ru.job4j.waitnotifynotifyall;

import org.junit.Before;
import org.junit.Test;

public class SimpleBlockingQueueTest {

    private SimpleBlockingQueue<String> producerNcustomer;
    private Thread producer, customer;

    @Before
    public void setUp() {
        producerNcustomer = new SimpleBlockingQueue<>();
        producer = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 50; i++) {
                        producerNcustomer.offer("someting" + i);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        customer = new Thread() {
            @Override
            public void run() {
                String value;
                try {
                    for (int i = 0; i < 50; i++) {
                        value = producerNcustomer.poll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        };
    }
    @Test
    public void testWorking() {
        producer.start();
        customer.start();
        try {
            producer.join();
            customer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



}