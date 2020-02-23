package edu.pkch.reactive;

import java.util.concurrent.Future;

public interface ShoppingCardFutureService {
    Future<Long> calculate(int input);
}
