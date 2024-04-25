package com.backend.favoritesmanagementservice;

import com.backend.favoritesmanagementservice.domain.Movie;
import com.backend.favoritesmanagementservice.domain.UserFavorite;
import com.backend.favoritesmanagementservice.repository.MovieRepository;
import com.backend.favoritesmanagementservice.repository.UserFavoriteRepository;
import com.backend.favoritesmanagementservice.dto.UserFavoriteMoviesMessage;
import com.backend.favoritesmanagementservice.service.MessageSenderService;
import com.backend.favoritesmanagementservice.service.UserFavoriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserFavoriteServiceTests {

    @Mock
    private UserFavoriteRepository userFavoriteRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MessageSenderService messageSenderService;

    @InjectMocks
    private UserFavoriteService userFavoriteService;



/*    @Test
    public void testAddFavoriteMovie() {
        Long userId = 1L;
        String movieExternalId = "mov123";
        Movie movie = new Movie();
        movie.setExternalId(movieExternalId);
        UserFavorite userFavorite = new UserFavorite(userId);
        userFavorite.setFavoriteMovies(new HashSet<>());

        when(userFavoriteRepository.findByUserId(userId)).thenReturn(Optional.of(userFavorite));
        when(movieRepository.findById(movieExternalId)).thenReturn(Optional.of(movie));

        UserFavorite result = userFavoriteService.addFavoriteMovie(userId, movieExternalId);

        assertThat(result.getFavoriteMovies()).contains(movie);
        verify(userFavoriteRepository).save(userFavorite);
    }*/

    @Test
    public void testAddFavoriteMovie_MovieNotFound() {
        Long userId = 1L;
        String movieExternalId = "mov404";

        when(userFavoriteRepository.findByUserId(userId)).thenReturn(Optional.of(new UserFavorite(userId)));
        when(movieRepository.findById(movieExternalId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> userFavoriteService.addFavoriteMovie(userId, movieExternalId));
    }

    @Test
    public void testSendAllUserFavoriteMovies() {
        Long userId = 1L;
        UserFavorite userFavorite = new UserFavorite(userId);
        Movie movie = new Movie();
        movie.setExternalId("mov123");
        userFavorite.setFavoriteMovies(new HashSet<>());
        userFavorite.getFavoriteMovies().add(movie);

        when(userFavoriteRepository.findByUserId(userId)).thenReturn(Optional.of(userFavorite));

        userFavoriteService.sendAllUserFavoriteMovies(userId);

        verify(messageSenderService).sendUserFavoriteMovies(any(UserFavoriteMoviesMessage.class));
    }
}

