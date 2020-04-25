package edu.pkch.movie.domain;

public class Capacity {

    private int capacity;

    public Capacity(int capacity) {
        this.capacity = capacity;
    }

    public int occupy(int occupiedCount) {
        capacity -= occupiedCount;
        return capacity;
    }

    public boolean isFull() {
        return capacity == 0;
    }
}
