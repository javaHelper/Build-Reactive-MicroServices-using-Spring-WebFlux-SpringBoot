package com.example.demo.utils;

import java.time.Duration;

import com.example.demo.exception.MoviesInfoServerException;
import com.example.demo.exception.ReviewsServerException;


import reactor.core.Exceptions;
import reactor.util.retry.Retry;

public class RetryUtils {

	public static Retry retrySpec() {
		return Retry.fixedDelay(3, Duration.ofSeconds(1))
				.filter(ex -> ex instanceof MoviesInfoServerException || ex instanceof ReviewsServerException)
				.onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> Exceptions.propagate(retrySignal.failure()));
	}
}