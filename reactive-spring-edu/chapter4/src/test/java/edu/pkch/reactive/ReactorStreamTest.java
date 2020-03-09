package edu.pkch.reactive;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Supplier;

public class ReactorStreamTest {

    @Test
    @DisplayName("Mono.just로 Mono 생성 테스트")
    void createMonoForJust() {
        StepVerifier.create(Mono.just("1"))
                .expectNext("1")
                .verifyComplete();
    }

    @Test
    @DisplayName("Mono.defer로 Mono 생성 테스트")
    void createMonoForDefer_notThrow() {
        StepVerifier.create(createMonoForDefer(1))
                .expectNext(1)
                .verifyComplete();

    }

    @Test
    @DisplayName("Mono.defer로 Mono 생성 테스트")
    void createMonoForDefer_throwIllegalArgumentException() {
        StepVerifier.create(createMonoForDefer(2))
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    private Mono<Integer> createMonoForDefer(int index) {
        return Mono.defer(
                () -> index == 1 ?
                        Mono.just(1) :
                        Mono.error(new IllegalArgumentException())
        );
    }

    @Test
    @DisplayName("Mono.create로 Mono 생성 테스트")
    void createMonoForCreate_throwIllegalArgumentException() {
        StepVerifier.create(Mono.create(sink -> sink.success(1)))
                .expectNext(1)
                .verifyComplete();
    }

    @Test
    @DisplayName("Flux just로 생성 테스트")
    void createFlux() {
        StepVerifier.create(Flux.just(1, 2, 3, 4, 5))
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .verifyComplete();
    }
}
