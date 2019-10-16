package thread;

public class YieldDemo {

    /**
     * yield对锁行为没影响
     * 多次测试结果显示，B的打印结果不一定是在A5之前执行的
     * 也就是让出这个线程的意愿调度器不一定会采纳
     */
    public static void main(String[] args) {
        Runnable yieldTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    System.out.println(Thread.currentThread().getName() + i);
                    if (i == 5) {
                        Thread.yield();
                    }
                }
            }
        };

        Thread t1 = new Thread(yieldTask, "A");
        Thread t2 = new Thread(yieldTask, "B");
        t1.start();
        t2.start();
    }
}
