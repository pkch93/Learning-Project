package edu.concurrent.threadpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorTest {

    @Test
    void name() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
    }
}
