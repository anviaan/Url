package net.andrecarbajal.url_shortener.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net.andrecarbajal.url_shortener.domain.url.UrlRecord;
import net.andrecarbajal.url_shortener.domain.url.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/shorten")
    @Transactional
    public ResponseEntity<String> shortenUrl(@RequestBody UrlRecord data) {
        String shortUrl = urlService.shortenUrl(data.originalUrl());
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{shortUrl}")
    public void getOriginalUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        if (originalUrl != null) {
            response.sendRedirect(originalUrl);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
