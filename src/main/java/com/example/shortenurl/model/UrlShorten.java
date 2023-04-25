package com.example.shortenurl.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlShorten {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long urlShortenId;

    private String shortUrl;

    private String longUrl;

    public void updateShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
