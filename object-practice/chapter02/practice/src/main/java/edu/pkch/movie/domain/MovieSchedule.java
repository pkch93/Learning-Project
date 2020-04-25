package edu.pkch.movie.domain;

public class MovieSchedule {

    private final Movie movie;

    private final RunningInfo runningInfo;

    private boolean reserved;

    public MovieSchedule(Movie movie, RunningInfo runningInfo) {
        this.movie = movie;
        this.runningInfo = runningInfo;
    }

    public boolean isMatchTurn(Turn turn) {
        return runningInfo.isMatchTurn(turn);
    }

    public PriceInfo reserve(int totalPeople) {
        this.reserved = true;
        return movie.calculatePrice(totalPeople);
    }

    public void cancel() {
        this.reserved = false;
    }

    public boolean isReserved() {
        return reserved;
    }
}
