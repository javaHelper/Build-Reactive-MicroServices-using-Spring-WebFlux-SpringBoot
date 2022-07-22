package com.example.demo.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.domain.Review;
import com.example.demo.exception.ReviewsClientException;
import com.example.demo.exception.ReviewsServerException;
import com.example.demo.utils.RetryUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ReviewRestClient {

	private WebClient webClient;

	public ReviewRestClient(WebClient webClient) {
		this.webClient = webClient;
	}

	@Value("${restClient.reviewsUrl}")
	private String moviesInfoUrl;

	public Flux<Review> retrieveReviews(String movieId) {
		String url = UriComponentsBuilder
				.fromHttpUrl(moviesInfoUrl)
				.queryParam("movieInfoId", movieId)
				.buildAndExpand()
				.toUriString();

		return webClient
				.get()
				.uri(url)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, reviewsResponse -> handle4xxError(movieId, reviewsResponse))
				.onStatus(HttpStatus::is5xxServerError, reviewsResponse -> handle5xxError(movieId, reviewsResponse))
				.bodyToFlux(Review.class)
				.retryWhen(RetryUtils.retrySpec())
				.log();
	}

	private Mono<Throwable> handle4xxError(String movieId, ClientResponse reviewsResponse) {
		if (reviewsResponse.statusCode().equals(HttpStatus.NOT_FOUND)) 
			return Mono.empty();

		return reviewsResponse.bodyToMono(String.class)
				.flatMap(responseMsg -> Mono.error(new ReviewsClientException(responseMsg)));
	}

	private Mono<Throwable> handle5xxError(String movieId, ClientResponse reviewsResponse) {
		return reviewsResponse.bodyToMono(String.class)
				.flatMap(responseMsg -> Mono.error(
						new ReviewsServerException("Server exception in ReviewService : " + responseMsg)
						));
	}

}