package com.example.shortenurl.service;

import com.example.shortenurl.model.UrlShorten;
import com.example.shortenurl.repository.UrlRepository;
import com.example.shortenurl.utils.Base62;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    private final Base62 base62;

    @Transactional
    public String generateShortenUrl(String originalUrl) {

        UrlShorten urlShorten = UrlShorten.builder()
                .longUrl(originalUrl)
                .build();


        urlRepository.save(urlShorten);

        Long urlShortenId =  urlShorten.getUrlShortenId();
        String encodedUrl = base62.encode(urlShortenId);
        System.out.println(encodedUrl);

        urlShorten.updateShortUrl(encodedUrl);
        System.out.println(urlShorten.getShortUrl());
        return null;
    }

    public String getOriginalUrlByShortUrl(String shortenUrl) {
        long decodedShortenUrl = base62.decode(shortenUrl);
        System.out.println("decoded Shorten URL : " + decodedShortenUrl);

        UrlShorten urlShorten = urlRepository.findById(decodedShortenUrl)
                .orElseThrow(
                        () -> new IllegalArgumentException("일리걸")
                );

        log.info("Original URL={}", urlShorten.getLongUrl());

        return urlShorten.getLongUrl();
    }
}
