package edu.pkch.reactive;

import java.util.stream.LongStream;

public class OrderService {

    private final ShoppingCardService shoppingCardService = (input, callback) -> {
        long sum = LongStream.range(0, 100_000_000_000L)
                .map(idx -> input * input)
                .sum();

        System.out.println(sum);
    };

    public void process() {
        int input = 10;
        shoppingCardService.calculate(input, System.out::println);
        System.out.println("hello");
    }
}
