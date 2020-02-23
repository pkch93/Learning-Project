package edu.pkch.reactive;

import java.util.concurrent.ExecutionException;

public class Application {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        OrderFutureService orderService = new OrderFutureService();

        orderService.process();
    }
}
