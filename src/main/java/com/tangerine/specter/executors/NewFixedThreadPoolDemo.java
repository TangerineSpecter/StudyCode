package com.tangerine.specter.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定数量线程池演示
 */
public class NewFixedThreadPoolDemo {

    public static class TaskA implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("AAA");
        }
    }

    public static class TaskB implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("BBB");
        }
    }


    public static class TaskC implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("CCC");
        }
    }

    /**
     * 初始化时需要确定线程数量，执行任务时从线程池中拿，
     * 假如执行的任务数比较多，达到了线程初始化时数量，则会缓存到一个无界（即没有长度限制）的阻塞队列中。
     */
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(new Thread(new NewFixedThreadPoolDemo.TaskA()));
        executor.execute(new Thread(new NewFixedThreadPoolDemo.TaskB()));
        executor.execute(new Thread(new NewFixedThreadPoolDemo.TaskC()));
    }
}
