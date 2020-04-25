package edu.pkch.movie.domain;

import java.util.Collections;
import java.util.Map;

public class MovieTable {
    private final Map<String, MovieSchedules> movieTable;

    private MovieTable(Map<String, MovieSchedules> movieTable) {
        this.movieTable = movieTable;
    }

    public MovieSchedule pick(String title, Turn turn) {
        return movieTable.get(title).findByTurn(turn);
    }

    public static class Builder {
        private Map<String, MovieSchedules> movieTable;

        public Builder addMovieSchedule(String title, MovieSchedules movieSchedules) {
            movieTable.put(title, movieSchedules);
            return this;
        }

        public MovieTable build() {
            return new MovieTable(Collections.unmodifiableMap(movieTable));
        }
    }
}
