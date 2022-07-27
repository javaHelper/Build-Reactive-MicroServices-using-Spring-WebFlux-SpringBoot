package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

public class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService generatorService = new FluxAndMonoGeneratorService();

    @Test
    void testNamesFluxWithSize() {
        Flux<String> namesFlux = generatorService.namesFlux();
        StepVerifier.create(namesFlux)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void testNamesFluxWithData(){
        Flux<String> namesFlux = generatorService.namesFlux();
        StepVerifier.create(namesFlux)
                .expectNext("Alex", "Ben", "Chloe")
                .verifyComplete();
    }

    @Test
    void testNamesFluxWithCountAndData(){
        Flux<String> namesFlux = generatorService.namesFlux();
        StepVerifier.create(namesFlux)
                .expectNext("Alex")
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void testNamesFluxMap(){
        Flux<String> namesFlux = generatorService.namesFluxMap();

        StepVerifier.create(namesFlux)
                .expectNext("ALEX")
                .expectNext("BEN")
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void testNamesFluxFilter() {
        var namesFlux = generatorService.namesFluxFilter(4);
        StepVerifier.create(namesFlux)
                .expectNext("Alex")
                .verifyComplete();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5})
    void testNamesFluxFilterWithSize(int nameLength) {
        var namesFlux = generatorService.namesFluxFilter(nameLength);
        StepVerifier.create(namesFlux)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void testNamesFluxFlatMap() {
        var namesFlux = generatorService.namesFluxFilterFlatMap(5);
        StepVerifier
                .create(namesFlux)
                .expectNext("C", "H", "L", "O", "E")
                .verifyComplete();
    }

    void testNamesFluxFlatMapAsync() {
        var namesFlux = generatorService.namesFluxFilterFlatMapAsync(3);
        Assertions
                .assertThrows(Error.class, () -> {
                    StepVerifier
                            .create(namesFlux)
                            .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                            .verifyComplete();
                });
    }

    @Test
    void testNamesFluxFlatMapAsyncCount() {
        var namesFlux = generatorService.namesFluxFilterFlatMapAsync(3);
        StepVerifier
                .create(namesFlux)
                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void testNamesFluxConcatMap() {
        var namesFlux = generatorService.namesFluxConcatMap(3);
        StepVerifier
                .create(namesFlux)
                .expectNext("B","E", "N")
                .verifyComplete();
    }

    @Test
    void testNamesMonoFlatMap() {
        var namesFlux = generatorService.namesMonoFlatMap(3);
        StepVerifier
                .create(namesFlux)
                .expectNext(List.of("A", "L", "E", "X"))
                .verifyComplete();
    }

    @Test
    void testNamesMonoFlatMapMany() {
        var namesFlux = generatorService.namesMonoFlatMapMany(3);
        StepVerifier
                .create(namesFlux)
                .expectNext("A", "L", "E", "X")
                .verifyComplete();
    }

    @Test
    void testNamesFluxTransform() {
        var namesFlux = generatorService.namesFluxTransform(3);
        StepVerifier
                .create(namesFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();
    }

    @Test
    void testNamesFluxTransform_1() {
        var namesFlux = generatorService.namesFluxWithDefault(6);
        StepVerifier
                .create(namesFlux)
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    void testNamesFluxTransform_2() {
        var namesFlux = generatorService.namesFluxWithSwitchIfEmpty(6);
        StepVerifier
                .create(namesFlux)
                .expectNext("DEFAULT")
                .verifyComplete();
    }

    @Test
    void testFluxConcat() {
        var namesFlux = generatorService.exploreConcat();
        StepVerifier
                .create(namesFlux)
                .expectNext("a", "b", "c", "d", "e", "f")
                .verifyComplete();
    }

    @Test
    void testFluxConcatWith() {
        var namesFlux = generatorService.exploreConcatWith();
        StepVerifier
                .create(namesFlux)
                .expectNext("a", "b", "c", "d", "e", "f")
                .verifyComplete();
    }

    @Test
    void testFluxMerge() {
        var namesFlux = generatorService.exploreMerge();
        StepVerifier
                .create(namesFlux)
                .expectNext("a", "d", "b", "e", "c", "f")
                .verifyComplete();
    }

    @Test
    void testFluxMergeWith() {
        var namesFlux = generatorService.exploreMergeWith();
        StepVerifier
                .create(namesFlux)
                .expectNext("a", "d", "b", "e", "c", "f")
                .verifyComplete();
    }

    @Test
    void testFluxZip() {
        var namesFlux = generatorService.exploreZip();
        StepVerifier
                .create(namesFlux)
                .expectNext("AD", "BE", "CF")
                .verifyComplete();
    }

    @Test
    void testFluxZip_1() {
        var namesFlux = generatorService.exploreZip_1();
        StepVerifier
                .create(namesFlux)
                .expectNext("AD14", "BE25", "CF36")
                .verifyComplete();
    }
}
