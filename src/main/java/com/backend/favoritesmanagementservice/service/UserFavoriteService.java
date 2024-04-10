package com.backend.favoritesmanagementservice.service;

import com.backend.favoritesmanagementservice.dto.UserFavoriteMoviesMessage;
import com.backend.favoritesmanagementservice.domain.Movie;
import com.backend.favoritesmanagementservice.domain.UserFavorite;
import com.backend.favoritesmanagementservice.repository.MovieRepository;
import com.backend.favoritesmanagementservice.repository.UserFavoriteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserFavoriteService {
    private final UserFavoriteRepository userFavoriteRepository;
    private final MovieRepository movieRepository;
    private final MessageSenderService messageSenderService;

    @Transactional
    public UserFavorite addFavoriteMovie(Long userId, String movieExternalId) {
        Optional<UserFavorite> userFavoriteOpt = userFavoriteRepository.findByUserId(userId);
        UserFavorite userFavorite = userFavoriteOpt.orElseGet(() -> new UserFavorite(userId));

        Movie movie = movieRepository.findById(movieExternalId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with id: " + movieExternalId));

        userFavorite.getFavoriteMovies().add(movie);
        return userFavoriteRepository.save(userFavorite);
    }

    // No changes required for this method if it's working as expected
    @Transactional
    public List<UserFavorite> getAllFavorites(Long userId) {
        return userFavoriteRepository.getAllByUserId(userId);
    }

    // New method to send all favorite movies for a user
    @Transactional
    public void sendAllUserFavoriteMovies(Long userId) {
        UserFavorite userFavorite = userFavoriteRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Set<String> movieIds = userFavorite.getFavoriteMovies().stream()
                .map(Movie::getExternalId)
                .collect(Collectors.toSet());

        UserFavoriteMoviesMessage message = new UserFavoriteMoviesMessage(userFavorite.getUserId(), movieIds);
        messageSenderService.sendUserFavoriteMovies(message);
    }

    // Helper method to create the message, can be used by sendAllUserFavoriteMovies
    //not used at the moment
    private UserFavoriteMoviesMessage createMessage(UserFavorite userFavorite) {
        Set<String> movieIds = userFavorite.getFavoriteMovies().stream()
                .map(Movie::getExternalId)
                .collect(Collectors.toSet());
        return new UserFavoriteMoviesMessage(userFavorite.getUserId(), movieIds);
    }
}
