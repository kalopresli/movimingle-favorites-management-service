package com.backend.favoritesmanagementservice.repository;


import com.backend.favoritesmanagementservice.domain.UserFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserFavoriteRepository extends JpaRepository<UserFavorite, Long> {
    Optional<UserFavorite> findByUserId(Long userId);
    List<UserFavorite> getAllByUserId(Long userId);
}
