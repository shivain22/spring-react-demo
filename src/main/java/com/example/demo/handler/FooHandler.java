package com.example.demo.handler;

import java.time.Duration;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.domain.Foo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class FooHandler {

  public Mono<ServerResponse> getEvents(ServerRequest request) {

      int delayInSeconds = 1;
      Optional<String> opt = Optional.ofNullable(request.pathVariable("delayInSeconds"));
      if(opt.isPresent()) {
          delayInSeconds = Integer.parseInt(request.pathVariable("delayInSeconds"));
      }
      Flux<Foo> foos = Flux.interval(Duration.ofSeconds(delayInSeconds)).map(val -> new Foo(val,"event"+val));
	
      return ServerResponse.ok()
            .contentType(MediaType.TEXT_EVENT_STREAM)
            .body(foos,Foo.class);
  }
}
