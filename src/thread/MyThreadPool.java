package thread;

import java.util.concurrent.*;

/**
 * 线程池代码演示
 */
public class MyThreadPool implements Callable<String> {
    @Override
    public String call() throws Exception {
        String value = "test";
        System.out.println("Ready to work");
        Thread.currentThread().sleep(5000);
        System.out.println("task done");
        return value;
    }

    /**
     * 打印结果：
     * task has not finished, please wait!
     * Ready to work
     * task done
     * task return：test
     * <p>
     * 好处：可以提交多个实现callable的类，并发的去处理结果，方便管理
     */
    public static void main(String[] args) {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        Future<String> future = newCachedThreadPool.submit(new MyThreadPool());
        if (!future.isDone()) {
            System.out.println("task has not finished, please wait!");
        }
        try {
            System.out.println("task return：" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            //线程池一定要关闭
            newCachedThreadPool.shutdown();
        }
    }
}
