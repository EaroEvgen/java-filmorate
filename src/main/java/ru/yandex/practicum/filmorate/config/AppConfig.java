package ru.yandex.practicum.filmorate.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Instant;

@Setter
@Getter
@ToString
@ConfigurationProperties("java-filmorate")
public class AppConfig {
    private int maxLengthDescription;
    private Instant maxFilmAge;
}