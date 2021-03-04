package com.tangerine.specter.thread;

/**
 * Notify演示
 */
public class NotifycationDemo {

    private volatile boolean go = false;

    /**
     * 每次执行唤醒的线程为随机
     * <p>
     * 执行结果：
     * Thread[WT1,5,main] is going to wait on this object
     * Thread[WT3,5,main] is going to wait on this object
     * Thread[WT2,5,main] is going to wait on this object
     * Thread[NT1,5,main] is going to notify all or one thread waiting on this object
     * Thread[WT1,5,main]is woken up
     * NT1 finished Execution
     * WT1 finished Execution
     */
    public static void main(String[] args) throws InterruptedException {
        final NotifycationDemo test = new NotifycationDemo();

        Runnable waitTask = new Runnable() {
            @Override
            public void run() {
                try {
                    test.shouldGo();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " finished Execution");
            }
        };

        Runnable notifyTask = new Runnable() {
            @Override
            public void run() {
                test.go();
                System.out.println(Thread.currentThread().getName() + " finished Execution");
            }
        };

        Thread t1 = new Thread(waitTask, "WT1");
        Thread t2 = new Thread(waitTask, "WT2");
        Thread t3 = new Thread(waitTask, "WT3");
        Thread t4 = new Thread(notifyTask, "NT1");

        t1.start();
        t2.start();
        t3.start();
        //等待200ms再进行唤醒
        Thread.sleep(200);
        t4.start();
    }

    private synchronized void shouldGo() throws InterruptedException {
        while (!go) {
            System.out.println(Thread.currentThread() + " is going to wait on this object");
            //一开始start的三个线程在这里进入等待状态
            wait();
            System.out.println(Thread.currentThread() + "is woken up");
        }
        go = false;
    }

    private synchronized void go() {
        while (!go) {
            System.out.println(Thread.currentThread()
                    + " is going to notify all or one thread waiting on this object");
            go = true;
            //此处随机唤醒一个线程
            notify();
        }
    }
}
