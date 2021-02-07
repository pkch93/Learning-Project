package edu.pkch.function;

import java.util.function.Function;
import java.util.function.Predicate;

public class PartialFunc<T, R> {

    private final Predicate<T> condition;
    private final Function<T, R> func;

    public PartialFunc(Predicate<T> condition, Function<T, R> func) {
        this.condition = condition;
        this.func = func;
    }

    public R invoke(T t) {
        if (condition.test(t)) {
            return func.apply(t);
        }

        throw new IllegalArgumentException();
    }
}
