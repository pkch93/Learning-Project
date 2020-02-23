package edu.pkch.reactive;

public interface Subject<T> {
    void register(Observer<T> observer);
    void unRegister(Observer<T> observer);
    void notify(T event);
}
