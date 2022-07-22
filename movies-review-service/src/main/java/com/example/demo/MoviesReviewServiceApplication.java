package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Review;
import com.example.demo.repository.ReviewReactiveRepository;

@SpringBootApplication
public class MoviesReviewServiceApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MoviesReviewServiceApplication.class, args);
	}

	@Autowired
	private ReviewReactiveRepository reviewReactiveRepository;

	@Override
	public void run(String... args) throws Exception {
		//saveReviews();
	}

	public void saveReviews() {
		List<Review> reviews = List.of(
				Review.builder().movieInfoId("62da836342b68c5790658f45").comment("Good Movie").rating(8.0).build(),
				Review.builder().movieInfoId("62da836342b68c5790658f46").comment("Good Movie").rating(8.8).build(),
				Review.builder().movieInfoId("62da836342b68c5790658f47").comment("Good Movie").rating(7.2).build(),
				Review.builder().movieInfoId("62da836342b68c5790658f48").comment("Good Movie").rating(9.0).build(),
				Review.builder().movieInfoId("62da836342b68c5790658f49").comment("Good Movie").rating(6.7).build()
				);

		reviewReactiveRepository.saveAll(reviews).subscribe(System.out::println);
	}
}
