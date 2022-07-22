package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.MovieInfo;
import com.example.demo.repository.MovieInfoRepository;

@SpringBootApplication
public class MoviesInfoServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MoviesInfoServiceApplication.class, args);
	}

	@Autowired
	private MovieInfoRepository infoRepository;

	@Override
	public void run(String... args) throws Exception {
		//saveMovieInfos();
	}

	public void saveMovieInfos() {
		List<MovieInfo> movieInfos = List.of(MovieInfo.builder().name("Singham").year(2011).releaseDate(LocalDate.of(2011, 07, 22))
				.cast(List.of("Ajay Devgn", "Kajal Aggarwal", "Prakash Raj")).build(),

				MovieInfo.builder().name("Once Upon Time In India").year(2010).releaseDate(LocalDate.of(2010, 07, 30))
				.cast(List.of("Ajay Devgn", "Emraan Hashmi", "Kangana Ranaut")).build(),

				MovieInfo.builder().name("Golmaal 3").year(2011).releaseDate(LocalDate.of(2010, 10, 05))
				.cast(List.of("Ajay Devgn", "Kareena Kapoor", "Arshad Warsit")).build(),

				MovieInfo.builder().name("Tanhaji").year(2021).releaseDate(LocalDate.of(2021, 01, 10))
				.cast(List.of("Ajay Devgn", "Saif Ali Khan", "Kajol")).build(),

				MovieInfo.builder().name("Golmaal Again").year(2017).releaseDate(LocalDate.of(2017, 9, 20))
				.cast(List.of("Ajay Devgn", "Tushar Kapoor", "Tabu")).build());

		infoRepository.saveAll(movieInfos).subscribe(s -> System.out.println(s));
	}
}
