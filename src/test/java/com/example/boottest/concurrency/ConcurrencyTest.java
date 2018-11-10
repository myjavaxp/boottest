package com.example.boottest.concurrency;

import com.example.boottest.annotation.NotThreadSafe;
import com.example.boottest.annotation.ThreadSafe;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@NotThreadSafe
public class ConcurrencyTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrencyTest.class);
    private static final int CLIENT_TOTAL = 5000;
    private static final int THREAD_TOTAL = 200;
    private static int count = 0;
    private static AtomicInteger atomicCount = new AtomicInteger(0);

    @Test
    @NotThreadSafe
    public void test01() {
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

    @Test
    @ThreadSafe
    public void test02() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(THREAD_TOTAL);
        final CountDownLatch countDownLatch = new CountDownLatch(CLIENT_TOTAL);
        for (int i = 0; i < CLIENT_TOTAL; i++) {
            threadPool.execute(() -> {
                try {
                    semaphore.acquire();
                    /*
                    调用链---
                    public final int incrementAndGet() {
                        return unsafe.getAndAddInt(this, valueOffset, 1) + 1;
                    }
                    //var1 原始对象，var2，当前线程的对象初始值，var4，需要加的值
                    public final int getAndAddInt(Object var1, long var2, int var4) {
                        int var5;
                        do {
                            var5 = this.getIntVolatile(var1, var2);
                        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
                        return var5;
                    }
                     */
                    atomicCount.incrementAndGet();
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
        LOGGER.info("atomicCount:{}", atomicCount.get());
    }
}