package edu.concurrent.future;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CompletableFutureTest {

    @Test
    @DisplayName("CompletableFuture는 complete으로 값을 설정한다.")
    void practiceCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> countFuture = new CompletableFuture<>();
        IntStream.rangeClosed(1, 10)
                .forEach(index -> asyncCount(countFuture));

        assertThat(countFuture).isDone();
        assertThat(countFuture.get()).isEqualTo(1);
    }

    private void asyncCount(CompletableFuture<Integer> future) {
        new Thread(() -> future.complete(1)).start();
    }

    @Test
    @DisplayName("CompletableFuture completeExceptionally로 예외 던지기")
    void practiceCompleteExceptionally() {
        assertThatThrownBy(() -> asyncCountForException().get()).isInstanceOf(RuntimeException.class);
    }

    private Future<Integer> asyncCountForException() {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        new Thread(() -> completableFuture.completeExceptionally(new RuntimeException())).start();
        return completableFuture;
    }

    @Test
    @DisplayName("Executor를 설정한 CompletableFuture 사용하기")
    void practiceCompletableFuture_customExecutor() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10, runnable -> {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            return thread;
        });

        CompletableFuture<Integer> actual = CompletableFuture.supplyAsync(() -> 1, executor);

        assertThat(actual.get()).isEqualTo(1);
    }
}
