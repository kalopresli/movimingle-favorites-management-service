package com.backend.favoritesmanagementservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    private String externalId; // the unique ID from the external API

    private String title; // caching title
    private String description; // caching description
}
