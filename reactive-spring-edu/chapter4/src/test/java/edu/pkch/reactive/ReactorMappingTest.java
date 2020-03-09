package edu.pkch.reactive;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

public class ReactorMappingTest {

    @Test
    @DisplayName("reactor map 사용")
    void practiceMap() {
        StepVerifier.create(
                Flux.just(1, 2, 3, 4, 5).map(num -> num + 10)
        )
                .expectNext(11)
                .expectNext(12)
                .expectNext(13)
                .expectNext(14)
                .expectNext(15)
                .verifyComplete();
    }

    @Test
    @DisplayName("reactor cast 사용")
    void practiceCast() {
        StepVerifier.create(
                Flux.just(1, 2, 3, 4, 5).cast(Object.class)
        )
                .expectNext(1, 2, 3, 4, 5)
                .verifyComplete();
    }

    @Test
    @DisplayName("reactor cast시 계층구조에 없는 Integer에서 String으로 변환할 때 ClassCastException이 발생한다.")
    void practiceCast_ifIntegerCastToString_thenClassCastException() {
        StepVerifier.create(
                Flux.just(1, 2, 3, 4, 5).cast(String.class)
        )
                .expectError(ClassCastException.class)
                .verify();
    }

    @Test
    @DisplayName("reactor index 사용")
    void practiceIndex() {
        StepVerifier.create(
                Flux.just("a", "b", "c", "d", "e").index().map(Tuple2::getT1)
        )
                .expectNext(0L)
                .expectNext(1L)
                .expectNext(2L)
                .expectNext(3L)
                .expectNext(4L)
                .verifyComplete();
    }

    @Test
    @Disabled("현재 시간에 대한 변수가 매우 많음으로 Disabled")
    @DisplayName("reactor timestamp 사용")
    void practice_fluxThen() {
        StepVerifier.create(
                Mono.just(1).timestamp().map(Tuple2::getT1)
        )
                .expectNext(1L)
                .verifyComplete();
    }
}
