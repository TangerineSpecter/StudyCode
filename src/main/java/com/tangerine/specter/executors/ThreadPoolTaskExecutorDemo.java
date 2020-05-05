package com.tangerine.specter.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 手动创建线程池演示
 */
public class ThreadPoolTaskExecutorDemo {

    public static class TaskA implements Runnable {
        private String name;

        public TaskA(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.name + "-运行中");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 参数说明
     * corePoolSize：核心线程数，即线程池长期存活的线程数量。
     * maximumPoolSize：最大线程数，此参数可以有效的控制线程数，避免无限制的创建线程导致 OOM，最大线程数是常驻+临时线程数量的总和。
     * keepAliveTime：线程的空闲时间，在超过核心线程数情况下，并且队列满了情况下，而被创建出来的线程的存活时间，线程在没有执行任务的情况下超过这个时间就会被销毁。
     * unit：空闲时间的单位，比如毫秒、秒、分钟和小时等等。
     * workQueue：等待队列，在超过核心线程数情况下，任务将放在等待队列，它是一个 BlockingQueue 阻塞队列。
     * threadFactory：线程工厂，用于创建一个线程，一般用于设置线程的名称。
     * handler：拒绝策略，当核心线满了、队列满了，超过了最大线程数情况下的处理策略。
     */
    public static void main(String[] args) {
        ThreadPoolExecutor poolTaskExecutor = new ThreadPoolExecutor(3, 5, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(50));
        // 设置线程池的拒绝策略为"在调用者线程执行，既不丢弃所有任务"
        poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 10; i++) {
            Runnable r = new TaskA("任务-" + i);
            poolTaskExecutor.execute(r);
        }
        poolTaskExecutor.shutdown();
    }
}
