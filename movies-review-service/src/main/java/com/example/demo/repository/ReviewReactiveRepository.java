package com.example.demo.repository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.model.Review;

import reactor.core.publisher.Flux;

public interface ReviewReactiveRepository extends ReactiveMongoRepository<Review, String> {

    Flux<Review> findReviewsByMovieInfoId(String movieInfoId);
}