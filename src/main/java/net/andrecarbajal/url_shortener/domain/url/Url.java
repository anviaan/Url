package net.andrecarbajal.url_shortener.domain.url;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JoinColumn(name = "original_url")
    private String originalUrl;

    @JoinColumn(name = "short_url")
    private String shortUrl;

    @JoinColumn(name = "created_at")
    private Date createdAt;
}
