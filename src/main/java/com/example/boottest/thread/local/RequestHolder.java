package com.example.boottest.thread.local;

public class RequestHolder {
    private static final ThreadLocal<Long> REQUEST_HOLDER = new ThreadLocal<>();

    /**
     * 接口处理之前写入
     *
     * @param id 用户ID或者其他
     */
    public static void add(long id) {
        REQUEST_HOLDER.set(id);
    }

    public static long getId() {
        return REQUEST_HOLDER.get();
    }

    /**
     * 接口处理之后移除
     */
    public static void remove() {
        REQUEST_HOLDER.remove();
    }
}