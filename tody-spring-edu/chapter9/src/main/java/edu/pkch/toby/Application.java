package edu.pkch.toby;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
@Slf4j
public class Application {

    @GetMapping("/")
    public Mono<String> hello() {
        log.info("position 1");
        Mono<String> m = Mono.just("hello webflux").log();
        log.info("position 2");

        return m;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
