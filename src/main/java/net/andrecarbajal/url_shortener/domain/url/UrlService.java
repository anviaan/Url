package net.andrecarbajal.url_shortener.domain.url;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    private final String BASE_URL = "http://localhost:8080/";

    public String shortenUrl(String originalUrl) {
        Url url = Url.builder()
                .originalUrl(originalUrl)
                .urlCode(generateCode())
                .createdAt(new Date())
                .build();
        urlRepository.save(url);
        return BASE_URL + generateCode();
    }

    public String getOriginalUrl(String shortUrl) {
        Optional<Url> url = urlRepository.findByUrlCode(shortUrl);
        return url.map(Url::getOriginalUrl).orElse(null);
    }

    private String generateCode() {
        return Long.toHexString(System.currentTimeMillis());
    }
}