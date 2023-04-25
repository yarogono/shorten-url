package com.example.shortenurl.repository;

import com.example.shortenurl.model.UrlShorten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlShorten, Long> {
}
