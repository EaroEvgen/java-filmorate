package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
public class Film {
    @NotNull
    private long id;
    @NotNull
    @NotBlank
    private String name;
    @Size(max = 200)
    private String description;
    private Instant releaseDate;
    private Duration duration;
    private Set<Long> likes = new HashSet<>();
}
