package edu.pkch.reactive;

import java.util.concurrent.*;
import java.util.stream.LongStream;

public class OrderFutureService {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    private final ShoppingCardFutureService shoppingCardFutureService = (input) -> {
        long sum = LongStream.range(0, 100_000_000_000L)
                .map(idx -> input * input)
                .sum();

        return executorService.submit(() -> sum);
    };

    public void process() throws ExecutionException, InterruptedException {
        int input = 10;
        Future<Long> calculated = shoppingCardFutureService.calculate(input);
        System.out.println(calculated.get());
        System.out.println("hello");
    }
}
