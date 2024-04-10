package com.backend.favoritesmanagementservice.controller;

import com.backend.favoritesmanagementservice.domain.Movie;
import com.backend.favoritesmanagementservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieService.saveMovie(movie);
        return ResponseEntity.ok(savedMovie);
    }

    @GetMapping("/{externalId}")
    public ResponseEntity<Movie> getMovie(@PathVariable String externalId) {
        Optional<Movie> movie = movieService.findMovieByExternalId(externalId);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
