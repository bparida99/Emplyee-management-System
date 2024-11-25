package com.exp.EmployeeService.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/*
This is an example of Controller annotation approach
 */
@RestController
public class EmployeeControllerAnnotatedApproachExample {

    @GetMapping("/getEmployeeIds")
    public Flux<Integer> getEmployeeIds(){
        return Flux.just(1,2,3,4).log();
    }

    @GetMapping("/getOneEmployeeName")
    public Mono<String> getOneEmployeeName(){
        return Mono.just("Biswo");
    }

    //It will continuously stream the response
    @GetMapping(value ="/getResponseInAnInterval",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> getResponseInAnInterval(){
        return Flux.interval(Duration.ofSeconds(1)).log();
    }
}
