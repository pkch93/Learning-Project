package edu.pkch.movie.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReserveSystemTest implements MovieStatic {
    private ReserveSystem reserveSystem;

    @BeforeEach
    void setUp() {
        reserveSystem = new ReserveSystem(MOVIE_TABLE);
    }

    @Test
    @DisplayName("예약을 한다.")
    void reserve() {
        ReserveInfo actual = reserveSystem.reserve("movie1", Turn.of(2), 2);

        assertThat(actual.getMovieSchedule()).isEqualTo(MOVIE_SCHEDULE_1);
    }
}