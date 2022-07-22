package com.example.demo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.domain.MovieInfo;

import reactor.core.publisher.Flux;

public interface MovieInfoRepository extends ReactiveMongoRepository<MovieInfo, String> {

	Flux<MovieInfo> findByYear(Integer year);

	Flux<MovieInfo> findByName(String name);
}