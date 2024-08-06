package net.andrecarbajal.url_shortener.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net.andrecarbajal.url_shortener.domain.url.UrlService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/shorten")
    @Transactional
    public String shortenUrl(@RequestParam("originalUrl") String originalUrl, Model model) {
        String shortUrl = urlService.shortenUrl(originalUrl);
        model.addAttribute("shortUrl", shortUrl);
        return "index";
    }

    @GetMapping("/{urlCode}")
    public void getOriginalUrl(@PathVariable String urlCode, HttpServletResponse response) throws IOException {
        String originalUrl = urlService.getOriginalUrl(urlCode);
        if (originalUrl != null) {
            response.sendRedirect(originalUrl);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
