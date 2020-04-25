package edu.pkch.movie.domain;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Turn {
    private static final AtomicInteger TURN_CHECKER = new AtomicInteger(0);
    private static final int TURN_STEP = 1;

    private final int turn;

    private Turn(int turn) {
        this.turn = turn;
    }

    public static Turn of(int turn) {
        return new Turn(turn);
    }

    public static Turn next() {
        return new Turn(TURN_CHECKER.addAndGet(TURN_STEP));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turn turn1 = (Turn) o;
        return turn == turn1.turn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(turn);
    }
}
