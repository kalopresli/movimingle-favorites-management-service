package com.backend.favoritesmanagementservice.repository;

import com.backend.favoritesmanagementservice.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {
}
