package edu.concurrent.executor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import static org.assertj.core.api.Assertions.assertThat;

public class ExecutorTest {
    private static final String WORKER_NAME = "Test worker";

    private Executor executor;

    @Test
    @DisplayName("Executor로 현재 스레드 그대로 동기적으로 실행")
    void practiceExecutorForSynchronous() {
        executor = new DirectExecutor();
        String threadName = Thread.currentThread().getName();

        assertThat(threadName).isEqualTo(WORKER_NAME);

        Runnable task = () -> assertThat(Thread.currentThread().getName()).isEqualTo(WORKER_NAME);
        executor.execute(task);
    }

    @Test
    @DisplayName("Executor로 새로운 스레드에서 비동기적으로 실행")
    void practiceExecutorForAsynchronous() throws InterruptedException {
        executor = new AsyncExecutor();
        String threadName = Thread.currentThread().getName();

        assertThat(threadName).isEqualTo(WORKER_NAME);

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Runnable task = () -> {
            assertThat(Thread.currentThread().getName()).isNotEqualTo(WORKER_NAME);
            countDownLatch.countDown();
        };

        executor.execute(task);
        countDownLatch.await();
    }
}
