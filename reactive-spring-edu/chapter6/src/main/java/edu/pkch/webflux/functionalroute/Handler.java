package edu.pkch.webflux.functionalroute;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

@Service
public class Handler {

    public HandlerFunction create() {
        System.out.println("request create");

        return request -> ServerResponse.created(null)
                .body(BodyInserters.fromValue("create"));
    }

    public HandlerFunction get() {
        System.out.println("request get");

        return request -> ServerResponse.ok().bodyValue("get");
    }
}
