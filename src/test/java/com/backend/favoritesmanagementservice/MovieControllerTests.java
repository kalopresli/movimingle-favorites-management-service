package com.backend.favoritesmanagementservice;

import com.backend.favoritesmanagementservice.controller.MovieController;
import com.backend.favoritesmanagementservice.domain.Movie;
import com.backend.favoritesmanagementservice.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class MovieControllerTests {

    private MockMvc mockMvc;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void testAddMovie() throws Exception {
        Movie movie = new Movie();
        movie.setExternalId("123");
        movie.setTitle("Test Movie");

        assertThat(movie.getTitle()).isEqualTo("Test Movie");

        given(movieService.saveMovie(any(Movie.class))).willReturn(movie);

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"externalId\":\"123\",\"title\":\"Test Movie\"}"))
                .andDo(print()) // prints the request and response for debugging
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Movie"));
    }


    @Test
    public void testGetMovie() throws Exception {
        Optional<Movie> optionalMovie = Optional.of(new Movie());
        optionalMovie.get().setExternalId("123");
        optionalMovie.get().setTitle("Test Movie");

        given(movieService.findMovieByExternalId("123")).willReturn(optionalMovie);

        mockMvc.perform(get("/movies/123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Test Movie"));
    }
}