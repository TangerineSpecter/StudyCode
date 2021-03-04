package com.tangerine.specter.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁演示
 */
public class DeadLockDemo {

    /**
     * 定义锁对象
     */
    static final Lock LOCK1 = new ReentrantLock();
    static final Lock LOCK2 = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        new Thread(new DeadLock(true), "线程1").start();
        Thread.sleep(1);
        new Thread(new DeadLock(false), "线程2").start();
    }
}

/**
 * 模拟死锁
 */
class DeadLock implements Runnable {
    private boolean flag;

    @Override
    public void run() {
        if (flag) {
            while (true) {
                synchronized (DeadLockDemo.LOCK1) {
                    System.out.println(Thread.currentThread().getName() + "获得LOCK1锁");
                    synchronized (DeadLockDemo.LOCK2) {
                        System.out.println(Thread.currentThread().getName() + "获得LOCK2锁");
                    }
                }
            }
        } else {
            while (true) {
                synchronized (DeadLockDemo.LOCK2) {
                    System.out.println(Thread.currentThread().getName() + "获得LOCK2锁---");
                    synchronized (DeadLockDemo.LOCK1) {
                        System.out.println(Thread.currentThread().getName() + "获得LOCK1锁---");
                    }
                }
            }
        }
    }

    DeadLock(boolean flag) {
        this.flag = flag;
    }
}