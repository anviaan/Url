package net.andrecarbajal.url_shortener.domain.url;

import jakarta.validation.constraints.NotBlank;

public record UrlRecord(
        @NotBlank String originalUrl
) {
}
