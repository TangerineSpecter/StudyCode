package com.tangerine.specter.executors;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池配置
 * 配合 @Async("xxxExecutor")注解使用
 * 其中:@ConfigurationProperties("async-thread-pool")的含义是
 * 读取application.yml配置文件中以"async-thread-pool"名为前缀的配置信息,并通过所描述类的set方法赋值给对应的属性
 */
@Setter
@Configuration
@ConfigurationProperties("async-thread-pool")
public class ExecutorConfig implements AsyncConfigurer {

    /**
     * 核心线程数
     */
    private int corePoolSize = 20;
    /**
     * 最大线程数
     */
    private int maximumPoolSize = 1000;
    /**
     * 线程空闲时间
     */
    private int keepAliveTime = 30;
    /**
     * 阻塞队列容量
     */
    private int queueCapacity = 200;
    /**
     * 构建线程工厂
     */
    private ThreadFactory threadFactory = new ThreadFactory() {
        //CAS算法
        private AtomicInteger at = new AtomicInteger(1000);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,
                    "async-thread-" + at.getAndIncrement());
        }
    };

    @Bean(name = "xxxExecutor")
    public Executor demoExecutor() {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        poolTaskExecutor.setCorePoolSize(corePoolSize);
        //最大线程数
        poolTaskExecutor.setMaxPoolSize(maximumPoolSize);
        //缓冲队列大小
        poolTaskExecutor.setQueueCapacity(queueCapacity);
        //线程池最大空闲时间
        poolTaskExecutor.setKeepAliveSeconds(keepAliveTime);
        //线程的名称前缀
        poolTaskExecutor.setThreadNamePrefix("demoExecutor");
        //拒绝策略
        poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //等待所有线程执行完毕，默认false
        poolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //等待时间
        poolTaskExecutor.setAwaitTerminationSeconds(60);
        poolTaskExecutor.initialize();
        return poolTaskExecutor;
    }
}
