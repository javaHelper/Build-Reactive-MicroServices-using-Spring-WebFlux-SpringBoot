package com.example.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class MoviesInfoServerException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public MoviesInfoServerException(String message) {
		super(message);
		this.message = message;
	}
}