package edu.pkch.concurrent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class AsyncConfig {
    public static final String THREAD_POOL_EXECUTOR = "testAsyncThreadPoolExecutor";

    @Bean(name = THREAD_POOL_EXECUTOR)
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(16);
        threadPoolTaskExecutor.setQueueCapacity(100000);
        threadPoolTaskExecutor.setMaxPoolSize(32);
        threadPoolTaskExecutor.initialize();

        return threadPoolTaskExecutor;
    }
}
