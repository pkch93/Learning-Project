package edu.pkch.movie.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MovieTableTest implements MovieStatic {

    @Test
    void pick() {
        MovieSchedule actual = MOVIE_TABLE.pick("movie1", Turn.of(2));

        assertThat(actual).isEqualTo(MOVIE_SCHEDULE_1);
    }
}