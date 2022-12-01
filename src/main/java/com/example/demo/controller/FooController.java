package com.example.demo.controller;

import com.example.demo.domain.Foo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;

@RestController
public class FooController {


    @GetMapping(value = {"/stream-foos-controller/{delayInSeconds}", "/stream-foos-controller"}, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Foo> getFoosWithTimeDelay(@PathVariable(name = "delayInSeconds", required = false) Optional<String> delayInSeconds) {
        if (!delayInSeconds.isPresent()) {
            delayInSeconds = Optional.of("1");
        }
        return Flux.interval(Duration.ofSeconds(Integer.parseInt(delayInSeconds.get()))).map(val -> new Foo(val, "event" + val));
    }
}
