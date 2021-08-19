package com.tangerine.specter.executors;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池提交任务演示
 */
public class ExecuteDemo {

    /**
     * execute() 在 Executor 接口类中声明，具体实现方法在 ThreadPoolExecutor 类，
     * 此方法时 ThreadPoolExecutor 类最核心的方法，作用是提交任务到线程池，
     * 由线程池来分配哪个线程去执行任务。
     * 不需要返回任务结果的使用这种方式提交，任务执行出错会抛异常。
     */
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,
                10L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(50), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        List<Result> list = new ArrayList<Result>(10);
        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            Result result = new Result();
            result.setIndex(i);
            list.add(result);
            MyRunnable runnable = new MyRunnable(result, latch);
            threadPoolExecutor.execute(runnable);
        }
        try {
            latch.await();
            System.out.println(new Gson().toJson(list));
            threadPoolExecutor.shutdown();
        } catch (Exception e) {

        }
    }

    public static class MyRunnable implements Runnable {
        private Result result;
        private CountDownLatch latch;

        @Override
        public void run() {
            String name = Thread.currentThread().getName() + "：";
            System.out.println(name + "start" + "-" + result.getIndex());
            try {
                System.out.println(name + "running" + "-" + result.getIndex());
                Thread.sleep(1000);
                result.setResult(result.getIndex() * 10);
                result.setSuccess(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
                result.setSuccess(false);
            }
            System.out.println(name + "end" + "-" + result.getIndex());
            latch.countDown();
        }

        public MyRunnable(Result result, CountDownLatch latch) {
            super();
            this.result = result;
            this.latch = latch;
        }
    }

    public static class Result {
        private int index;
        private Boolean success;
        private int result;

        public int getIndex() {
            return index;
        }

        public Boolean getSuccess() {
            return success;
        }

        public int getResult() {
            return result;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public void setResult(int result) {
            this.result = result;
        }
    }
}
