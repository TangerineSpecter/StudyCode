package com.tangerine.specter.thread;

/**
 * sleep和wait代码演示
 */
public class WaitSleepDemo {

    /**
     * wait让出CPU，释放锁，sleep不释放锁
     * <p>
     * 执行结果：
     * Thread A is waiting to get lock
     * Thread A get lock
     * Thread B is waiting to get lock
     * Thread A do wait method
     * Thread B get lock
     * Thread B is sleeping 10 ms
     * Thread B is done
     * Thread A is done
     */
    public static void main(String[] args) {
        final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread A is waiting to get lock");
                synchronized (lock) {
                    try {
                        System.out.println("Thread A get lock");
                        Thread.sleep(20);
                        System.out.println("Thread A do wait method");
                        //如果将此处wait改为sleep，那么只有在A执行完毕，B才能获取到锁继续执行。
                        lock.wait(1000);
                        System.out.println("Thread A is done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        try {
            //保证两个线程的先后执行顺序
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread B is waiting to get lock");
                synchronized (lock) {
                    try {
                        System.out.println("Thread B get lock");
                        System.out.println("Thread B is sleeping 10 ms");
                        Thread.sleep(20);
                        System.out.println("Thread B is done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
