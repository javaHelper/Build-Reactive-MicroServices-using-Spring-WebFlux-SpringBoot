package com.example.demo.exceptionhandler;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.example.demo.exception.ReviewDataException;
import com.example.demo.exception.ReviewNotFoundException;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalErrorHandler implements ErrorWebExceptionHandler {

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		log.error("Exception messages : {}", ex.getMessage());
		DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();
		var errMsg = dataBufferFactory.wrap(ex.getMessage().getBytes());
		if (ex instanceof ReviewDataException)
			exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
		else if (ex instanceof ReviewNotFoundException)
			exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
		else
			exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);

		return exchange.getResponse().writeWith(Mono.just(errMsg));
	}
}