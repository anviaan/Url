package net.andrecarbajal.url_shortener.domain.url;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record UrlRecord(
        @NotBlank @URL String originalUrl
) {
}
