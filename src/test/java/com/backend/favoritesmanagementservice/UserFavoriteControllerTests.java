package com.backend.favoritesmanagementservice;

import com.backend.favoritesmanagementservice.controller.UserFavoriteController;
import com.backend.favoritesmanagementservice.domain.UserFavorite;
import com.backend.favoritesmanagementservice.dto.FavoriteMovieRequest;
import com.backend.favoritesmanagementservice.service.UserFavoriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class UserFavoriteControllerTests {

    private MockMvc mockMvc;

    @Mock
    private UserFavoriteService userFavoriteService;

    @InjectMocks
    private UserFavoriteController userFavoriteController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userFavoriteController).build();
    }

    @Test
    public void testAddFavoriteMovie() throws Exception {
        FavoriteMovieRequest request = new FavoriteMovieRequest();
        request.setUserId(4L); // Adjusted userId to match the request body
        request.setMovieId("movie123");

        UserFavorite expected = new UserFavorite(4L); // Adjusted userId to match the request body
        expected.setId(1L); // Setting ID for completeness in mock

        given(userFavoriteService.addFavoriteMovie(anyLong(), anyString())).willReturn(expected);

        mockMvc.perform(post("/favorites/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":4,\"movieId\":\"movie123\"}")) // Adjusted userId to match the request body
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(4)); // Check for userId in JSON response, adjusted to match the request body
    }

    @Test
    public void testGetAllFavorites() throws Exception {
        List<UserFavorite> favorites = Collections.singletonList(new UserFavorite()); // Populate as needed
        given(userFavoriteService.getAllFavorites(anyLong())).willReturn(favorites);

        mockMvc.perform(get("/favorites")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1")) // Assuming you will change it to use @RequestParam or @PathVariable
                .andExpect(status().isOk());
    }
}