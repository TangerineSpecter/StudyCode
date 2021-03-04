package com.tangerine.specter.executors;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池提交任务演示
 */
public class SubmitDemo {

    /**
     * submit() 在 ExecutorService 接口中声明，
     * 具体实现在 AbstractExecutorService 抽象类中，
     * 此方法作用也是提交任务到线程池，
     * 但是与 execute() 方法有些不同，submit() 可以使用 Future 来获取任务执行结果，
     * 但是最终还是调用 execute() 提交任务（所以此方法是 ThreadPoolExecutor 最核心）。
     * 需要返回任务结果的使用这种方式提交，获取结果 get() 是阻塞方法，配合 CountDownLatch 使用可以避免阻塞
     * @param args
     */
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10,
                10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(50), Executors.defaultThreadFactory()
                , new ThreadPoolExecutor.CallerRunsPolicy());
        List<Future<Result>> list = new ArrayList<Future<Result>>(10);
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 1; i <= 10; i++) {
            Result result = new Result();
            result.setIndex(i);
            MyCallable callable = new MyCallable(result, latch);
            Future<Result> resultFuture = threadPool.submit(callable);
            list.add(resultFuture);
        }
        try {
            latch.await();
            for (Future<Result> resultFuture : list) {
                Result result = resultFuture.get();
                System.out.println(new Gson().toJson(result));
            }
            threadPool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class MyCallable implements Callable<Result> {
        private Result result;
        private CountDownLatch latch;

        @Override
        public Result call() throws Exception {
            String name = Thread.currentThread().getName();
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
            return result;
        }

        public MyCallable(Result result, CountDownLatch main) {
            super();
            this.result = result;
            this.latch = main;
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
