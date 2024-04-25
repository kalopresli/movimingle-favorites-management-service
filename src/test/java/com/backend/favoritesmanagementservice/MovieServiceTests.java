package com.backend.favoritesmanagementservice;

import com.backend.favoritesmanagementservice.domain.Movie;
import com.backend.favoritesmanagementservice.repository.MovieRepository;
import com.backend.favoritesmanagementservice.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTests {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;


    @Test
    public void testSaveMovie() {
        Movie movie = new Movie();
        movie.setExternalId("12345");
        movie.setTitle("Test Movie");

        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        Movie savedMovie = movieService.saveMovie(movie);

        assertThat(savedMovie).isNotNull();
        assertThat(savedMovie.getExternalId()).isEqualTo("12345");
    }

    @Test
    public void testFindMovieByExternalId() {
        Optional<Movie> optionalMovie = Optional.of(new Movie());
        when(movieRepository.findById("12345")).thenReturn(optionalMovie);

        Optional<Movie> result = movieService.findMovieByExternalId("12345");

        assertThat(result).isPresent();
    }
}
