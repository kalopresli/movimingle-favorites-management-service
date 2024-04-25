package com.backend.favoritesmanagementservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class UserFavoriteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql("classpath:testdata.sql")  // SQL script that inserts required movie object to add to favorites after that
    public void testAddFavoriteMovieIntegration() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/favorites/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1,\"movieId\":\"movie123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1));
    }
}


