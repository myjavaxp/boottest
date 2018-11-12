package com.example.boottest.concurrency;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
    private static CyclicBarrier barrier = new CyclicBarrier(9);
    private static final Logger LOGGER = LoggerFactory.getLogger(CyclicBarrierTest.class);

    @Test
    public void test01() throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000L);
            service.execute(() -> {
                try {
                    race(threadNum);
                } catch (BrokenBarrierException | InterruptedException e) {
                    LOGGER.error("exception", e);
                }
            });
        }
        service.shutdown();
    }

    private void race(int threadNum) throws BrokenBarrierException, InterruptedException {
        Thread.sleep(100L);
        LOGGER.info("{} is ready", threadNum);
        barrier.await();
        LOGGER.info("{} continue", threadNum);
    }
}
