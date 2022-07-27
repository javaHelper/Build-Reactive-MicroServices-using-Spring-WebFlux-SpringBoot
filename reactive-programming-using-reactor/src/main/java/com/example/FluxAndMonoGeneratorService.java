package com.example;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {
    public Flux<String> namesFlux(){
        return Flux.fromIterable(List.of("Alex", "Ben", "Chloe"))
                .log();
    }

    public Mono<String> nameMono(){
        return Mono.just("Alex").log();
    }

    public Flux<String> namesFluxMap(){
        return Flux.
                fromIterable(List.of("Alex", "Ben", "Chloe"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> namesFluxFilter(int nameLength){
        return Flux
                .fromIterable(List.of("Alex", "Ben", "Chloe"))
                .filter(name -> name.length() == nameLength)
                .log();
    }

    public Flux<String> namesFluxFilterFlatMap(int nameLength){
        return Flux.fromIterable(List.of("Alex", "Ben", "Chloe"))
                .map(String::toUpperCase)
                .filter(name -> name.length() == nameLength)
                .flatMap(this::splitString)
                .log();
    }

    private Flux<String> splitString(String str) {
        String[] charsArray = str.split("");
        return Flux.fromArray(charsArray);
    }

    public Flux<String> namesFluxFilterFlatMapAsync(int nameLength){
        return Flux.fromIterable(List.of("Alex", "Ben", "Chloe"))
                .map(String::toUpperCase)
                .filter(name -> name.length() == nameLength)
                .flatMap(this::splitStringWithDelay)
                .log();
    }

    private Flux<String> splitStringWithDelay(String str){
        String[] charsArray = str.split("");
        int delay = new Random().nextInt(1000);
        return Flux
                .fromArray(charsArray)
                .delayElements(Duration.ofMillis(delay));
    }


    public Flux<String> namesFluxConcatMap(int nameLength){
        return Flux
                .fromIterable(List.of("Alex", "Ben", "Chloe"))
                .map(String::toUpperCase)
                .filter(name -> name.length() == nameLength)
                .concatMap(this::splitStringWithDelay)
                .log();
    }

    public Mono<List<String>> namesMonoFlatMap(int nameLength){
        return Mono.just("Alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > nameLength)
                .flatMap(this::splitStringMono)
                .log();
    }

    private Mono<List<String>> splitStringMono(String name){
        String[] charsArray = name.split("");
        return Mono.just(List.of(charsArray));
    }

    public Flux<String> namesMonoFlatMapMany(int nameLength){
        return Mono
                .just("Alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > nameLength)
                .flatMapMany(this::splitString)
                .log();
    }

    public Flux<String> namesFluxTransform(int nameLength) {
        Function<Flux<String>, Flux<String>> filterMap =
                name -> name
                        .map(String::toUpperCase)
                        .filter(n -> n.length() > nameLength);

        return Flux
                .fromIterable(List.of("Alex", "Ben", "Chloe"))
                .transform(filterMap)
                .flatMap(this::splitString)
                .log();
    }

    public Flux<String> namesFluxWithDefault(int nameLength) {
        return Flux
                .fromIterable(List.of("Alex", "Ben", "Chloe"))
                .filter(name -> name.length() == nameLength)
                .defaultIfEmpty("default")
                .log();
    }

    public Flux<String> namesFluxWithSwitchIfEmpty(int nameLength) {
        var defaultFlux = Flux.just("default").map(String::toUpperCase);

        return Flux
                .fromIterable(List.of("Alex", "Ben", "Chloe"))
                .filter(name -> name.length() == nameLength)
                .switchIfEmpty(defaultFlux)
                .log();
    }

    public Flux<String> exploreConcat(){
        Flux<String> flux1 = Flux.just("a", "b", "c");
        Flux<String> flux2 = Flux.just("d", "e", "f");
        return Flux.concat(flux1, flux2).log();
    }

    public Flux<String> exploreConcatWith(){
        Flux<String> flux1 = Flux.just("a", "b", "c");
        Flux<String> flux2 = Flux.just("d", "e", "f");

        return flux1.concatWith(flux2).log();
    }

    public Flux<String> exploreMerge(){
        Flux<String> flux1 = Flux.just("a", "b", "c")
                .delayElements(Duration.ofMillis(100));

        Flux<String> flux2 = Flux.just("d", "e", "f")
                .delayElements(Duration.ofMillis(125));

        return Flux.merge(flux1, flux2).log();
    }

    public Flux<String> exploreMergeWith(){
        Flux<String> flux1 = Flux.just("a", "b", "c")
                .delayElements(Duration.ofMillis(100));

        Flux<String> flux2 = Flux.just("d", "e", "f")
                .delayElements(Duration.ofMillis(125));

        return flux1.mergeWith(flux2).log();
    }

    public Flux<String> exploreMergeSequential(){
        Flux<String> flux1 = Flux.just("a", "b", "c");
        Flux<String> flux2 = Flux.just("d", "e", "f");

        return Flux.mergeSequential(flux1, flux2).log();
    }

    public Flux<String> exploreZip(){
        Flux<String> flux1 = Flux.just("a", "b", "c");
        Flux<String> flux2 = Flux.just("d", "e", "f");

        return Flux.zip(flux1, flux2, (first, second) -> first.toUpperCase() + second.toUpperCase())
                .log();
    }

    public Flux<String> exploreZip_1(){
        Flux<String> flux1 = Flux.just("a", "b", "c");
        Flux<String> flux2 = Flux.just("d", "e", "f");
        Flux<String> flux3 = Flux.just("1", "2", "3");
        Flux<String> flux4 = Flux.just("4", "5", "6");

        return Flux.zip(flux1, flux2, flux3, flux4)
                .map(tuple -> tuple.getT1() + tuple.getT2() + tuple.getT3() + tuple.getT4())
                .map(String::toUpperCase)
                .log();
    }


}
