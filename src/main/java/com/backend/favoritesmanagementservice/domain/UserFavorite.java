package com.backend.favoritesmanagementservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserFavorite{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
            name = "user_favorite_movies",
            joinColumns = @JoinColumn(name = "user_favorite_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_external_id")
    )
    private Set<Movie> favoriteMovies = new HashSet<>();

    public UserFavorite(Long userId)
    {
        this.userId = userId;
    }
}
