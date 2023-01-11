package com.tangerine.specter.thread;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 使用CompletableFuture遍历执行，多线程之间异常互不影响
 * <p>
 * 实现效果：
 * 如果子线程出现异常，则抛出异常「 无需继续执行 」
 */
public class CompletableFutureLoopDemo {


    private static final ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("my-pool-%d").build();
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
    private static final List<Integer> resultList = Collections.synchronizedList(new ArrayList<>());
    private static final List<CompletableFuture<Integer>> futureList = new ArrayList<>();


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
//            doSomething(executor, resultList, futureList);
            doSomething2(executor, resultList, futureList);
        } catch (Exception e) {
            System.out.println("main 处理了异常 -> " + e.getMessage());
        }
        System.out.println("方法执行时间: " + (System.currentTimeMillis() - start));
        System.out.println("main 执行完了...");
        executor.shutdown();
    }

    /**
     * 遍历进行子线程调度
     * 第四个子线程抛出异常，每个子线程休眠0~9秒
     * 执行结果：即便是异常，也全部执行完毕，耗时9秒
     */
    private static void doSomething(ThreadPoolExecutor executor, List<Integer> resultList, List<CompletableFuture<Integer>> futureList) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    //根据线程数进行休眠
                    Thread.sleep(finalI * 1000);
                    System.out.println("线程" + finalI + "执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (finalI == 4) {
                    System.out.println("线程" + finalI + "业务异常... -> " + finalI);
                    throw new RuntimeException("业务异常... -> " + finalI);
                }
                return finalI;
            }, executor).handle((result, e) -> {
                if (e != null) {
                    System.out.println("CompletableFuture处理异常 -> " + e.getMessage());
                    throw new RuntimeException("CompletableFuture处理异常 -> " + e.getMessage());
                }
                resultList.add(result);
                return result;
            });
            futureList.add(future);
        }

        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();
        System.out.println(resultList.size());
        System.out.println(resultList);
    }

    /**
     * 遍历进行子线程调度
     * 第四个子线程抛出异常，每个子线程休眠0~9秒
     * 执行结果：某一线程异常直接返回，耗时4秒
     * 通过isCompletedExceptionally判断任务是否异常结束
     */
    private static void doSomething2(ThreadPoolExecutor executor, List<Integer> resultList, List<CompletableFuture<Integer>> futureList) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    final int time = finalI * 1000;
                    Thread.sleep(time);
                    System.out.println("线程" + finalI + "执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (finalI == 4) {
                    System.out.println("线程" + finalI + "业务异常... -> " + finalI);
                    throw new RuntimeException("业务异常... -> " + finalI);
                }
                return finalI;
            }, executor).handle((result, e) -> {
                if (e != null) {
                    sb.append("CompletableFuture处理异常 -> " + e.getMessage());
                    throw new RuntimeException("CompletableFuture处理异常 -> " + e.getMessage());
                }
                resultList.add(result);
                return result;
            });
            futureList.add(future);
        }

        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));
        //判断是否异常
        if (completableFuture.isCompletedExceptionally()) {
            throw new RuntimeException(sb.toString());
        } else {
            resultList.addAll(futureList.stream().map(CompletableFuture::join).collect(Collectors.toList()));
        }
        System.out.println(resultList);
    }
}
