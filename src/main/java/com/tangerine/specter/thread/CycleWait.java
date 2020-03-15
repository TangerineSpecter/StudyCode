package thread;

/**
 * 主线程等待以及join()代码演示
 */
public class CycleWait implements Runnable {

    private String value;

    @Override
    public void run() {
        try {
            Thread.currentThread().sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        value = "we have data now";
    }

    /**
     * 主线程等待法
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        CycleWait cw = new CycleWait();
        Thread t = new Thread(cw);
        t.start();
        //1.主线程等待法：没有此步骤判断，value为null
        while (cw.value == null) {
            Thread.currentThread().sleep(100);
        }
        //2.阻塞当前线程，等待子线程完成
        //t.join();
        System.out.println("value：" + cw.value);
    }
}
