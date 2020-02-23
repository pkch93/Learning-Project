package edu.pkch.reactive;

public interface Observer<T> {
    void observe(T event);
}
