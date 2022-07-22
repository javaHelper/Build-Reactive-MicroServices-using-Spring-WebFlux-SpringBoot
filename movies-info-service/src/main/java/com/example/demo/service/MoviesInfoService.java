package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.MovieInfo;
import com.example.demo.repository.MovieInfoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MoviesInfoService {

	@Autowired
	private MovieInfoRepository movieInfoRepository;

	public Mono<MovieInfo> addMovieInfo(MovieInfo movieInfo) {
		return movieInfoRepository.save(movieInfo);
	}

	public Flux<MovieInfo> getAllMovieInfos() {
		return movieInfoRepository.findAll();
	}

	public Mono<MovieInfo> getMovieInfoById(String id) {
		return movieInfoRepository.findById(id);
	}

	public Mono<MovieInfo> updateMovieInfo(String id, MovieInfo movieInfo) {
		return movieInfoRepository.findById(id).flatMap(existingMovieInfo -> {
			existingMovieInfo.setCast(movieInfo.getCast());
			existingMovieInfo.setName(movieInfo.getName());
			existingMovieInfo.setYear(movieInfo.getYear());
			existingMovieInfo.setReleaseDate(movieInfo.getReleaseDate());
			
			return movieInfoRepository.save(existingMovieInfo);
		});
	}

	public Mono<Void> deleteMovieInfo(String id) {
		return movieInfoRepository.deleteById(id);
	}

	public Flux<MovieInfo> findMovieInfosByYear(Integer year) {
		return movieInfoRepository.findByYear(year);
	}

	public Flux<MovieInfo> findMovieInfosByName(String name) {
		return movieInfoRepository.findByName(name);
	}
}