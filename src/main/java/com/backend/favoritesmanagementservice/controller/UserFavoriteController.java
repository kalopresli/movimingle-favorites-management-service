package com.backend.favoritesmanagementservice.controller;

import com.backend.favoritesmanagementservice.domain.UserFavorite;
import com.backend.favoritesmanagementservice.dto.FavoriteMovieRequest;
import com.backend.favoritesmanagementservice.service.UserFavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/favorites")
public class UserFavoriteController {

    private final UserFavoriteService userFavoriteService;

    @PostMapping("/add")
    public UserFavorite addFavoriteMovie(@RequestBody FavoriteMovieRequest request) {
        return userFavoriteService.addFavoriteMovie(request.getUserId(), request.getMovieId());
    }

    @GetMapping
    public List<UserFavorite> getAllFavorites(@RequestParam Long userId) {
        return userFavoriteService.getAllFavorites(userId);
    }
}
