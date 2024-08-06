package net.andrecarbajal.url_shortener.domain.url;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByUrlCode(String url_code);
    List<Url> findByOriginalUrl(String originalUrl);
}
