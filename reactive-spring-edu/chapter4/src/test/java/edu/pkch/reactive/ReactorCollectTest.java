package edu.pkch.reactive;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.internal.Function;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

public class ReactorCollectTest {


    /**
     * collectList는 Flux를 Mono<List<Object>> 형태로 변환시켜준다.
     */
    @Test
    @DisplayName("collectList로 Flux를 List로 변환하기")
    void practiceCollectList() {
        StepVerifier.create(Flux.just(1, 2, 3, 4, 5).collectList())
                .expectNext(Arrays.asList(1, 2, 3, 4, 5))
                .verifyComplete();
    }

    /**
     * collectSortedList는 Flux를 Mono<List<Object>> 형태로 변환시켜준다.
     * 이때 Comparator를 인자로 주지 않으면 오름차순으로 정렬된다.
     */
    @Test
    @DisplayName("collectSortedList로 Flux를 List로 변환하기")
    void practiceCollectSortedList() {
        StepVerifier.create(Flux.just(3, 5, 1, 4, 2).collectSortedList())
                .expectNext(Arrays.asList(1, 2, 3, 4, 5))
                .verifyComplete();
    }

    /**
     * collectSortedList는 Flux를 Mono<List<Object>> 형태로 변환시켜준다.
     * 이때 Comparator를 인자로 주지 않으면 오름차순으로 정렬된다.
     */
    @Test
    @DisplayName("collectSortedList로 Flux를 List로 내림차순 변환하기")
    void practiceCollectSortedListForDescending() {
        Comparator<Integer> desc = (former, letter) -> former < letter ? 1 : -1;

        StepVerifier.create(Flux.just(3, 5, 1, 4, 2).collectSortedList(desc))
                .expectNext(Arrays.asList(5, 4, 3, 2, 1))
                .verifyComplete();
    }

    /**
     * collectMap은 Flux를 Mono<Map<K, V>> 형태로 변환시켜준다.
     */
    @Test
    @DisplayName("collectMap으로 Flux를 Map으로 변환하기")
    void practiceCollectMap() throws InterruptedException {
        Map<String, Integer> actual = Flux.just(3, 5, 1, 4, 2)
                .collectMap(number -> number + "")
                .block();

        assertThat(actual)
                .extracting("1", "2", "3", "4", "5")
                .containsOnly(1, 2, 3, 4, 5);
    }

    /**
     * collectMultiMap는 Flux를 Mono<<Map<K, Collection<V>>> 형태로 변환시켜준다.
     */
    @Test
    @DisplayName("collectMultiMap으로 Flux를 Map<K, Collection<T>>으로 변환하기")
    void practiceCollectMultiMap() throws InterruptedException {
        StepVerifier.create(Flux.just(1, 2, 3, 4, 5)
                .collectMultimap(number -> "key")
                .map(map -> map.get("key"))
        )
                .expectNext(Arrays.asList(1, 2, 3, 4, 5))
                .verifyComplete();
    }

    @Test
    @DisplayName("distinct로 시퀀스 내부에 중복 제거")
    void practiceDistinct() {
        StepVerifier.create(
                Flux.just(1, 1, 1, 1, 2, 2, 3)
                        .distinct()
        )
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .verifyComplete();
    }
}
