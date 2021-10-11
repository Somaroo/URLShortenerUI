package com.somar.URLShortenerUI.repository;

import com.somar.URLShortenerUI.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    public Optional<Url> findByUrlShort(@Param("urlShort") String urlShort);
    public Optional<Url> findByUrl(@Param("url") String url);
    public Optional<Url> findById(@Param("id") Long id);
    public boolean existsByUrl(@Param("url") String url);





}
