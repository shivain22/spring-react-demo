package com.example.demo.router;

import com.example.demo.handler.FooHandler;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/*
 * This class define RouterFunction bean which will handle the route
 * */
@Configuration(proxyBeanMethods = false)
public class FooRouter {


    @Bean
    @RouterOperation(beanClass = FooHandler.class, beanMethod = "getEvents")
    public RouterFunction<ServerResponse> route(FooHandler fooHandler) {
        return RouterFunctions
                .route(GET("/stream-foos-handler").and(accept(MediaType.TEXT_EVENT_STREAM)), fooHandler::getEvents)
                .andRoute(GET("/stream-foos-handler/{delayInSeconds}").and(accept(MediaType.TEXT_EVENT_STREAM)), fooHandler::getEvents);
    }
}
