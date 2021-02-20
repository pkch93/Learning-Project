package edu.pkch.patterns.observer;

public interface Observer<T> {
    void update(Subject<T> subject, T t);
}
