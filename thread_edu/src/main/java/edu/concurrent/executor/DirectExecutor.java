package edu.concurrent.executor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;

@Slf4j
public class DirectExecutor implements Executor {
    private static final Logger logger = LoggerFactory.getLogger(DirectExecutor.class);

    @Override
    public void execute(Runnable command) {
        logger.info("current thread is {}", Thread.currentThread().getName());
        command.run();
    }
}
