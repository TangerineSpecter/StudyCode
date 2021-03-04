package com.tangerine.specter.thread;

public class ThreadTest {

    private static void attack() {
        System.out.println("Fight");
        System.out.println("Current Thread is：" + Thread.currentThread().getName());
    }

    /**
     * 运行结果：
     * Current Thread is：main
     * Fight
     * Current Thread is：main
     * Fight
     * Current Thread is：Thread-0
     */
    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                attack();
            }
        };
        System.out.println("Current Thread is：" + Thread.currentThread().getName());
        t.run();
        t.start();
    }
}
