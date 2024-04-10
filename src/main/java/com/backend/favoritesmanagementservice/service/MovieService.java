package com.backend.favoritesmanagementservice.service;

import com.backend.favoritesmanagementservice.domain.Movie;
import com.backend.favoritesmanagementservice.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Optional<Movie> findMovieByExternalId(String externalId) {
        return movieRepository.findById(externalId);
    }
}
