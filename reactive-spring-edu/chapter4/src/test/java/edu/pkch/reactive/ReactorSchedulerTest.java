package edu.pkch.reactive;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

public class ReactorSchedulerTest {

    @Test
    @DisplayName("publishOn으로 다른 스레드 사용하기")
    void practice_publishOn() {
        Scheduler scheduler = Schedulers.parallel();

        StepVerifier.create(Flux.range(1, 100_000)
                .map(num -> num + ""));

    }
}
