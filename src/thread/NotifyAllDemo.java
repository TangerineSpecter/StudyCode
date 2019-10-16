package thread;

/**
 * notify和notifyAll代码演示
 */
public class NotifyAllDemo {

    /**
     * notify随机唤醒，notifyAll唤醒全部
     * <p>
     * 执行结果：
     * Thread A is waiting to get lock
     * Thread A get lock
     * Thread B is waiting to get lock
     * Thread A do wait method
     * Thread B get lock
     * Thread B is sleeping 10 ms
     * Thread B is done
     * Thread A is done
     */
    public static void main(String[] args) {
        final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread A is waiting to get lock");
                synchronized (lock) {
                    try {
                        System.out.println("Thread A get lock");
                        Thread.sleep(20);
                        System.out.println("Thread A do wait method");
                        //此处进入无限等待，等待唤醒
                        lock.wait();
                        System.out.println("Thread A is done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        try {
            //保证两个线程的先后执行顺序
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread B is waiting to get lock");
                synchronized (lock) {
                    try {
                        System.out.println("Thread B get lock");
                        System.out.println("Thread B is sleeping 10 ms");
                        Thread.sleep(20);
                        System.out.println("Thread B is done");
                        //释放全部锁
                        lock.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
