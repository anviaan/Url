package net.andrecarbajal.url_shortener.domain.url;

import lombok.RequiredArgsConstructor;
import net.andrecarbajal.url_shortener.infra.ValidationException;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;

    public String shortenUrl(String originalUrl) {
        if (!isValidUrl(originalUrl)) {
            throw new ValidationException("Invalid URL format");
        }

        List<Url> existingUrls = urlRepository.findByOriginalUrl(originalUrl);
        if (!existingUrls.isEmpty()) {
            return "http://localhost:8080/" + existingUrls.getFirst().getUrlCode();
        }

        String urlCode = generateCode();
        Url url = Url.builder()
                .originalUrl(originalUrl)
                .urlCode(urlCode)
                .createdAt(new Date())
                .build();
        urlRepository.save(url);

        return "http://localhost:8080/" + urlCode;
    }

    public String getOriginalUrl(String shortUrl) {
        Optional<Url> url = urlRepository.findByUrlCode(shortUrl);
        return url.map(Url::getOriginalUrl).orElse(null);
    }

    private boolean isValidUrl(String url) {
        String URL_REGEX = "^(https?|ftp)://([a-zA-Z0-9.-]+)(:[0-9]+)?(/.*)?$";
        Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

        if (!URL_PATTERN.matcher(url).matches()) {
            return false;
        }

        try {
            URL urlObj = new URI(url).toURL();
            String protocol = urlObj.getProtocol();
            String host = urlObj.getHost();

            if (protocol == null || host == null || host.isEmpty()) {
                return false;
            }

            String[] hostParts = host.split("\\.");
            return hostParts.length >= 2 && !hostParts[0].isEmpty() && !hostParts[hostParts.length - 1].isEmpty();
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }

    private String generateCode() {
        String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int CODE_LENGTH = 6;
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(CHAR_SET.charAt(new SecureRandom().nextInt(CHAR_SET.length())));
        }
        return code.toString();
    }
}