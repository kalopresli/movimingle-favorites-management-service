package com.backend.favoritesmanagementservice.repository;

import com.backend.favoritesmanagementservice.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
}
