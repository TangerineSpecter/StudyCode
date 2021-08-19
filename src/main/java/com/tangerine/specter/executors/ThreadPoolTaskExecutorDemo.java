package com.tangerine.specter.executors;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 手动创建线程池演示
 */
public class ThreadPoolTaskExecutorDemo {

    public static class TaskA implements Runnable {
        private String name;

        TaskA(String name) {
            this.name = name;
        }

        private static int count = 0;

        @Override
        public void run() {
            try {
                System.out.println("------------------------------");
                System.out.println(this.name + "-运行中");
                int queueSize = poolTaskExecutor.getQueue().size();
                System.out.println("当前排队线程数：" + queueSize);

                int activeCount = poolTaskExecutor.getActiveCount();
                System.out.println("当前活动线程数：" + activeCount);

                long completedTaskCount = poolTaskExecutor.getCompletedTaskCount();
                System.out.println("执行完成线程数：" + completedTaskCount);

                long taskCount = poolTaskExecutor.getTaskCount();
                System.out.println("总线程数：" + taskCount);
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static ThreadPoolExecutor poolTaskExecutor = new ThreadPoolExecutor(3,
            5,
            0,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(5),
            new ThreadFactory() {
                private final AtomicInteger mCount = new AtomicInteger(1);

                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "AsyncTask#" + mCount.getAndIncrement());
                }
            },
            new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 参数说明
     * corePoolSize：核心线程数，即线程池长期存活的线程数量。
     * maximumPoolSize：最大线程数，此参数可以有效的控制线程数，避免无限制的创建线程导致 OOM，最大线程数是常驻+临时线程数量的总和。
     * keepAliveTime：线程的空闲时间，在超过核心线程数情况下，并且队列满了情况下，而被创建出来的线程的存活时间，线程在没有执行任务的情况下超过这个时间就会被销毁。
     * unit：空闲时间的单位，比如毫秒、秒、分钟和小时等等。
     * workQueue：等待队列，在超过核心线程数情况下，任务将放在等待队列，它是一个 BlockingQueue 阻塞队列。
     * threadFactory：线程工厂，用于创建一个线程，一般用于设置线程的名称。
     * handler：拒绝策略，当核心线满了、队列满了，超过了最大线程数情况下的处理策略。
     * <p>
     * ------------------------------------------------------------------------------
     * <p>
     * workQueue队列说明：
     * SynchronousQueue：这个队列接收到任务的时候，会直接提交给线程处理，而不保留它，如果所有线程都在工作怎么办？那就新建一个线程来处理这个任务！所以为了保证不出现<线程数达到了maximumPoolSize而不能新建线程>的错误，使用这个类型队列的时候，maximumPoolSize 一般指定成 Integer.MAX_VALUE，即无限大
     * <p>
     * LinkedBlockingQueue：这个队列接收到任务的时候，如果当前线程数小于核心线程数，则新建线程(核心线程)处理任务；如果当前线程数等于核心线程数，则进入队列等待。由于这个队列没有最大值限制，即所有超过核心线程数的任务都将被添加到队列中，这也就导致了 maximumPoolSize 的设定失效，因为总线程数永远不会超过 corePoolSize
     * <p>
     * ArrayBlockingQueue：可以限定队列的长度，接收到任务的时候，如果没有达到 corePoolSize 的值，则新建线程(核心线程)执行任务，如果达到了，则入队等候，如果队列已满，则新建线程 (非核心线程) 执行任务，又如果总线程数到了 maximumPoolSize，并且队列也满了，则发生错误
     * <p>
     * DelayQueue：队列内元素必须实现 Delayed 接口，这就意味着你传进去的任务必须先实现 Delayed 接口。这个队列接收到任务时，首先先入队，只有达到了指定的延时时间，才会执行任务
     * ------------------------------------------------------------------------------
     * <p>
     * handler策略说明：
     * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常
     * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常
     * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）。
     * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
     * ------------------------------------------------------------------------------
     */
    public static void main(String[] args) throws InterruptedException {
        // 设置线程池的拒绝策略为"在调用者线程执行，既不丢弃所有任务"
        //poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        Long startTime = System.currentTimeMillis();
        CompletableFuture[] completableFutures = new CompletableFuture[10];
        for (int i = 0; i < 10; i++) {
            Runnable r = new TaskA("任务-" + i);
            completableFutures[i] = CompletableFuture.runAsync(r, poolTaskExecutor);
        }
//        poolTaskExecutor.shutdown();
        Long endTime = System.currentTimeMillis();
        CompletableFuture.allOf(completableFutures).join();
        System.out.println("-----------------------处理完毕，耗时：" + (endTime - startTime));
    }
}
