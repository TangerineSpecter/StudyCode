package com.tangerine.specter.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程线程池演示
 */
public class NewSingleThreadExecutorDemo {

    public static class TaskA implements Runnable {
        @Override
        public void run() {
            System.out.println("AAA");
        }
    }

    public static class TaskB implements Runnable {
        @Override
        public void run() {
            System.out.println("BBB");
        }
    }

    public static class TaskC implements Runnable {
        @Override
        public void run() {
            System.out.println("CCC");
        }
    }

    /**
     * 使用单线程串行执行所有任务，
     * 适合需要顺序执行任务的场景。
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Thread(new TaskA()));
        executor.execute(new Thread(new TaskB()));
        executor.execute(new Thread(new TaskC()));
    }
}
