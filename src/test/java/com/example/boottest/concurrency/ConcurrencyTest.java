package com.example.boottest.concurrency;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ConcurrencyTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrencyTest.class);
    private static final int CLIENT_TOTAL = 5000;
    private static final int THREAD_TOTAL = 200;
    private static int count = 0;

    @Test
    public void test01(){
        ExecutorService threadPool = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(THREAD_TOTAL);
        final CountDownLatch countDownLatch = new CountDownLatch(CLIENT_TOTAL);
        for (int i = 0; i < CLIENT_TOTAL; i++) {
            threadPool.execute(() -> {
                try {
                    semaphore.acquire();
                    count++;
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
        LOGGER.info("count:{}", count);
    }
}