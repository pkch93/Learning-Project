package edu.pkch.movie.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MovieSchedulesTest implements MovieStatic {

    @Test
    void findByTurn() {
        MovieSchedule actual = MOVIE_SCHEDULES_1.findByTurn(Turn.of(2));

        assertThat(actual).isEqualTo(MOVIE_SCHEDULE_1);
    }

    @Test
    void findByTurn_ifInvalidTurn_thenIllegalArgumentException() {
        assertThatThrownBy(() -> MOVIE_SCHEDULES_1.findByTurn(Turn.of(10)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}