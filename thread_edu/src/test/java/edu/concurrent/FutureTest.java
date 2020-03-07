package edu.concurrent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;

public class FutureTest {

    @Test
    @DisplayName("future 생성 및 테스트")
    public void practiceFuture() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<Integer> one = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            return 1;
        });
        Future<Integer> two = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            return 2;
        });

        assertThat(one.get() + two.get()).isEqualTo(3);
    }
}
