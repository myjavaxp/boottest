package com.example.boottest.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(SyncDemo.class);

    public void test1(int j) {
        synchronized (SyncDemo.class) {
            for (int i = 0; i < 10; i++) {
                LOGGER.info("test1:{},{}", j, i);
            }
        }
    }

    public static synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            LOGGER.info("test2:{},{}", j, i);
        }
    }

    public synchronized void test3(int j) {
        for (int i = 0; i < 10; i++) {
            LOGGER.info("test3:{},{}", j, i);
        }
    }
}