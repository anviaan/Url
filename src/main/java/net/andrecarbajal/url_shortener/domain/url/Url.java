package net.andrecarbajal.url_shortener.domain.url;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "url")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "original_url")
    private String originalUrl;

    @JoinColumn(name = "url_code")
    private String urlCode;

    @JoinColumn(name = "created_at")
    private Date createdAt;
}
