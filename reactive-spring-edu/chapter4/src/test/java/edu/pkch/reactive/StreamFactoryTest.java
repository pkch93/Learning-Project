package edu.pkch.reactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class StreamFactoryTest {

    @Test
    void name() {
        Mono.error(IllegalStateException::new);
    }
}
