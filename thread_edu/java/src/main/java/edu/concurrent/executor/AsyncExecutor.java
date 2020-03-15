package edu.concurrent.executor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;

@Slf4j
public class AsyncExecutor implements Executor {
    private static final Logger logger = LoggerFactory.getLogger(AsyncExecutor.class);

    @Override
    public void execute(Runnable task) {
        logger.info("Current thread is {}", Thread.currentThread().getName());
        new Thread(task).start();
    }
}
