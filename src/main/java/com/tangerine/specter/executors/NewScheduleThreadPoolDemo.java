package com.tangerine.specter.executors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 延时或者延时后定时执行的固定工作线程数量的线程池演示
 */
public class NewScheduleThreadPoolDemo {

    public static class TaskA implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("线程 A 当前时间(s)：" + time2Date(System.currentTimeMillis() / 1000, "yyyy-MM-dd HH:mm:ss"));
        }
    }

    public static class TaskB implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程 B 当前时间(s)：" + time2Date(System.currentTimeMillis() / 1000, "yyyy-MM-dd HH:mm:ss"));
        }
    }

    public static String time2Date(long seconds, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.parseLong(seconds + "000")));
    }

    /**
     * 创建一个延时或者延时后定时执行的固定工作线程数量的线程池，
     * 定时执行类似于 Timer，
     * 适合定时处理任务场景。
     *
     * @param args
     */
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(5);
        System.out.println("主线程当前时间(s)：" + time2Date(System.currentTimeMillis() / 1000, "yyyy-MM-dd HH:mm:ss"));
        //延时执行，只执行一次
        scheduledExecutor.schedule(new Thread(new NewScheduleThreadPoolDemo.TaskA()), 3, TimeUnit.SECONDS);
        scheduledExecutor.schedule(new Thread(new NewScheduleThreadPoolDemo.TaskB()), 5, TimeUnit.SECONDS);
        //延后 1 秒后定时执行
        scheduledExecutor.scheduleAtFixedRate(new Thread(new NewScheduleThreadPoolDemo.TaskA()), 1, 3, TimeUnit.SECONDS);
        scheduledExecutor.scheduleAtFixedRate(new Thread(new NewScheduleThreadPoolDemo.TaskB()), 1, 3, TimeUnit.SECONDS);
    }
}
