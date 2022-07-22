package com.example.demo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.domain.MovieInfo;
import com.example.demo.exception.MoviesInfoClientException;
import com.example.demo.exception.MoviesInfoServerException;
import com.example.demo.utils.RetryUtils;

import reactor.core.publisher.Mono;

@Component
public class MoviesInfoRestClient {

	@Autowired
    private WebClient webClient;

    @Value("${restClient.moviesInfoUrl}")
    private String moviesInfoUrl;

    public Mono<MovieInfo> retrieveMovieInfo(String movieId) {
        var url = moviesInfoUrl.concat("/{id}");
        return webClient
                .get()
                .uri(url, movieId)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, moviesInfoResponse -> handle4xxError(movieId, moviesInfoResponse))
                .onStatus(HttpStatus::is5xxServerError, moviesInfoResponse -> handle5xxError(movieId, moviesInfoResponse))
                .bodyToMono(MovieInfo.class)
                .retryWhen(RetryUtils.retrySpec())
                .log();
    }

    private Mono<Throwable> handle4xxError(String movieId, ClientResponse moviesInfoResponse) {
        if (moviesInfoResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
            return Mono.error(
                    new MoviesInfoClientException("No Movie available for passed movieId : " + movieId,
                            moviesInfoResponse.statusCode().value()));
        }

        return moviesInfoResponse.bodyToMono(String.class)
                .flatMap(responseMsg -> Mono.error(
                        new MoviesInfoClientException(responseMsg, moviesInfoResponse.statusCode().value())
                ));
    }

    private Mono<Throwable> handle5xxError(String movieId, ClientResponse moviesInfoResponse) {
        return moviesInfoResponse.bodyToMono(String.class)
                .flatMap(responseMsg -> Mono.error(
                        new MoviesInfoServerException("Server exception in MovieInfoService : " + responseMsg)
                ));
    }
}