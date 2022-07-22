package com.example.demo.model;

import javax.validation.constraints.Min;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Review {

	@Id
	private String reviewId;
	private String movieInfoId;
	private String comment;
	@Min(value = 0L, message = "rating.negative : please pass a non-negative value")
	private Double rating;
}