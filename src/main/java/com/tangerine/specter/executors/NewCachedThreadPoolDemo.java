package com.tangerine.specter.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可缓存线程空闲 60 秒的无界线程池演示
 */
public class NewCachedThreadPoolDemo {

    public static class ThreadA implements Runnable {
        @Override
        public void run() {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
        }
    }

    /**
     * 创建一个可缓存线程空闲 60 秒的无界线程池，
     * 使用 SynchronousQueue 作为阻塞队列，这个队列比较特殊，只能存储一个元素。
     * 可缓存其实就是线程执行完任务后不立即销毁线程，而是在 60 秒内无需执行任务的情况下才销毁，
     * 适合处理大量耗时较短的任务场景。
     */
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            executor.execute(threadA);
        }
        executor.shutdown();
    }
}
