package com.tangerine.specter.executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 * 配合 @Async("xxxExecutor")注解使用
 */
@Configuration
public class ExecutorConfig {

    @Bean(name = "xxxExecutor")
    public Executor demoExecutor() {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        poolTaskExecutor.setCorePoolSize(5);
        //最大线程数
        poolTaskExecutor.setMaxPoolSize(5);
        //缓冲队列大小
        poolTaskExecutor.setQueueCapacity(1024);
        //线程池最大空闲时间
        poolTaskExecutor.setKeepAliveSeconds(300);
        //线程的名称前缀
        poolTaskExecutor.setThreadNamePrefix("demoExecutor");
        //拒绝策略
        poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return poolTaskExecutor;
    }
}
