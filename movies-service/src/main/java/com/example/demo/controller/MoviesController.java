package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.MoviesInfoRestClient;
import com.example.demo.client.ReviewRestClient;
import com.example.demo.domain.Movie;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/movies")
public class MoviesController {
	@Autowired
	private MoviesInfoRestClient moviesInfoRestClient;

	@Autowired
	private ReviewRestClient reviewRestClient;

	@GetMapping("/{movieId}")
	public Mono<Movie> retrieveMovieById(@PathVariable String movieId) {
		return moviesInfoRestClient.retrieveMovieInfo(movieId)
				.flatMap(movieInfo -> {
					var reviewsMono = reviewRestClient.retrieveReviews(movieId).collectList();
					return reviewsMono
							.map(reviews -> new Movie(movieInfo, reviews));
				});
	}

}