package com.example.demo.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.MoviesInfoClientException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {

	@ExceptionHandler(MoviesInfoClientException.class)
	public ResponseEntity<String> handleMoviesInfoClientException(MoviesInfoClientException ex) {
		log.error("Exception caught handleMoviesInfoClientException in : {}", ex.getMessage());
		return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
		log.error("Exception caught handleRuntimeException in : {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}
}