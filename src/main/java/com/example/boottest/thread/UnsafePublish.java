package com.example.boottest.thread;

/**
 * 发布对象：使一个对象能够被当前范围之外的代码所使用
 * 对象逸出：一种错误的发布，当一个对象还没有构造完成时，就使它被其它线程所见
 */
public class UnsafePublish {
    private String[] array = new String[]{"a", "b", "c"};

    public String[] getArray() {
        return array;
    }
}