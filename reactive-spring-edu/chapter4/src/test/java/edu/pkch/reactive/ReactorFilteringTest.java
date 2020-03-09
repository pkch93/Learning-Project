package edu.pkch.reactive;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class ReactorFilteringTest {

    @Test
    @DisplayName("reactor filter 사용")
    void practiceFilter() {
        StepVerifier.create(
                Flux.just(1, 2, 3, 4, 5)
                .filter(num -> (num & 1) == 0)
        )
                .expectNext(2)
                .expectNext(4)
                .verifyComplete();
    }

    @Test
    @DisplayName("시퀀스의 어떤 원소도 통과시키지 않는 메서드인 ignoreElements 사용")
    void practiceIgnoreElements() {
        StepVerifier.create(
                Flux.just(1, 2, 3, 4, 5)
                .ignoreElements()
        )
                .verifyComplete();
    }

    @Test
    @DisplayName("take로 3번째 데이터까지 통과하도록 만든다.")
    void practiceTake() {
        StepVerifier.create(
                Flux.just(1, 2, 3, 4, 5)
                .take(3L)
        )
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .verifyComplete();
    }

    @Test
    @Disabled("10 나노초 기준으로 데이터를 가져오지만 컴퓨터마다 성능이 다르므로 Disabled")
    @DisplayName("take에 인자로 Duration 지정")
    void practiceTakeByDuration() {
        StepVerifier.create(
                Flux.just(1, 2, 3, 4, 5)
                .take(Duration.ofNanos(10))
        )
                .verifyComplete();
    }

    @Test
    @DisplayName("takeLast 사용 실습")
    void practiceTakeLast() {
        StepVerifier.create(
                Flux.just(1, 2, 3, 4, 5)
                        .takeLast(2)
        )
                .expectNext(4)
                .expectNext(5)
                .verifyComplete();
    }

    @Test
    @DisplayName("원소가 3인 경우까지 가져오도록 takeUntil 사용 실습")
    void practiceTakeUntil() {
        StepVerifier.create(
                Flux.just(1, 2, 3, 4, 5)
                        .takeUntil(num -> num == 3)
        )
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .verifyComplete();
    }

    @Test
    @DisplayName("elementAt으로 4번째 데이터 가져오기 실습")
    void practiceElementAt() {
        StepVerifier.create(
                Flux.just(1, 2, 3, 4, 5)
                        .elementAt(3)
        )
                .expectNext(4)
                .verifyComplete();
    }

    @Test
    @DisplayName("elementAt으로 없는 인덱스의 데이터를 가져오는 경우 default값이 있다면 해당 default값 전달")
    void practiceElementAt_ifNotExistAtTargetIndex_thenDefaultValue() {
        StepVerifier.create(
                Flux.just(1, 2, 3, 4, 5)
                        .elementAt(5, 0)
        )
                .expectNext(0)
                .verifyComplete();
    }

    @Test
    @DisplayName("elementAt으로 없는 인덱스의 데이터를 가져오는 경우 default값이 없다면 IndexOutOfBoundsException 발생")
    void practiceElementAt_ifNotExistAtTargetIndex_thenIndexOutOfBoundsException() {
        StepVerifier.create(
                Flux.just(1, 2, 3, 4, 5)
                        .elementAt(5)
        )
                .expectError(IndexOutOfBoundsException.class)
                .verify();
    }
}
