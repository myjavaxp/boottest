package com.example.boottest.thread;

import com.example.boottest.annotation.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NotThreadSafe
public class Escape {
    private static final Logger LOGGER = LoggerFactory.getLogger(Escape.class);
    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {
        private InnerClass() {
            LOGGER.info("{}", Escape.this.thisCanBeEscape);
        }
    }
}
