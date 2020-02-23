package edu.pkch.reactive;

import java.util.function.Consumer;

public interface ShoppingCardService {
    void calculate(int input, Consumer<Long> callback);
}
