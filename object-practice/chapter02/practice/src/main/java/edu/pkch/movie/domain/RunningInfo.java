package edu.pkch.movie.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class RunningInfo {

    private final Turn turn;

    private final LocalDate runningDate;

    private final LocalTime start;

    public RunningInfo(Turn turn, LocalDate runningDate, LocalTime start) {
        this.turn = turn;
        this.runningDate = runningDate;
        this.start = start;
    }

    public boolean isMatchTurn(Turn turn) {
        return this.turn.equals(turn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RunningInfo that = (RunningInfo) o;
        return Objects.equals(turn, that.turn) &&
                Objects.equals(runningDate, that.runningDate) &&
                Objects.equals(start, that.start);
    }

    @Override
    public int hashCode() {
        return Objects.hash(turn, runningDate, start);
    }
}
