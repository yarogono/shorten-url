package com.example.shortenurl.controller;

import com.example.shortenurl.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/shorten")
    public String generateShortenUrl(@RequestParam("url") String originalUrl) {
        return urlService.generateShortenUrl(originalUrl);
    }

    @GetMapping("/shorten/{shortUrl}")
    public void redirect(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        String originalUrl = urlService.getOriginalUrlByShortUrl(shortUrl);

        String encodeRedirectURL = response.encodeRedirectURL(originalUrl);
        response.sendRedirect(encodeRedirectURL);
    }
}
