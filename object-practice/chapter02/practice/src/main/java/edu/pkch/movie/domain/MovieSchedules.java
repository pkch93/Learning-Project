package edu.pkch.movie.domain;

import java.util.List;

public class MovieSchedules {
    private List<MovieSchedule> movieSchedules;

    public MovieSchedules(List<MovieSchedule> movieSchedules) {
        this.movieSchedules = movieSchedules;
    }

    public MovieSchedule findByTurn(Turn turn) {
        return movieSchedules.stream()
                .filter(movieSchedule -> movieSchedule.isMatchTurn(turn))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
