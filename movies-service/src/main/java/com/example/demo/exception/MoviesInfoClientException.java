package com.example.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class MoviesInfoClientException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private Integer statusCode;

	public MoviesInfoClientException(String message, Integer statusCode) {
		super(message);
		this.message = message;
		this.statusCode = statusCode;
	}
}