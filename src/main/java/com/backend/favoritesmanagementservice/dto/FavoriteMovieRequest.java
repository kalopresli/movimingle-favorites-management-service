package com.backend.favoritesmanagementservice.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FavoriteMovieRequest {
    private Long userId;
    private String movieId;
}