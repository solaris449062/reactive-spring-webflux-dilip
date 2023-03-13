package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("Alex", "Ben", "Chloe")) // emulating db or a remote service call
                .log();
    }

    public Mono<String>  nameMono() {
        return Mono.just("Alex")
                .log();
    }

    public Flux<String> namesFlux_map() {

        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(name -> name.toUpperCase())
//                .map(String::toUpperCase)
                .log();
    }


    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

        fluxAndMonoGeneratorService.namesFlux()
                .subscribe(name -> System.out.println("Flux:" + name));

        fluxAndMonoGeneratorService.nameMono()
                .subscribe(name -> System.out.println("Mono: " + name));
    }
}
