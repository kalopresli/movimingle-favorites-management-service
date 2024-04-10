package com.backend.favoritesmanagementservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Movie {
    @Id
    private String externalId; // the unique ID from the external API

    private String title; // caching title
    private String description; // caching description
}
