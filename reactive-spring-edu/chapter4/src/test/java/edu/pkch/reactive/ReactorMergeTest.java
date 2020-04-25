package edu.pkch.reactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple3;

import java.util.Arrays;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class ReactorMergeTest {

    @Test
    void merge() {
        Function<Integer, Integer> powerNumber = number -> {
            System.out.println("number is " + number);
            return number * number;
        };
        Mono<Integer> byInteger = Mono.defer(() -> Mono.just(powerNumber.apply(1)));

        Mono<Integer> actual = byInteger.mergeWith(byInteger).mergeWith(byInteger)
                .flatMap(Mono::just)
                .reduce(Integer::sum);

        StepVerifier.create(actual)
                .expectNext(3)
                .verifyComplete();
    }

    @Test
    void zip() {
        Mono<Tuple3<Integer, Integer, Integer>> actual = Mono.zip(Mono.just(1), Mono.just(1), Mono.just(1))
                .map(result -> result);

        StepVerifier.create(actual)
                .consumeNextWith(tuple3 -> {
                    assertThat(tuple3.getT1()).isEqualTo(1);
                    assertThat(tuple3.getT2()).isEqualTo(1);
                    assertThat(tuple3.getT3()).isEqualTo(1);
                })
                .verifyComplete();
    }

    @Test
    void zipWithCombinator() {
        Mono<Integer> actual = Mono.zip(
                numbers -> Arrays.stream(numbers).mapToInt(number -> (int) number).sum(),
                Mono.just(1), Mono.just(1), Mono.just(1)
        );

        StepVerifier.create(actual)
                .expectNext(3)
                .verifyComplete();
    }
}
