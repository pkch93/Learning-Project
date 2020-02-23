package edu.pkch.reactive;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class TestController {

    @GetMapping
    public SseEmitter test() {
        SseEmitter sseEmitter = new SseEmitter();

        return sseEmitter;
    }

    @Async
    @EventListener
    public void test2() {

    }
}
