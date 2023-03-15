package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void namesFluxSuccess_whenCorrectOrder() {
        // given

        // when
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();

        // then
        StepVerifier.create(namesFlux)
                .expectNext("Alex", "Ben", "Chloe")
                .verifyComplete();
    }

    @Test
    void namesFluxFails_whenIncorrectOrder() {
        // given

        // when
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();

        // then
        StepVerifier.create(namesFlux)
                .expectNext("Ben", "Alex", "Chloe")
                .expectError(AssertionError.class);
    }

    @Test
    void namesFluxSuccess_whenCorrectNumberOfItemsPassed() {
        // given

        // when
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();

        // then
        StepVerifier.create(namesFlux)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void namesFluxSuccess_whenFirstItemIsCorrect_andCorrectNumberOfRemainingItemsPassed() {
        // given

        // when
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();

        // then
        StepVerifier.create(namesFlux)
                .expectNext("Alex")
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void namesFlux_mapConvertsNamesToUpperCase() {
        var namesFluxMapUpperCase = fluxAndMonoGeneratorService.namesFlux_map();

        StepVerifier.create(namesFluxMapUpperCase)
                .expectNext("ALEX", "BEN", "CHLOE")
                .verifyComplete();
    }

    //  verify that the reactive stream is immutable
    @Test
    void namesFlux_immutability() {
        var namesFluxMapUpperCase = fluxAndMonoGeneratorService.namesFlux_immutability();

        StepVerifier.create(namesFluxMapUpperCase)
                .expectNext("alex", "ben", "chloe")
//                .expectNext("ALEX", "BEN", "CHLOE") // This would fail the test
                .verifyComplete();
    }

    @Test
    void namesFluxMapByLength() {
        var namesFlux = fluxAndMonoGeneratorService.namesFluxMapByLength(3);

        StepVerifier.create(namesFlux)
                .expectNext("4-alex", "5-chloe")
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMapToStringArray() {
        StepVerifier.create(fluxAndMonoGeneratorService.namesFluxFlatMapToStringArray(3))
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();
    }
}