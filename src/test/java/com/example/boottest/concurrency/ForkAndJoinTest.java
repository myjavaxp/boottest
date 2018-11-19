package com.example.boottest.concurrency;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 另外需要学习一下阻塞队列
 *
 * @author Yibo
 */
public class ForkAndJoinTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ForkAndJoinTest.class);

    private class Example extends RecursiveTask<Integer> {
        private static final long serialVersionUID = 3065394751260895867L;
        private static final int HOLD = 2;
        private int start;
        private int end;

        private Example(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            boolean canCompute = (end - start) <= HOLD;
            if (canCompute) {
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                int middle = (start + end) / 2;
                Example left = new Example(start, middle);
                Example right = new Example(middle + 1, end);
                left.fork();
                right.fork();
                sum = left.join() + right.join();
            }
            return sum;
        }
    }

    @Test
    public void test() {
        ForkJoinPool pool = new ForkJoinPool();
        Example example = new Example(1, 10000);
        Future<Integer> result = pool.submit(example);
        try {
            LOGGER.info("result:{}", result.get());
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("exception", e);
        }
    }
}